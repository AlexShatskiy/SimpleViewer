package com.shatskiy.repository.dao.manager;

import java.util.ResourceBundle;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class PathPropertiesResourceManager {
	
	private static final String FILE_NAME = "folder";
	
	private static PathPropertiesResourceManager instance = null;
	private final ResourceBundle bundle = ResourceBundle.getBundle(FILE_NAME);
	
	private PathPropertiesResourceManager() {}

	public static PathPropertiesResourceManager getInstance() {
		if(instance == null){
			instance = new PathPropertiesResourceManager();
		}
		return instance;
	}
	
	public String getValue(String key){
		return bundle.getString(key);
	}
}
