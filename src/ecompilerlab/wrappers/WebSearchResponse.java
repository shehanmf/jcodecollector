package ecompilerlab.wrappers;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/28/13
 * Time: 12:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebSearchResponse implements Serializable
{

  public List<WebSearchItem> items;

  @SerializedName("total_count")
  public String total_count;
}
