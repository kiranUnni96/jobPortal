package com.job.portal.jobapp.reviews.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.portal.jobapp.company.Company;
import com.job.portal.jobapp.company.CompanyService;
import com.job.portal.jobapp.job.Job;
import com.job.portal.jobapp.reviews.Review;
import com.job.portal.jobapp.reviews.ReviewRepository;
import com.job.portal.jobapp.reviews.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepository repo;

	@Autowired
	CompanyService companyService;

	@Override
	public boolean createReview(int companyId, Review review) {
		Company company = companyService.findById(companyId);
		if (company != null) {
			review.setCompany(company);
			repo.save(review);
			return true;
		} else
			return false;

	}

	/*
	 * @Override public List<Review> findAll() {
	 * 
	 * return repo.findAll(); }
	 */

	@Override
	public List<Review> findByCompanyId(int id) {
		return repo.findByCompanyId(id);
	}

	@Override
	public boolean deleteById(int id) {
		try {
			repo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateById(Review updatedReview, int reviewId) {
		Optional<Review> reviewOptional = repo.findById(reviewId);
		if (reviewOptional.isPresent()) {
			Review review = reviewOptional.get();
			review.setReviewDesc(updatedReview.getReviewDesc());
			review.setId(updatedReview.getId());
			review.setTitle(updatedReview.getTitle());
			review.setRating(updatedReview.getRating());
			//review.setCompany(updatedReview.getCompany());
			repo.save(review);

			return true;
		}

		return false;

	}

	@Override
	public Review findById(int id, Review review) {
		Review temp=repo.findById(id).orElse(null);
		return null;
		
	}

}
