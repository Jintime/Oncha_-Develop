package com.oncha.oncha_web.feature.payment.repository;

import com.oncha.oncha_web.domain.payment.model.OnchaPayment;
import com.oncha.oncha_web.feature.payment.model.OnchaPaymentDTO;
import com.oncha.oncha_web.util.Querydsl4RepositorySupport;
import com.querydsl.core.types.Projections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import static com.oncha.oncha_web.domain.payment.model.QOnchaPayment.onchaPayment;

@Repository
public class PaymentQueryRepository extends Querydsl4RepositorySupport {
    public PaymentQueryRepository() {
        super(OnchaPayment.class);
    }

    public Page<OnchaPaymentDTO> findAllByPageable(Pageable pageable) {
        return applyPagination(pageable, query -> select(Projections.constructor(
                OnchaPaymentDTO.class,
                onchaPayment.id,
                onchaPayment.impUid,
                onchaPayment.payment_price,
                onchaPayment.payment_status,
                onchaPayment.buyer_name,
                onchaPayment.buyer_email
        )));
    }


    public OnchaPaymentDTO findById(long id) {
        return select(Projections.constructor(
                OnchaPaymentDTO.class,
                onchaPayment.id,
                onchaPayment.impUid,
                onchaPayment.payment_price,
                onchaPayment.payment_status,
                onchaPayment.buyer_name,
                onchaPayment.buyer_email
        ))
                .from(onchaPayment)
                .where(onchaPayment.id.eq(id))
                .fetchOne();

    }
}
