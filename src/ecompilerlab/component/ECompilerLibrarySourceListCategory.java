package ecompilerlab.component;

import com.explodingpixels.macwidgets.SourceListCategory;
import ecompilerlab.service.impl.Platforms;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/2/13
 * Time: 3:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class ECompilerLibrarySourceListCategory extends SourceListCategory {

    private Platforms platform;

    public ECompilerLibrarySourceListCategory(String text,Platforms platform) {
        super(text);
        this.platform = platform;
    }

    public Platforms getPlatform() {
        return platform;
    }

    public void setPlatform(Platforms platform) {
        this.platform = platform;
    }
}
