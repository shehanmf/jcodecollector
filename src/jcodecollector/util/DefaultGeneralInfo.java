/*
 * Copyright 2006-2013 Alessandro Cocco.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jcodecollector.util;

/**
 * General informations about the application.
 * 
 * @author Alessandro Cocco
 */
public class DefaultGeneralInfo implements ApplicationInfo{

    /** Application's name. */
    private static final String APPLICATION_NAME = "jCodeCollector";

    /** Application's version. */
    private static final String APPLICATION_VERSION = "2.5";

    /** Years of copyright. */
    private static final String COPYRIGHT_YEARS = "� 2008-2011";

    /** My blog address. */
    private static final String BLOG_URL = "http://alessandro-cocco.blogspot.com";

    /** My email address. */
    private static final String MY_EMAIL = "mailto:alessandro-cocco@javastaff.com";

    private DefaultGeneralInfo() {
        // do nothing
    }

    public String getApplicationName() {
        return APPLICATION_NAME;
    }

    public String getApplicationVersion() {
        return APPLICATION_VERSION;
    }

    public String getCopyrightYears() {
        return COPYRIGHT_YEARS;
    }

    public String getBlogUrl() {
        return BLOG_URL;
    }

    public  String getMyEmail() {
        return MY_EMAIL;
    }
}
