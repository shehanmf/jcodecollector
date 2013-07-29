package ecompilerlab.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/27/13
 * Time: 11:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class JsoupExm
{

  public static void main(String[] args) throws IOException
  {

//    String searchString = "org.apache.commons.math.stat.Frequency"
//      ;
//    final String url = String.format("https://api.github.com/search/code?q=%s+in:file+extension:Java", searchString);
    Document doc = Jsoup.connect(
      "https://github.com/zzzj15/senior_design/blob/350e21e58acd4fbb86ebfa00a3e7866b66ce51ca/src/com/example/tests/featureTester.java")
      .get();

    final Element last = doc.select("td").last();

    System.out.println(last.toString());
  }
}
