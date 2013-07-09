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

  private boolean compileOnly;


  public CompileRequest(String code, Platforms platform, String[] libraryIDs, boolean compileOnly)
  {
    this.code = code;
    this.platform = platform;
    this.libraryIDs = libraryIDs;
    this.compileOnly = compileOnly;
  }

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

  public boolean isCompileOnly()
  {
    return compileOnly;
  }

  public void setCompileOnly(boolean compileOnly)
  {
    this.compileOnly = compileOnly;
  }
}
