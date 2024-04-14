package com.job.portal.jobapp.company;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.job.portal.jobapp.job.Job;
import com.job.portal.jobapp.reviews.Review;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String companyName;
	private String companyDesc;
	
	@JsonIgnore
	@OneToMany(mappedBy="company")
	private List<Job> jobs;
	@JsonIgnore
	@OneToMany(mappedBy="company")
	private List<Review> reviews;

	public Company() {
		super();
	}

	public Company(Integer companyId, String companyName, String companyDesc,List<Review> review) {
		super();
		this.id = companyId;
		this.companyName = companyName;
		this.companyDesc = companyDesc;
		this.reviews=reviews;
	}

	public Integer getCompanyId() {
		return id;
	}

	public void setCompanyId(Integer companyId) {
		this.id = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyDesc() {
		return companyDesc;
	}

	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

}
