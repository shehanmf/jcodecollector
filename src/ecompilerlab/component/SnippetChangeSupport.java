package ecompilerlab.component;

import jcodecollector.common.bean.Snippet;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/2/13
 * Time: 7:19 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SnippetChangeSupport {

    public void setSnippet(Snippet snippet) ;

    public Snippet getSnippet();

    public void createNewSnippet();
}
