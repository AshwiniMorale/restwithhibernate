package com.rest.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class[] getRootConfigClasses() {
		System.out.println("AppInitializer::getRootConfigClasses() called");
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class[] getServletConfigClasses() {
		System.out.println("AppInitializer::getServletConfigClasses() called");
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		System.out.println("AppInitializer::getServletMapping() called");
		return new String[] { "/" };
	}

}