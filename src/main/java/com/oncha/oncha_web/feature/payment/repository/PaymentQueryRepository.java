package com.oncha.oncha_web.feature.payment.repository;

import com.oncha.oncha_web.domain.payment.model.Payment;
import com.oncha.oncha_web.feature.payment.model.PaymentDTO;
import com.oncha.oncha_web.util.Querydsl4RepositorySupport;
import com.querydsl.core.types.Projections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import static com.oncha.oncha_web.domain.payment.model.QPayment.payment;

@Repository
public class PaymentQueryRepository extends Querydsl4RepositorySupport {
    public PaymentQueryRepository() {
        super(Payment.class);
    }

    public Page<PaymentDTO> findAllByPageable(Pageable pageable) {
        return applyPagination(pageable, query -> select(Projections.constructor(
                PaymentDTO.class,
                payment.id,
                payment.impUid,
                payment.payment_price,
                payment.payment_status,
                payment.buyer_name,
                payment.buyer_email
        )));
    }


    public PaymentDTO findById(long id) {
        return select(Projections.constructor(
                PaymentDTO.class,
                payment.id,
                payment.impUid,
                payment.payment_price,
                payment.payment_status,
                payment.buyer_name,
                payment.buyer_email
        ))
                .from(payment)
                .where(payment.id.eq(id))
                .fetchOne();

    }
}
