package com.oncha.oncha_web.feature.payment.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.oncha.oncha_web.domain.payment.PaymentRepository.OnchaPaymentRepository;
import com.oncha.oncha_web.domain.payment.model.OnchaPayment;
import com.oncha.oncha_web.feature.payment.model.OnchaPaymentDTO;
import com.oncha.oncha_web.feature.payment.model.OnchaPaymentRequest;
import com.oncha.oncha_web.feature.payment.repository.PaymentQueryRepository;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OnchaPaymentService {

    private final OnchaPaymentRepository onchaPaymentRepository;
    private final PaymentQueryRepository paymentQueryRepository;

    // 결제 정보를 저장하는 메서드
    public void save(IamportResponse<Payment> paymentRequest) {
        OnchaPaymentRequest data = new OnchaPaymentRequest();
        data.setImpUid(paymentRequest.getResponse().getImpUid());
        data.setPayment_price(String.valueOf(paymentRequest.getResponse().getAmount()));
        data.setPayment_status(paymentRequest.getResponse().getStatus());
        data.setBuyer_name(paymentRequest.getResponse().getBuyerName());
        data.setBuyer_email(paymentRequest.getResponse().getBuyerEmail());
        System.out.println(data.toString());
       OnchaPayment payment = onchaPaymentRepository.save(OnchaPayment.toPayment(data));

    }
    //결제 정보 수정하는 메소드
    public void update(Long id, OnchaPaymentRequest paymentRequest){
        OnchaPayment onchaPayment = onchaPaymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 결제 정보가 존재하지 않습니다."));

        onchaPayment = OnchaPayment.builder()
                .id(onchaPayment.getId())
                .impUid(paymentRequest.getImpUid())
                .payment_price(paymentRequest.getPayment_price())
                .payment_status(paymentRequest.getPayment_status())
                .buyer_name(paymentRequest.getBuyer_name())
                .buyer_email(paymentRequest.getBuyer_email())
                .build();

        onchaPaymentRepository.save(onchaPayment);
    }

    @Transactional(readOnly = true)
    public List<OnchaPaymentDTO> findAll(Pageable pageable){
       return  paymentQueryRepository.findAllByPageable(pageable).getContent();
    }

    @Transactional(readOnly = true)
    public OnchaPaymentDTO findById(Long id) {
        return  paymentQueryRepository.findById(id);
    }



}
