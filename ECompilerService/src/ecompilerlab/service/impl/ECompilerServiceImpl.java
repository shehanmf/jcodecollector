package ecompilerlab.service.impl;

import ecompilerlab.service.ECompilerService;
import ecompilerlab.service.app.CompileFacade;
import ecompilerlab.service.app.PlatformFacade;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 6/11/13
 * Time: 7:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class ECompilerServiceImpl implements ECompilerService
{

  @Override
  public Platforms[] getSupportedPlatforms()
  {
    return Platforms.values();
  }

  @Override
  public LibraryEntity[] getAllAvailableLibraries()
  {

    return getSampleData();
  }

  @Override
  public CompileResult doCompile(CompileRequest request)
  {
    return CompileFacade.getInstance().doCompile(request);
  }

  @Override
  public PlatformsInfo getInfoByPlatform(Platforms platforms)
  {
    return PlatformFacade.getInstance().getInfoByPlatform(platforms);
  }

  @Override
  public LibraryEntity[] getLibrariesByID(String[] ids)
  {
    return new LibraryEntity[]{new LibraryEntity(1, "Axis 2", Platforms.JAVA, false, null),
      new LibraryEntity(5, "Axiom", Platforms.JAVA, false, null)};
  }

  private LibraryEntity[] getSampleData()
  {
    ArrayList<LibraryEntity> list = new ArrayList<LibraryEntity>();
    list.add(new LibraryEntity(1, "Axis 2", Platforms.JAVA, false, null));
    list.add(new LibraryEntity(2, "DOM", Platforms.JAVA, false, null));
    list.add(new LibraryEntity(3, "Jasper", Platforms.JAVA, false, null));
    list.add(new LibraryEntity(4, "XMLBeans", Platforms.JAVA, false, null));
    list.add(new LibraryEntity(5, "Axiom", Platforms.JAVA, false, null));
    list.add(new LibraryEntity(6, "Standard Function Library", Platforms.C, false, null));
    list.add(new LibraryEntity(7, "GLib", Platforms.C, false, null));
    list.add(new LibraryEntity(8, "Reason", Platforms.C_PLUS, false, null));
    list.add(new LibraryEntity(9, "Loki", Platforms.C_PLUS, false, null));
    list.add(new LibraryEntity(10, "Boost ", Platforms.C_PLUS, false, null));
//        list.add(new LibraryEntity(11,"Axis 2",Platforms.JAVA,false,null));

    return list.toArray(new LibraryEntity[]{});

//        avlList.getModel().addItemToCategory(new SourceListItem("Axis 2"),category);
//        avlList.getModel().addItemToCategory(new SourceListItem("DOM"),category);
//        avlList.getModel().addItemToCategory(new SourceListItem("Jasper"),category);
//        avlList.getModel().addItemToCategory(new SourceListItem("XMLBeans"),category);
//        avlList.getModel().addItemToCategory(new SourceListItem("Java2PDF"),category);
//        avlList.getModel().addItemToCategory(new SourceListItem("HTTPS"),category);
//        avlList.getModel().addItemToCategory(new SourceListItem("Axiom"),category);
  }

}
