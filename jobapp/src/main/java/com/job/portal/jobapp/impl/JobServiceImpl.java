package com.job.portal.jobapp.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.job.portal.jobapp.Job;
import com.job.portal.jobapp.JobService;

@Service
public class JobServiceImpl implements JobService {

	List<Job> jobs=new ArrayList<>();
	@Override
	public void createJob(Job job) {
		this.jobs.add(job);		
	}

	@Override
	public List<Job> findAll() {	 
		return this.jobs;
	}

	@Override
	public Job findById(int id) {
		for(Job job: jobs) {
			if(job.getId().equals(id))
				return job;
		}
		return null;
	}
	public boolean deleteById(int id) {
		Iterator<Job> iterator=jobs.iterator();
		while(iterator.hasNext()) {
			Job job=iterator.next();
			if(job.getId().equals(id)) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean putJob(int id, Job updatedJob) {
		for(Job job:jobs) {
			if(job.getId().equals(updatedJob.getId())) {
				job.setDescription(updatedJob.getDescription());
				job.setId(updatedJob.getId());
				job.setLocation(updatedJob.getLocation());
				job.setMaxSalary(updatedJob.getMaxSalary());
				job.setMinSalary(updatedJob.getMinSalary());
				job.setTitle(updatedJob.getTitle());
			}
			return true;
		}
		return false;
	}

}
