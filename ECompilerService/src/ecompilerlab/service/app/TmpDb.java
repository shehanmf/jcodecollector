package ecompilerlab.service.app;

import ecompilerlab.service.impl.LibraryEntity;
import ecompilerlab.service.impl.Platforms;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/7/13
 * Time: 7:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class TmpDb
{

  private static ArrayList<LibraryEntity> list = new ArrayList<LibraryEntity>();

  static
  {


    list.add(new LibraryEntity(1, "Apache Common Math", Platforms.JAVA, false, "\\common-math",
      new String[]{"\\common-math\\commons-math3-3.2.jar"}));
    list.add(new LibraryEntity(2, "Apache Common Lang", Platforms.JAVA, false, "\\common-lang",
      new String[]{"\\common-lang\\commons-lang3-3.1.jar", "\\common-lang\\commons-io-2.4.jar"}));
    list.add(new LibraryEntity(3, "Apache Common IO", Platforms.JAVA, false, "\\common-io",
      new String[]{"\\common-io\\commons-io-2.4.jar"}));
    list.add(new LibraryEntity(4, "Google-guava", Platforms.JAVA, false, "\\google-guava",
      new String[]{"\\google-guava\\guava-14.0.1.jar"}));
    list.add(
      new LibraryEntity(5, "Google-Gson", Platforms.JAVA, false, "\\gson", new String[]{"\\gson\\gson-2.2.4.jar"}));
    list.add(
      new LibraryEntity(6, "Joda Time", Platforms.JAVA, false, "\\joda", new String[]{"\\joda\\joda-time-2.2.jar"}));
    list.add(new LibraryEntity(7, "JDom", Platforms.JAVA, false, "\\jdom",
      new String[]{"\\jdom\\jdom-1.1.3.jar", "\\jdom\\jaxen.jar", "\\jdom\\xalan.jar", "\\jdom\\xerces.jar",
        "\\jdom\\xml-apis.jar", "\\jdom\\ant.jar"}));


//    list.add(new LibraryEntity(2, "DOM", Platforms.JAVA, false, null));
//    list.add(new LibraryEntity(3, "Jasper", Platforms.JAVA, false, null));
//    list.add(new LibraryEntity(4, "XMLBeans", Platforms.JAVA, false, null));
//    list.add(new LibraryEntity(5, "Axiom", Platforms.JAVA, false, null));
//    list.add(new LibraryEntity(6, "Standard Function Library", Platforms.C, false, null));
//    list.add(new LibraryEntity(7, "GLib", Platforms.C, false, null));
//    list.add(new LibraryEntity(8, "Reason", Platforms.C_PLUS, false, null));
//    list.add(new LibraryEntity(9, "Loki", Platforms.C_PLUS, false, null));
//    list.add(new LibraryEntity(10, "Boost ", Platforms.C_PLUS, false, null));
//        list.add(new LibraryEntity(11,"Axis 2",Platforms.JAVA,false,null));

  }


  public static ArrayList<LibraryEntity> getAllLibraries()
  {
    return list;
  }


  public static ArrayList<LibraryEntity> getLibrariesByID(String[] ids)
  {
    ArrayList<LibraryEntity> tmpList = new ArrayList<LibraryEntity>();
    if (ids != null && ids.length > 0)
    {
      for (String id : ids)
      {
        for (LibraryEntity entity : list)
        {
          if (entity.getId() == Integer.parseInt(id))
          {
            tmpList.add(entity);
            break;
          }
        }
      }
    }
    return tmpList;
  }
}
