package com.job.portal.jobapp.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface JobRepository extends JpaRepository<Job, Integer> {
	
}
