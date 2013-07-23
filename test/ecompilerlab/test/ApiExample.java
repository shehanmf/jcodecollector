package ecompilerlab.test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/23/13
 * Time: 9:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class ApiExample
{
  private static final char[] hexChars ={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

  public static void usage()
  {
    System.out.println("Usage:");
    System.out.println("java ApiExample [api_key] [user_email]");
  }

  public ApiExample(String apiKey, String userEmail)
  {
    initiate(apiKey, userEmail);
  }

  public void initiate(String apiKey, String userEmail)
  {
    System.out.println("Initialising request.");

    // Calculate MD5 digest from the email address.
    String emailDigest = calculateDigest(userEmail);

    try
    {
      // Request XML file.
      URL url = new URL("http://www.ohloh.net/accounts/" + emailDigest + ".xml?api_key=" + apiKey + "&v=1");
      URLConnection con = url.openConnection();

      // Check for status OK.
      if (con.getHeaderField("Status").startsWith("200"))
      {
        System.out.println("Request succeeded.");
      }
      else
      {
        System.out.println("Request failed. Possibly wrong API key?");
        return;
      }
      System.out.println("Looking up name..");

      // Create a document from the URL's input stream, and parse.
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document doc = builder.parse(con.getInputStream());

      NodeList responseNodes = doc.getElementsByTagName("response");
      for (int i = 0; i < responseNodes.getLength(); i++)
      {
        Element element = (Element)responseNodes.item(i);

        // First check for the status code inside the XML file. It is
        //  most likely, though, that if the request would have failed,
        //  it is already returned earlier.
        NodeList statusList = element.getElementsByTagName("status");
        if (statusList.getLength() == 1)
        {
          final Node statusNode = statusList.item(0);

          // Check if the text inidicates that the request was
          //  successful.
          if (!statusNode.getTextContent().equals("success"))
          {
            System.out.println("Failed. " + statusNode.getTextContent());
            return;
          }
        }

        Element resultElement = (Element)element.getElementsByTagName("result").item(0);
        // We assume we only have one account result here.
        Element accountElement = (Element)resultElement.getElementsByTagName("account").item(0);

        if (accountElement != null)
        {
          // Lookup name.
          String realName = accountElement.getElementsByTagName("name").item(0).getTextContent();
          System.out.println("Located the real name: " + realName);
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public String calculateDigest(String email)
  {
    return hexStringFromBytes(calculateHash(email.getBytes()));
  }

  private byte[] calculateHash(byte[] dataToHash)
  {
    try
    {
      // Calculate MD5 digest.
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(dataToHash, 0, dataToHash.length);
      return md.digest();
    }
    catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  public String hexStringFromBytes(byte[] b)
  {
    // Conversion from bytes to String.
    String hex = "";

    int msb;

    int lsb = 0;
    int i;

    for (i = 0; i < b.length; i++)
    {
      msb = ((int)b[i] & 0x000000FF) / 16;

      lsb = ((int)b[i] & 0x000000FF) % 16;
      hex = hex + hexChars[msb] + hexChars[lsb];
    }
    return hex;
  }

  public static void main(String[] args)
  {
//    if (args.length == 2)
//    {
//      // Simply pass arguments.
      new ApiExample("CM9p6WT6g2HZBOus7HFQ", "shehan_mf@yahoo.com");
//    }
//    else
//    {
//      // Show usage information.
//      usage();
//    }
  }
}