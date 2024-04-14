package com.job.portal.jobapp.job.impl;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.portal.jobapp.company.Company;
import com.job.portal.jobapp.company.CompanyService;
import com.job.portal.jobapp.job.Job;
import com.job.portal.jobapp.job.JobRepository;
import com.job.portal.jobapp.job.JobService;

@Service
public class JobServiceImpl implements JobService {

	// List<Job> jobs=new ArrayList<>();
	@Autowired
	JobRepository repo;

	@Autowired
	CompanyService companyService;

	@Override
	public boolean createJob(Job job) {
		Company company = companyService.findById(job.getCompany().getCompanyId());
		if (company != null) {
			job.setCompany(company);
			this.repo.save(job);
			return true;
		} else
			return false;
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

			job.setDescription(updatedJob.getDescription());
			job.setId(updatedJob.getId());
			job.setLocation(updatedJob.getLocation());
			job.setMaxSalary(updatedJob.getMaxSalary());
			job.setMinSalary(updatedJob.getMinSalary());
			job.setTitle(updatedJob.getTitle());
			repo.save(job);

			return true;
		}

		return false;
	}

}
