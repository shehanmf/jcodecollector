package ecompilerlab.test;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/25/13
 * Time: 12:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class SniperResponce
{


  public List<SnippetItems> items;

  @SerializedName("total_count")
  public String total_count;
}
