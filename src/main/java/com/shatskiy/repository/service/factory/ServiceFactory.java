package com.shatskiy.repository.service.factory;

import com.shatskiy.repository.service.ServiceJavaExplorer;
import com.shatskiy.repository.service.impl.ServiceJavaExplorerImpl;


/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class ServiceFactory {
	private final static ServiceFactory instance = new ServiceFactory();
	
	private ServiceJavaExplorer serviceJavaExplorer = new ServiceJavaExplorerImpl();
	
	public static ServiceFactory getInstance(){
		return instance;
	}

	public ServiceJavaExplorer getServiceJavaExplorer() {
		return serviceJavaExplorer;
	}
}
