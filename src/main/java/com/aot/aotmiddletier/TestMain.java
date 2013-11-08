package com.aot.aotmiddletier;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.aot.aotmiddletier.service.OptionSearchService;

public class TestMain {

	private static String[] CONFIG_FILES = { "config/applicationContext.xml" };
	private static OptionSearchService optionSearchService;

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				CONFIG_FILES);
		optionSearchService = (OptionSearchService) ctx
				.getBean("optionSearchService");

		optionSearchService.doSearch();

		// Test from Alex
	}
}
