package ecompilerlab.service;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 6/11/13
 * Time: 7:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class LibraryEntity {

    private String name;

    private Platforms platform;

    private String[] libNames;

    public String[] getLibNames() {
        return libNames;
    }

    public void setLibNames(String[] libNames) {
        this.libNames = libNames;
    }

    public Platforms getPlatform() {
        return platform;
    }

    public void setPlatform(Platforms platform) {
        this.platform = platform;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
