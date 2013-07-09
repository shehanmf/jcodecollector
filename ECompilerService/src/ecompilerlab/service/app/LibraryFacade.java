package ecompilerlab.service.app;

import ecompilerlab.service.impl.LibraryEntity;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/7/13
 * Time: 6:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class LibraryFacade
{
  private static LibraryFacade instance = new LibraryFacade();

  public static LibraryFacade getInstance()
  {
    return instance;
  }

  public LibraryEntity[] getLibrariesByID(String[] ids)
  {
    return TmpDb.getLibrariesByID(ids).toArray(new LibraryEntity[]{});
  }

  public LibraryEntity[] getAllLibraries()
  {
    return TmpDb.getAllLibraries().toArray(new LibraryEntity[]{});
  }
}
