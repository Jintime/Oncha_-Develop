package com.oncha.oncha_web.domain.payment.repository;


import com.oncha.oncha_web.domain.payment.model.OnchaPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnchaPaymentRepository extends JpaRepository<OnchaPayment,Long> {

}
