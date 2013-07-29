package ecompilerlab.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/27/13
 * Time: 9:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestWordSelection
{

  public static void main(String[] args)
  {

    String passage = "String dateStart = \"01/14/2012 09:29:58\";\n" +
      "String dateStop = \"01/15/2012 10:31:48\";\n" +
      " \n" +
      "\t\t//HH converts hour in 24 hours format (0-23), day calculation\n" +
      "java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(\"MM/dd/yyyy HH:mm:ss\");\n" +
      "\n" +
      "java.util.Date d1 = null;\n" +
      "java.util.Date d2 = null;\n" +
      " \n" +
      "try {\n" +
      "\td1 = format.parse(dateStart);\n" +
      "\td2 = format.parse(dateStop);\n" +
      " \n" +
      "\t\t\t//in milliseconds\n" +
      "\tlong diff = d2.getTime() - d1.getTime();\n" +
      " \n" +
      "\tlong diffSeconds = diff / 1000 % 60;\n" +
      "\tlong diffMinutes = diff / (60 * 1000) % 60;\n" +
      "\tlong diffHours = diff / (60 * 60 * 1000) % 24;\n" +
      "\tlong diffDays = diff / (24 * 60 * 60 * 1000);\n" +
      " \n" +
      "\tSystem.out.print(diffDays + \" days, \");\n" +
      "\tSystem.out.print(diffHours + \" hours, \");\n" +
      "\tSystem.out.print(diffMinutes + \" minutes, \");\n" +
      "\tSystem.out.print(diffSeconds + \" seconds.\");\n" +
      " \n" +
      "} catch (Exception e) {\n" +
      "\te.printStackTrace();\n" +
      "}";


    String spli[] = passage.split("\\W");

    System.out.println(Arrays.toString(spli));
    String clean[] = clean(spli);
    System.out.println(Arrays.toString(clean));


  }


  public static String[] clean(final String[] firstArray) {

    List<String> list = new ArrayList<String>();

    String pattern = "(?U)\\b\\p{Lu}\\p{L}*\\b";

    for(String s : firstArray) {
      if(s != null && s.length() > 0) {

        if(s.matches(pattern)){
          list.add(s);
        }


      }
    }





    return list.toArray(new String[list.size()]);
  }
}
