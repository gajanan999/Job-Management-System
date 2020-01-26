# Job-Management-System

## Job 
   It implements a runnable interface so that we can implement anything operation under it
   package com.jms.jobs;

public class Job implements Runnable{

	private String name;
	private String schedule;
	private String className;
	private String status;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}



## Configuration Class
   It is class which stores the Map of all the jobs
   Map<String,Job> jobs=new HashMap<>();

## ScheduleTaskService
   It is service which manages all the job scheduling.

## To Check Status of all the jobs
   http://localhost:8080/getAllJobStatus
