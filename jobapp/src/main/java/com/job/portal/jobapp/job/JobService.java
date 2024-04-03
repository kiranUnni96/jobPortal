package com.job.portal.jobapp.job;

import java.util.List;

public interface JobService {
	
	public void createJob(Job job);
	public List<Job> findAll();
	public Job findById(int id);
	//public void updateJob()
	public boolean deleteById(int id);
	public boolean putJob(int id, Job job);
	
}
