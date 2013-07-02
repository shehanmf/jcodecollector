package ecompilerlab.component;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/2/13
 * Time: 8:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class OnlineCompilerTask {


    private static OnlineCompilerTask instance;

    public static OnlineCompilerTask getInstance() {
        if(instance == null)
        {
            instance = new OnlineCompilerTask();
        }
        return instance;
    }

    public void doCompile()
    {}


}
