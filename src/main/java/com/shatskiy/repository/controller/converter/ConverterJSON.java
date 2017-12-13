package com.shatskiy.repository.controller.converter;

import com.shatskiy.repository.model.FileModelPOJO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Set;

public class ConverterJSON {

    public static String convertSetToStringJSON(Set<FileModelPOJO> set){

        String result;
        JSONArray arrayObj=new JSONArray();

        putInJSONArray(set, arrayObj);

        result = arrayObj.toString();

        return result;
    }

    public static String convertSetToStringJSON(Set<FileModelPOJO> setFolder, Set<FileModelPOJO> setFile){

        String result;
        JSONArray arrayObj=new JSONArray();

        putInJSONArray(setFolder, arrayObj);
        putInJSONArray(setFile, arrayObj);

        result = arrayObj.toString();

        return result;
    }

    private static JSONArray putInJSONArray(Set<FileModelPOJO> set, JSONArray arrayObj){

        for (FileModelPOJO model : set){
            JSONObject object = new JSONObject();

            object.put("fileName", model.getFileName());
            object.put("isDirectory", model.getDirectory());
            object.put("fullPath", model.getFullPath());
            object.put("parentFileName", model.getParentFileName());
            object.put("sizeModel", model.getSize());

            arrayObj.put(object);
        }

        return arrayObj;
    }
}
