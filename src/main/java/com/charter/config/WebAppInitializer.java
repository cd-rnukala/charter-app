package com.charter.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {
	private static final PropertiesLoader propertiesLoader = new PropertiesLoader();

	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		Properties prop = propertiesLoader.load("application.properties");
		ctx.getEnvironment().setActiveProfiles(prop.getProperty("spring.profiles.active"));
		ctx.register(AppConfig.class);
		ctx.setServletContext(servletContext);
		Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		dynamic.addMapping("/");
		dynamic.setLoadOnStartup(1);
	}

}

class PropertiesLoader {

	public Properties load(String fileName) {
		Properties prop = new Properties();
		InputStream im = null;
		try {
			im = findFile(fileName);
			prop.load(im);
		} catch (IOException ignore) {
		} finally {
			if (im != null) {
				try {
					im.close();
				} catch (IOException ignore) {
				}
			}
		}
		return prop;
	}

	private InputStream findFile(String fileName) throws FileNotFoundException {
		InputStream im = findInWorkingDirectory(fileName);
		if (im == null)
			im = findInClasspath(fileName);
		if (im == null)
			im = findInSourceDirectory(fileName);
		if (im == null)
			throw new FileNotFoundException(String.format("File %s not found", fileName));
		return im;
	}

	private InputStream findInSourceDirectory(String fileName) throws FileNotFoundException {
		return new FileInputStream("src/main/resources/" + fileName);
	}

	private InputStream findInClasspath(String fileName) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
	}

	private InputStream findInWorkingDirectory(String fileName) {
		try {
			return new FileInputStream(System.getProperty("user.dir") + fileName);
		} catch (FileNotFoundException e) {
			return null;
		}
	}

}
