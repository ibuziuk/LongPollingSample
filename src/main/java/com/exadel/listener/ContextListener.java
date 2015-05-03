package com.exadel.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
//		Timer time = new Timer(); 
//		ScheduledTask st = new ScheduledTask(); 
//		time.schedule(st, 0, 5000);
	}

	public void contextDestroyed(ServletContextEvent event) {

	}

}
