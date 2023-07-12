package com.oncha.oncha_web.feature.payment.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.oncha.oncha_web.domain.payment.repository.OnchaPaymentRepository;
import com.oncha.oncha_web.domain.payment.model.OnchaPayment;
import com.oncha.oncha_web.feature.payment.model.OnchaPaymentDTO;
import com.oncha.oncha_web.feature.payment.model.OnchaPaymentInfoDTO;
import com.oncha.oncha_web.feature.payment.model.OnchaPaymentRequest;
import com.oncha.oncha_web.feature.payment.repository.PaymentQueryRepository;
import com.oncha.oncha_web.feature.product.productBoard.model.ProductBoardDTO;
import com.oncha.oncha_web.feature.user.model.MemberDTO;
import com.oncha.oncha_web.feature.user.service.MemberService;
import com.oncha.oncha_web.util.SecurityUtil;
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
    private final MemberService memberService;

    // 결제 정보를 저장하는 메서드
    @Transactional
    public void save(IamportResponse<Payment> paymentRequest) {
        OnchaPaymentRequest data = new OnchaPaymentRequest();
        data.setImpUid(paymentRequest.getResponse().getImpUid());
        data.setPayment_price(String.valueOf(paymentRequest.getResponse().getAmount()));
        data.setPayment_status(paymentRequest.getResponse().getStatus());
        data.setBuyer_name(paymentRequest.getResponse().getBuyerName());
        data.setBuyer_email(paymentRequest.getResponse().getBuyerEmail());
        data.setPost_code(paymentRequest.getResponse().getBuyerPostcode());
        data.setPhone_number(paymentRequest.getResponse().getBuyerTel());
        onchaPaymentRepository.save(OnchaPayment.toPayment(data));
    }
    public OnchaPaymentInfoDTO setPaymentData(ProductBoardDTO productBoardDTO) {
        Long userId = SecurityUtil.getCurrentId().orElse(null);
        MemberDTO memberDTO = memberService.findById(userId);
        OnchaPaymentInfoDTO data =new OnchaPaymentInfoDTO();
        data.setPayment_price(String.valueOf(productBoardDTO.getPrice()));
        data.setProduct_name(productBoardDTO.getProductName());
        data.setBuyer_name(memberDTO.getName());
        data.setBuyer_email(memberDTO.getEmail());
        data.setPhone_number(memberDTO.getPhoneNumber());
        data.setSeller_id(productBoardDTO.getId());
        return data;
    }
    //결제 정보 수정하는 메소드
    @Transactional
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
