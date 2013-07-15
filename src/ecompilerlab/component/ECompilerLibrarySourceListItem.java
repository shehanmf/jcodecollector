package ecompilerlab.component;

import com.explodingpixels.macwidgets.SourceListItem;
import ecompilerlab.clientstub.LibraryEntity;


/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/2/13
 * Time: 2:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class ECompilerLibrarySourceListItem extends SourceListItem
{


  private LibraryEntity library;

  public ECompilerLibrarySourceListItem(String text, LibraryEntity library)
  {
    super(text);
    this.library = library;
  }

  public LibraryEntity getLibrary()
  {
    return library;
  }

  public void setLibrary(LibraryEntity library)
  {
    this.library = library;
  }
}
