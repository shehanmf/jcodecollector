package ecompilerlab.component.model;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 9/9/13
 * Time: 11:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class SourceTagDialogModel implements Cloneable
{

  private String name;

  private ArrayList<String> tags;

  private int ratings;

  private String sourceCode;


  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public ArrayList<String>  getTags()
  {
    return tags;
  }

  public void setTags(ArrayList<String> tags)
  {
    this.tags = tags;
  }

  public int getRatings()
  {
    return ratings;
  }

  public void setRatings(int ratings)
  {
    this.ratings = ratings;
  }

  public String getSourceCode()
  {
    return sourceCode;
  }

  public void setSourceCode(String sourceCode)
  {
    this.sourceCode = sourceCode;
  }

  public String getTagsAsString() {
    if (tags == null || tags.size() == 0 || (tags.size() == 1 && tags.get(0).trim().length() == 0)) {
      return "";
    }

    StringBuilder temp = new StringBuilder();

    for (String s : tags) {
      temp.append(s + ", ");
    }

    return temp.toString();
  }
}
