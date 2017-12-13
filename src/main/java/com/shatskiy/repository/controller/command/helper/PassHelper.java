package com.shatskiy.repository.controller.command.helper;

import com.shatskiy.repository.dao.manager.PassPropertiesParameter;
import com.shatskiy.repository.dao.manager.PassPropertiesResourceManager;

public class PassHelper {
    /**
     * @param pass
     * @return It determines the path of the root folder
     */
    public static String getRootPath(String pass) {

        String result;
        String passMakedFirst = null;

        PassPropertiesResourceManager resourceManager = PassPropertiesResourceManager.getInstance();
        String mainPass = resourceManager.getValue(PassPropertiesParameter.FOLDER_PATH);

        if (pass.startsWith(mainPass)) {
            int first = pass.lastIndexOf("\\");
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
