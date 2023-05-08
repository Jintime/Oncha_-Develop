package com.oncha.oncha_web.domain.payment.PaymentRepository;


import com.oncha.oncha_web.domain.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

}
