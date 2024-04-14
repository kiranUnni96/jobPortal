package com.job.portal.jobapp.reviews;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

	@Autowired
	private ReviewService service;

	@GetMapping("/reviews")
	public ResponseEntity<List<Review>> listAllReviews(@PathVariable int companyId) {

		return new ResponseEntity(service.findByCompanyId(companyId), HttpStatus.OK);
	}

	@PostMapping("/reviews")
	public ResponseEntity addReview(@PathVariable int companyId, @RequestBody Review review) {
		boolean flag = service.createReview(companyId, review);
		if (flag)
			return new ResponseEntity(HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity updateReview(@PathVariable int reviewId, @RequestBody Review review) {
		Boolean flag=service.updateById(review,reviewId);
		if(flag)
				return new ResponseEntity<>("Review Updated", HttpStatus.OK);
		else
				return new ResponseEntity<>("Review Not found", HttpStatus.NOT_FOUND);		
		
	}
	
	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity deleteById(@PathVariable int reviewId) {
		Boolean flag=service.deleteById(reviewId);
		if(flag)
				return new ResponseEntity<>("Review Deleted",HttpStatus.OK);
		else
				return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
	}
	

}
