package com.job.portal.jobapp.reviews;

import java.util.List;

public interface ReviewService {

	public boolean createReview(int companyId,Review review);

	//public List<Review> findAll();

	public List<Review> findByCompanyId(int id);
	
	public Review findById(int id, Review review);

	public boolean deleteById(int id);

	public boolean updateById(Review review, int reviewId);

}
