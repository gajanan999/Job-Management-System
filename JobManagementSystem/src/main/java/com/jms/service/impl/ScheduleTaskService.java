package com.jms.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.jms.config.Configuration;
import com.jms.constants.Constants;
import com.jms.jobs.Job;

@Service
public class ScheduleTaskService {

	// Task Scheduler
	//TaskScheduler scheduler;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	TaskScheduler taskScheduler;

	// A map for keeping scheduled tasks
	Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

	public ScheduleTaskService() {
		//this.scheduler = scheduler;
	}

	// Schedule Task to be executed every night at 00 or 12 am
	public void addTaskToScheduler(String id, Runnable task,String cronExpression) {
		ScheduledFuture<?> scheduledTask = taskScheduler.schedule(task,
				new CronTrigger(cronExpression, TimeZone.getTimeZone(TimeZone.getDefault().getID())));
		jobsMap.put(id, scheduledTask);
	}

	// Remove scheduled task
	public void removeTaskFromScheduler(String id) {
		ScheduledFuture<?> scheduledTask = jobsMap.get(id);
		if (scheduledTask != null) {
			scheduledTask.cancel(true);
			jobsMap.put(id, null);
		}
	}

	// A context refresh event listener
	@EventListener({ ContextRefreshedEvent.class })
	void contextRefreshedEvent() {
		// Get all tasks from DB and reschedule them in case of context restarted
	}

	public void scheduleAllJobs() {
		Configuration configuration = context.getBean(Configuration.class);
		
		Map<String,Job> jobs=configuration.getJobs();
		for(Map.Entry<String, Job> entry:jobs.entrySet()) {
			Job job= entry.getValue();
			if(null != job.getSchedule()) {
				job.setStatus(Constants.QUEUED);
				addTaskToScheduler(job.getName(), job, job.getSchedule());
			}
		}
		
	}
}
