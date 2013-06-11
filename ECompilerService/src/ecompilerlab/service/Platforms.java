package ecompilerlab.service;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 6/11/13
 * Time: 7:37 AM
 * To change this template use File | Settings | File Templates.
 */
public enum Platforms {
    JAVA("Java"),
    C("C"),
    C_PLUS("C++"),
    C_SHARP("C#"),
    PYTHON("Python");


    private String value;

    Platforms(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return this.value;
    }
}
