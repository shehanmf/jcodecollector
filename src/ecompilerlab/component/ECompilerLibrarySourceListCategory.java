package ecompilerlab.component;

import com.explodingpixels.macwidgets.SourceListCategory;
import ecompilerlab.clientstub.Platforms;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/2/13
 * Time: 3:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class ECompilerLibrarySourceListCategory extends SourceListCategory
{

  private Platforms platform;

  private String uniqueName;

  public ECompilerLibrarySourceListCategory(String text, String uniqueName)
  {
    super(text);
    this.uniqueName = uniqueName;
  }

  public ECompilerLibrarySourceListCategory(String text, Platforms platform)
  {
    super(text);
    this.platform = platform;
  }

  public Platforms getPlatform()
  {
    return platform;
  }

  public void setPlatform(Platforms platform)
  {
    this.platform = platform;
  }

  public String getUniqueName()
  {
    return uniqueName;
  }

  public void setUniqueName(String uniqueName)
  {
    this.uniqueName = uniqueName;
  }
}
