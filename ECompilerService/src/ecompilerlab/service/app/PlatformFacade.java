package ecompilerlab.service.app;

import ecompilerlab.service.impl.Platforms;
import ecompilerlab.service.impl.PlatformsInfo;

/**
 * Created with IntelliJ IDEA.
 * User: shehan.fernando
 * Date: 7/6/13
 * Time: 6:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlatformFacade
{

  private static PlatformFacade instance = new PlatformFacade();

  public PlatformFacade()
  {
    ApplicationSettingsManager.readApplicationSettings();
  }

  public static PlatformFacade getInstance()
  {
    return instance;
  }

  public PlatformsInfo getInfoByPlatform(Platforms platforms)
  {
    switch (platforms)
    {
      case JAVA:
        return new PlatformsInfo(Platforms.JAVA,
          "java version \"" + System.getProperty("java.version") + "\" \nJava(TM) SE Runtime Environment (build " +
            System.getProperty("java.runtime.version") + ") \n" + System.getProperty("java.vm.name") + " (build " +
            System.getProperty("java.vm.version") + ", " + System.getProperty("java.vm.info") + ")");

      case C:
        return new PlatformsInfo(Platforms.C, "Not Implement Yet");
      case C_PLUS:
        return new PlatformsInfo(Platforms.C_PLUS, "Not Implement Yet");
      case C_SHARP:
        return new PlatformsInfo(Platforms.C_SHARP, "Not Implement Yet");
      case PYTHON:
        return new PlatformsInfo(Platforms.PYTHON, "Not Implement Yet");
      default:
        return null;
    }

  }
}
