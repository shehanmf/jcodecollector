package ecompilerlab.service;

import ecompilerlab.clientstub.ClassLookUpResponse;
import ecompilerlab.clientstub.CompileRequest;
import ecompilerlab.clientstub.CompileResponse;
import ecompilerlab.clientstub.CompileResponseState;
import ecompilerlab.clientstub.ECompilerService;
import ecompilerlab.clientstub.LibraryEntity;
import ecompilerlab.clientstub.LibraryType;
import ecompilerlab.clientstub.Platforms;
import ecompilerlab.clientstub.PlatformsInfo;
import ecompilerlab.clientstub.ResourceLookUpEntry;
import ecompilerlab.component.CompilerDataProvideListener;
import ecompilerlab.component.TextEntry;
import ecompilerlab.service.app.CompileFacade;
import ecompilerlab.service.app.LibraryFacade;
import ecompilerlab.service.app.PlatformFacade;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 6/13/13
 * Time: 8:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebServiceClientImpl implements WebServiceClient
{

  private ecompilerlab.clientstub.ECompilerService port;

  private static WebServiceClient instance;

  private CompilerDataProvideListener dataProvider;

  private boolean connectSuccess;

  private boolean localService = true;

  public static WebServiceClient getInstance()
  {
    if (instance == null)
    {
      instance = new WebServiceClientImpl();
    }
    return instance;
  }

  public WebServiceClientImpl()
  {

  }

  private void notifyServerNotConnected()
  {
    dataProvider.notifyPerformed(new TextEntry("Server Not Connected", true, TextEntry.ENTRY_TYPE.ERROR));
  }


  @Override
  public Platforms[] getSupportedPlatforms()
  {
    if (!connectSuccess)
    {
      return null;
    }
    final List<Platforms> supportedPlatforms = port.getSupportedPlatforms();
    return supportedPlatforms.toArray(new Platforms[]{});
  }

  public LibraryEntity[] getAllAvailableLibraries()
  {
    final List<LibraryEntity> allAvailableLibraries = port.getAllAvailableLibraries();
    return allAvailableLibraries.toArray(new LibraryEntity[]{});
  }

  public CompileResponse doCompile(CompileRequest request)
  {
    if (!connectSuccess)
    {
      notifyServerNotConnected();
      return null;
    }
    return port.doCompile(request);
  }

  public PlatformsInfo getInfoByPlatform(Platforms platforms)
  {
    return port.getInfoByPlatform(platforms);
  }

  @Override
  public LibraryEntity[] getLibrariesByID(String[] ids)
  {
    final List<LibraryEntity> librariesByID = port.getLibrariesByID(Arrays.asList(ids));
    return librariesByID.toArray(new LibraryEntity[]{});
  }

  public void setDataProvider(CompilerDataProvideListener dataProvider)
  {
    this.dataProvider = dataProvider;
  }

  @Override
  public ResourceLookUpEntry[] classLookUp(String className, Platforms platforms, String[] libIds)
  {
    final List<ResourceLookUpEntry> resourceLookUpEntries = port.classLookUp(className, platforms,
      Arrays.asList(libIds));
    return resourceLookUpEntries.toArray(new ResourceLookUpEntry[]{});
  }

  @Override
  public void doConnect()
  {
    if (localService)
    {
      connectSuccess = true;
      port = new ECompilerService()
      {
        @Override
        public CompileResponse doCompile(@WebParam(name = "request", targetNamespace = "") CompileRequest request)
        {
          return convert(CompileFacade.getInstance().doCompile(convert(request)));
        }

        @Override
        public PlatformsInfo getInfoByPlatform(@WebParam(name = "platforms", targetNamespace = "") Platforms platforms)
        {
          return convert(PlatformFacade.getInstance().getInfoByPlatform(convert(platforms)));
        }

        @Override
        public List<LibraryEntity> getAllAvailableLibraries()
        {
          return convert(Arrays.asList(LibraryFacade.getInstance().getAllLibraries()));
        }

        @Override
        public List<LibraryEntity> getLibrariesByID(@WebParam(name = "ids", targetNamespace = "") List<String> ids)
        {
          return convert(Arrays.asList(LibraryFacade.getInstance().getLibrariesByID(ids.toArray(new String[]{}))));
        }

        @Override
        public List<Platforms> getSupportedPlatforms()
        {
          return Arrays.asList(Platforms.values());
        }

        @Override
        public List<LibraryType> getSupportedLibraryTypes()
        {
          return Arrays.asList(LibraryType.values());
        }

        @Override
        public List<ResourceLookUpEntry> classLookUp(String className,Platforms platforms,List<String> libraryIDs)
        {
          final List<LibraryEntity> librariesByID = getLibrariesByID(libraryIDs);
          final List<ecompilerlab.service.impl.LibraryEntity> libraryEntities = convertLib(librariesByID);

          return convert(Arrays.asList(LibraryFacade.getInstance().classLookUp(className, convert(platforms),
            libraryEntities.toArray(new ecompilerlab.service.impl.LibraryEntity[]{}))), false);

        }
      };

      return;
    }
    try
    {
      port = new ecompilerlab.clientstub.ECompilerService_Service().getECompilerServicePort();
      connectSuccess = true;
    }
    catch (Exception e)
    {
      notifyServerNotConnected();

    }
  }


  public PlatformsInfo convert(ecompilerlab.service.impl.PlatformsInfo platformsInfo)
  {
    PlatformsInfo info = new PlatformsInfo();
    info.setVersionInfo(platformsInfo.getVersionInfo());
    info.setPlatform(convert(platformsInfo.getPlatform()));
    return info;
  }

  public CompileResponse convert(ecompilerlab.service.impl.CompileResponse response)
  {
    CompileResponse compileResponse = new CompileResponse();

    compileResponse.setResultString(response.getResultString());
    compileResponse.setState(convert(response.getState()));
    return compileResponse;
  }


  public CompileResponseState convert(ecompilerlab.service.impl.CompileResponseState compileResponseState)
  {
    switch (compileResponseState)
    {
      case SUCCESS:
        return CompileResponseState.SUCCESS;

      case COMPILE_ERROR:
        return CompileResponseState.COMPILE_ERROR;

      case OTHER_ERROR:
        return CompileResponseState.OTHER_ERROR;

      case RUNTIME_ERROR:
        return CompileResponseState.RUNTIME_ERROR;

      default:
        return null;
    }
  }

  public ecompilerlab.service.impl.CompileResponse convert(CompileResponse compileResponse)
  {
    return new ecompilerlab.service.impl.CompileResponse(convert(compileResponse.getState()),
      compileResponse.getResultString());
  }

  public ecompilerlab.service.impl.CompileRequest convert(CompileRequest compileRequest)
  {

    return new ecompilerlab.service.impl.CompileRequest(
      compileRequest.getCode(), convert(compileRequest.getPlatform()),
      compileRequest.getLibraryIDs().toArray(new String[]{}),
      compileRequest.isCompileOnly());
  }


  public ecompilerlab.service.impl.CompileResponseState convert(CompileResponseState responseState)
  {
    switch (responseState)
    {
      case SUCCESS:
        return ecompilerlab.service.impl.CompileResponseState.SUCCESS;

      case COMPILE_ERROR:
        return ecompilerlab.service.impl.CompileResponseState.COMPILE_ERROR;

      case OTHER_ERROR:
        return ecompilerlab.service.impl.CompileResponseState.OTHER_ERROR;

      case RUNTIME_ERROR:
        return ecompilerlab.service.impl.CompileResponseState.RUNTIME_ERROR;

      default:
        return null;
    }
  }


  public ecompilerlab.service.impl.Platforms convert(Platforms platforms)
  {
    switch (platforms)
    {
      case JAVA:
        return ecompilerlab.service.impl.Platforms.JAVA;
      case C:
        return ecompilerlab.service.impl.Platforms.C;
      case C_PLUS:
        return ecompilerlab.service.impl.Platforms.C_PLUS;
      default:
        return null;

    }
  }


  public List<ResourceLookUpEntry> convert(List<ecompilerlab.service.impl.ResourceLookUpEntry> resourceEntity,
                                           boolean toUnique)
  {
    List<ResourceLookUpEntry> entities = new ArrayList<ResourceLookUpEntry>();
    for (ecompilerlab.service.impl.ResourceLookUpEntry entity : resourceEntity)
    {
      entities.add(convert(entity));
    }

    return entities;

  }


  public List<ecompilerlab.service.impl.LibraryEntity> convertLib(List<LibraryEntity> libraryEntity)
  {
    List<ecompilerlab.service.impl.LibraryEntity> entities = new ArrayList<ecompilerlab.service.impl.LibraryEntity>();
    for (LibraryEntity entity : libraryEntity)
    {
      entities.add(convertLib(entity));
    }

    return entities;
  }

  public ecompilerlab.service.impl.LibraryEntity convertLib(LibraryEntity libraryEntity)
  {
    ecompilerlab.service.impl.LibraryEntity entity = new ecompilerlab.service.impl.LibraryEntity();
    entity.setId(libraryEntity.getId());
    entity.setName(libraryEntity.getName());
    entity.setBaseLibPath(libraryEntity.getBaseLibPath());
    entity.setFromCloud(libraryEntity.isFromCloud());
    entity.setPlatform(convert(libraryEntity.getPlatform()));
    entity.setLibNames(libraryEntity.getLibNames().toArray(new String[]{}));
    return entity;
  }


  public List<LibraryEntity> convert(List<ecompilerlab.service.impl.LibraryEntity> libraryEntity)
  {
    List<LibraryEntity> entities = new ArrayList<LibraryEntity>();
    for (ecompilerlab.service.impl.LibraryEntity entity : libraryEntity)
    {
      entities.add(convert(entity));
    }

    return entities;
  }

  public LibraryEntity convert(ecompilerlab.service.impl.LibraryEntity libraryEntity)
  {
    LibraryEntity entity = new LibraryEntity();
    entity.setId(libraryEntity.getId());
    entity.setName(libraryEntity.getName());
    entity.setBaseLibPath(libraryEntity.getBaseLibPath());
    entity.setFromCloud(libraryEntity.isFromCloud());
    entity.setPlatform(convert(libraryEntity.getPlatform()));
    entity.getLibNames().addAll(Arrays.asList(libraryEntity.getLibNames()));
    return entity;
  }


  public ResourceLookUpEntry convert(ecompilerlab.service.impl.ResourceLookUpEntry resourceEntity)
  {
    ResourceLookUpEntry entity = new ResourceLookUpEntry();
    entity.setLibraryName(resourceEntity.getLibraryName());
    entity.setFullClassName(resourceEntity.getFullClassName());
    return entity;
  }


  public Platforms convert(ecompilerlab.service.impl.Platforms platforms)
  {
    switch (platforms)
    {
      case JAVA:
        return Platforms.JAVA;
      case C:
        return Platforms.C;
      case C_PLUS:
        return Platforms.C_PLUS;
      default:
        return null;

    }
  }


}
