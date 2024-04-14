package com.job.portal.jobapp.company;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.job.portal.jobapp.job.Job;
import com.job.portal.jobapp.job.JobService;

@RestController
@RequestMapping("/companies")
public class CompanyController {

	@Autowired
	CompanyService service;
	@Autowired
	JobService jobService;

	@GetMapping
	public ResponseEntity<List<Company>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Company> findById(@PathVariable Integer id) {
		Company company = service.findById(id);
		if (company != null)
			return new ResponseEntity<>(company, HttpStatus.OK);
		else
			return new ResponseEntity<>(company, HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity createCompany(@RequestBody Company company) {
		service.createCompany(company);
		return new ResponseEntity(HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity updateCompany(@PathVariable int id, @RequestBody Company company) {
		Boolean flag = service.putCompany(id, company);
		if (flag)
			return new ResponseEntity(HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteCompany(@PathVariable int id) {				
		List<Job> jobs=service.findById(id).getJobs();
		for(Job job: jobs) {
			jobService.deleteById(job.getId());
		}
		Boolean flag = service.deleteById(id);
		if (flag)
			return new ResponseEntity(HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NOT_FOUND);

	}

}
