package com.job.portal.jobapp.company;

import java.util.List;

public interface CompanyService {

	public void createCompany(Company company);

	public List<Company> findAll();

	public Company findById(int id);

	public boolean deleteById(int id);

	public boolean putCompany(int id, Company company);
}
