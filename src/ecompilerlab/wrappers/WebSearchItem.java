package ecompilerlab.wrappers;

import com.google.gson.annotations.SerializedName;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/28/13
 * Time: 12:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebSearchItem
{

  @SerializedName("name")
  public String name;

  @SerializedName("path")
  public String path;

  @SerializedName("html_url")
  public String html_url;
}
