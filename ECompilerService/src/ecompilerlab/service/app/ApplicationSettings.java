package ecompilerlab.service.app;

/**
 * Created with IntelliJ IDEA.
 * User: shehan.fernando
 * Date: 7/6/13
 * Time: 6:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationSettings
{

  private static ApplicationSettings settings = new ApplicationSettings();

  private String javaHome;

  private String testClassName;

  public static ApplicationSettings getInstance()
  {
    return settings;
  }

  public static String APPLICATION_SETTING_PATH = "";

  static
  {
    APPLICATION_SETTING_PATH = "D:\\Personal\\Project\\ecompilerlab\\ECompilerService\\src\\ecompilerlab\\service\\app\\settings.dat";
  }

  public void setJavaHome(String javaHome)
  {
    this.javaHome = javaHome;
  }

  public String getJavaHome()
  {
    return javaHome;
  }

  public String getTestClassName()
  {
    return testClassName;
  }

  public void setTestClassName(String testClassName)
  {
    this.testClassName = testClassName;
  }
}
