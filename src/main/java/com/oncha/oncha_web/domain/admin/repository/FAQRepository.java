package com.oncha.oncha_web.domain.admin.repository;

import com.oncha.oncha_web.domain.admin.model.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FAQRepository extends JpaRepository<FAQ,Long> {
}
