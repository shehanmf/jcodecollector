package ecompilerlab.test;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/24/13
 * Time: 11:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class SnipSearch
{

  public static SniperResponce searchSnippet(String searchString)
  {
//    String url = "https://api.github.com/search/code?q=org.apache.commons.math3.stat.Frequency+in:file+extension:Java";


    final String url = String.format("https://api.github.com/search/code?q=%s+in:file+extension:Java", searchString);
    HttpClient client = new DefaultHttpClient();
    HttpGet request = new HttpGet(url);
    request.setHeader("Accept", "application/vnd.github.preview");

    try
    {
      HttpResponse response = client.execute(request);

      StringBuilder builder = new StringBuilder();
      if (response.getStatusLine().getStatusCode() == 200)
      {
        HttpEntity entity = response.getEntity();

        // All the work is done for you here :)
        String jsonContent = EntityUtils.toString(entity);

// Create a Reader from String
        Reader stringReader = new StringReader(jsonContent);
        return printURLS(stringReader);
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

      return null;
  }


  private static SniperResponce printURLS(Reader stringReader) throws IOException
  {

    Gson gson = new Gson();
    return gson.fromJson(stringReader, SniperResponce.class);
  }


}
