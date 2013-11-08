package com.aot.aotmiddletier.test;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(locations = { "classpath:config/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestAotMiddleTier {
	
	private final Logger log = Logger.getLogger(this.getClass());

	
//	@Resource
	private JobLauncher jobLauncher;
	
//	@Resource
	private Job job;
	
//	@Test
	public void findPath() {
		log.debug("user.home: " + System.getProperty("user.home"));
		log.debug("user.dir: " + System.getProperty("user.dir"));
	}
	
	@Test
	public void testAotMiddleTier() {

	}
	
}