package ecompilerlab.service.impl;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 6/11/13
 * Time: 7:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class LibraryEntity
{

  private int id;

  private String name;

  private Platforms platform;

  private boolean isFromCloud;

  private String baseLibPath;

  private String[] libNames;

  public LibraryEntity(int id, String name, Platforms platform, boolean fromCloud, String baseLibPath,
                       String[] libNames)
  {
    this.id = id;
    this.name = name;
    this.platform = platform;
    isFromCloud = fromCloud;
    this.baseLibPath = baseLibPath;
    this.libNames = libNames;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String[] getLibNames()
  {
    return libNames;
  }

  public void setLibNames(String[] libNames)
  {
    this.libNames = libNames;
  }

  public Platforms getPlatform()
  {
    return platform;
  }

  public void setPlatform(Platforms platform)
  {
    this.platform = platform;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public boolean isFromCloud()
  {
    return isFromCloud;
  }

  public void setFromCloud(boolean fromCloud)
  {
    isFromCloud = fromCloud;
  }

  public String getBaseLibPath()
  {
    return baseLibPath;
  }

  public void setBaseLibPath(String baseLibPath)
  {
    this.baseLibPath = baseLibPath;
  }
}

