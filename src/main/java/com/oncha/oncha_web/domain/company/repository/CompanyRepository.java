package com.oncha.oncha_web.domain.company.repository;

import com.oncha.oncha_web.domain.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
