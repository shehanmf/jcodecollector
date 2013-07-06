package ecompilerlab.service.impl;

/**
 * Created with IntelliJ IDEA.
 * User: shehan.fernando
 * Date: 7/6/13
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlatformsInfo
{
  private Platforms platform;

  private String versionInfo;

  public PlatformsInfo(Platforms platform, String versionInfo)
  {
    this.platform = platform;
    this.versionInfo = versionInfo;
  }

  public Platforms getPlatform()
  {
    return platform;
  }

  public void setPlatform(Platforms platform)
  {
    this.platform = platform;
  }

  public String getVersionInfo()
  {
    return versionInfo;
  }

  public void setVersionInfo(String versionInfo)
  {
    this.versionInfo = versionInfo;
  }
}
