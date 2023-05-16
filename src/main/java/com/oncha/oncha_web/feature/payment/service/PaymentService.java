package com.oncha.oncha_web.feature.payment.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.oncha.oncha_web.domain.payment.PaymentRepository.PaymentRepository;
import com.oncha.oncha_web.domain.payment.model.Payment;
import com.oncha.oncha_web.domain.payment.model.RequsetPayment;
import com.oncha.oncha_web.feature.payment.model.PaymentDTO;
import com.oncha.oncha_web.feature.payment.model.PaymentRequest;
import com.oncha.oncha_web.feature.payment.repository.PaymentQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentQueryRepository paymentQueryRepository;

    // 결제 정보를 저장하는 메서드
    public void save(PaymentRequest paymentRequest) {
        Payment payment = Payment.toPayment(paymentRequest);
        paymentRepository.save(payment);
    }
    //결제 정보 수정하는 메소드
    public void update(Long id, PaymentRequest paymentRequest){
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 결제 정보가 존재하지 않습니다."));

        payment = Payment.builder()
                .id(payment.getId())
                .impUid(paymentRequest.getImpUid())
                .payment_price(paymentRequest.getPayment_price())
                .payment_status(paymentRequest.getPayment_status())
                .buyer_name(paymentRequest.getBuyer_name())
                .buyer_email(paymentRequest.getBuyer_email())
                .build();

        paymentRepository.save(payment);
    }

    @Transactional(readOnly = true)
    public List<PaymentDTO> findAll(Pageable pageable){
       return  paymentQueryRepository.findAllByPageable(pageable).getContent();
    }

    @Transactional(readOnly = true)
    public PaymentDTO findById(Long id) {
        return  paymentQueryRepository.findById(id);
    }



}
