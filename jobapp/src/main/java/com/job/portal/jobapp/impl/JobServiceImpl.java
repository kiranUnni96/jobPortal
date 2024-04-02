package com.job.portal.jobapp.impl;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.portal.jobapp.Job;
import com.job.portal.jobapp.JobRepository;
import com.job.portal.jobapp.JobService;

@Service
public class JobServiceImpl implements JobService {

	// List<Job> jobs=new ArrayList<>();
	@Autowired
	JobRepository repo;

	@Override
	public void createJob(Job job) {
		this.repo.save(job);
	}

	@Override
	public List<Job> findAll() {
		return this.repo.findAll();
	}

	@Override
	public Job findById(int id) {
		return this.repo.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));

	}

	public boolean deleteById(int id) {
		try {
			repo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean putJob(int id, Job updatedJob) {
		Optional<Job> jobOptional = repo.findById(id);
		if (jobOptional.isPresent()) {
			Job job = jobOptional.get();
			if (job.getId().equals(updatedJob.getId())) {
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
