package ecompilerlab.service.impl;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/2/13
 * Time: 8:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompileRequest implements Serializable{

  private String code;

  private Platforms platform;

  private String[] libraryIDs;

  public String getCode()
  {
    return code;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public Platforms getPlatform()
  {
    return platform;
  }

  public void setPlatform(Platforms platform)
  {
    this.platform = platform;
  }

  public String[] getLibraryIDs()
  {
    return libraryIDs;
  }

  public void setLibraryIDs(String[] libraryIDs)
  {
    this.libraryIDs = libraryIDs;
  }
}
