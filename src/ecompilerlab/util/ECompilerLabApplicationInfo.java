package ecompilerlab.util;

import jcodecollector.util.ApplicationInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 5/26/13
 * Time: 6:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class ECompilerLabApplicationInfo implements ApplicationInfo {


    /** Application's name. */
    private static final String APPLICATION_NAME = "ECompilerLab";

    /** Application's version. */
    private static final String APPLICATION_VERSION = "1.0";

    /** Years of copyright. */
    private static final String COPYRIGHT_YEARS = "2013";

    /** My blog address. */
    private static final String BLOG_URL = "";

    /** My email address. */
    private static final String MY_EMAIL = "";

    public ECompilerLabApplicationInfo() {
        // do nothing
    }


    @Override
    public String getApplicationName() {
        return APPLICATION_NAME;
    }

    @Override
    public String getApplicationVersion() {
        return APPLICATION_VERSION;
    }

    @Override
    public String getCopyrightYears() {
        return COPYRIGHT_YEARS;
    }

    @Override
    public String getBlogUrl() {
        return BLOG_URL;
    }

    @Override
    public String getMyEmail() {
        return MY_EMAIL;
    }
}
