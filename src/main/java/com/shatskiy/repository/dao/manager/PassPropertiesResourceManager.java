package com.shatskiy.repository.dao.manager;

import java.util.ResourceBundle;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class PassPropertiesResourceManager {
	
	private static final String FILE_NAME = "folder";
	
	private static PassPropertiesResourceManager instance = null;
	private final ResourceBundle bundle = ResourceBundle.getBundle(FILE_NAME);
	
	private PassPropertiesResourceManager() {}

	public static PassPropertiesResourceManager getInstance() {
		if(instance == null){
			instance = new PassPropertiesResourceManager();
		}
		return instance;
	}
	
	public String getValue(String key){
		return bundle.getString(key);
	}
}
