package com.shatskiy.repository.controller.converter;

import com.shatskiy.repository.model.FileModelPOJO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Set;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class ConverterJSON {

    /**
     * convert two sets to one JSON string
     * @param setFolder set of folders
     * @param setFile set of files
     * @return JSON String
     */
    public static String convertSetToStringJSON(Set<FileModelPOJO> setFolder, Set<FileModelPOJO> setFile){

        String result;
        JSONArray arrayObj=new JSONArray();

        putInJSONArray(setFolder, arrayObj);
        putInJSONArray(setFile, arrayObj);

        result = arrayObj.toString();

        return result;
    }

    /**
     * It put set to JSON array
     * @param set
     * @param arrayObj JSON array
     * @return put JSON array
     */
    private static JSONArray putInJSONArray(Set<FileModelPOJO> set, JSONArray arrayObj){

        for (FileModelPOJO model : set){
            JSONObject object = new JSONObject();

            object.put("fileName", model.getFileName());
            object.put("isDirectory", model.getDirectory());
            object.put("parentFileName", model.getParentFileName());
            object.put("sizeModel", model.getSize());

            arrayObj.put(object);
        }
        return arrayObj;
    }
}
