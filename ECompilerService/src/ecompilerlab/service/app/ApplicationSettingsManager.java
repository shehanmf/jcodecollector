package ecompilerlab.service.app;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: shehan.fernando
 * Date: 7/6/13
 * Time: 6:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationSettingsManager
{
  public static void readApplicationSettings()
  {
    readApplicationSettings(new File(ApplicationSettings.APPLICATION_SETTING_PATH));
  }

  private static void readApplicationSettings(File file)
  {
    Properties properties = new Properties();
    ApplicationSettings settings = ApplicationSettings.getInstance();

    try
    {
      properties.load(new FileReader(file));
    }
    catch (IOException ex)
    {
      System.err.println("error loading preferences - using default");
    }

    settings.setJavaHome(properties.getProperty("java_home", ""));
    settings.setTestClassName("ECompilerTest");
  }
  //settings.dat
}
