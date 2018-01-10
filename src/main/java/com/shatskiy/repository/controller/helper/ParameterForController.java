package com.shatskiy.repository.controller.helper;

import com.shatskiy.repository.dao.manager.PathPropertiesParameter;
import com.shatskiy.repository.dao.manager.PathPropertiesResourceManager;

/**
 * static parameter for controller
 * @author Shatskiy Alex
 * @version 1.0
 */
public final class ParameterForController {

    private ParameterForController(){}

    public static String SINGLE_PAGE = "index";
    public static String ROOT_PATH = PathPropertiesResourceManager.getInstance().getValue(PathPropertiesParameter.FOLDER_PATH);
    public static String PATH_FOLDER = "pathFolder";
    public static String NAME_FOLDER = "nameFolder";
    //This is necessary to determine true path(newPathFolder)
    public static String SWITCH_PATH = "switchPath";
    public static String FILE_NAME = "fileName";

}
