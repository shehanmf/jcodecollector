package ecompilerlab.test;

import com.google.gson.annotations.SerializedName;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/25/13
 * Time: 12:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class SnippetItems
{
  @SerializedName("name")
  public String name;

  @SerializedName("path")
  public String path;

  @SerializedName("html_url")
  public String html_url;
}
