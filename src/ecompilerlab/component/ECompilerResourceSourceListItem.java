package ecompilerlab.component;

import com.explodingpixels.macwidgets.SourceListItem;
import ecompilerlab.clientstub.ResourceLookUpEntry;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/14/13
 * Time: 9:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class ECompilerResourceSourceListItem extends SourceListItem
{

  private ResourceLookUpEntry resource;

  public ECompilerResourceSourceListItem(String text, ResourceLookUpEntry resourceLookUpEntry)
  {
    super(text);
    this.resource = resourceLookUpEntry;
  }

  public ResourceLookUpEntry getResource()
  {
    return resource;
  }

  public void setResource(ResourceLookUpEntry resource)
  {
    this.resource = resource;
  }
}
