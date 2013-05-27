package jcodecollector.util;

import ecompilerlab.util.ECompilerLabApplicationInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 5/26/13
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationInfoFactory {


    private static ApplicationInfoFactory instance;

    public static ApplicationInfoFactory getInstance()
    {
        if(instance == null)
        {
            instance = new ApplicationInfoFactory();
        }

        return instance;
    }

    public ApplicationInfo getCurrentApplication()
    {
        return new ECompilerLabApplicationInfo();
    }

}
