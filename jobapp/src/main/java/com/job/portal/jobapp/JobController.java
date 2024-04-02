package com.job.portal.jobapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.job.portal.jobapp.impl.JobServiceImpl;

@RestController
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	private Integer nextId=0;
	
	/*
	 * public JobController(JobService jobService) { super(); this.jobService =
	 * jobService; }
	 */

	@GetMapping("/jobs")
	public ResponseEntity<List<Job>> findAll(){
		List<Job> jobs=jobService.findAll();
		return new ResponseEntity<>(jobs,HttpStatus.OK);
	}
	
	@PostMapping("/jobs")
	public ResponseEntity createJob(@RequestBody Job job) {
		job.setId(++nextId);
		jobService.createJob(job);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping("jobs/{id}")
	public ResponseEntity<Job> findById(@PathVariable Integer id) {
		Job job=jobService.findById(id);
		if (job!=null)
			return new ResponseEntity(job,HttpStatus.OK);
		else return new ResponseEntity(job,HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("jobs/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Integer id) {
		boolean flag=jobService.deleteById(id);
		if(flag)
			return new ResponseEntity<>("Job successfully deleted", HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("jobs/{id}")
	public ResponseEntity<String> updateJob(@PathVariable Integer id, @RequestBody Job job) {
		
		boolean Flag=jobService.putJob(id, job);
		if(Flag) {
			return new ResponseEntity<>("Job successfully updated", HttpStatus.OK); 
		}
		else return new ResponseEntity<>("Job Not found", HttpStatus.NOT_FOUND); 	
		
	}
	
	
}
