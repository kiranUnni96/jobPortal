package com.job.portal.jobapp.company.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.portal.jobapp.company.Company;
import com.job.portal.jobapp.company.CompanyRepository;
import com.job.portal.jobapp.company.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepository repo;

	@Override
	public void createCompany(Company company) {
		this.repo.save(company);

	}

	@Override
	public List<Company> findAll() {
		return this.repo.findAll();
	}

	@Override
	public Company findById(int id) {
		return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Company with not found with id " + id));
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
	public boolean putCompany(int id, Company updatedCompany) {
		Optional<Company> companyOptional = repo.findById(id);
		if (companyOptional.isPresent()) {
			Company company = companyOptional.get();
			company.setCompanyDesc(updatedCompany.getCompanyDesc());
			company.setCompanyName(updatedCompany.getCompanyName());
			repo.save(company);
			return true;
		}
		return false;
	}

}
