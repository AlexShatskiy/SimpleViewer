package com.shatskiy.repository.controller.command.helper;

import com.shatskiy.repository.dao.manager.PathPropertiesParameter;
import com.shatskiy.repository.dao.manager.PathPropertiesResourceManager;

import java.io.File;

public class PassHelper {
    /**
     * @param pass
     * @return
     * It determines the path of the root folder
     */
    public static String getRootPath(String pass) {

        String result;
        String passMakedFirst = null;

        PathPropertiesResourceManager resourceManager = PathPropertiesResourceManager.getInstance();
        String mainPass = resourceManager.getValue(PathPropertiesParameter.FOLDER_PATH);

        if (pass.startsWith(mainPass)) {

            int first = pass.lastIndexOf(File.separator);
            passMakedFirst = pass.substring(0, first);

            if (passMakedFirst.startsWith(mainPass)) {
                result = passMakedFirst;
            } else {
                result = mainPass;
            }
        } else {
            result = mainPass;
        }
        return result;
    }
}
