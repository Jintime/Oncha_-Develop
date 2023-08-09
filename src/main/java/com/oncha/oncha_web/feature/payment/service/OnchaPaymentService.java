package com.oncha.oncha_web.feature.payment.service;

import com.amazonaws.services.ec2.model.transform.AdvertiseByoipCidrResultStaxUnmarshaller;
import com.amazonaws.services.kms.model.NotFoundException;
import com.oncha.oncha_web.domain.payment.repository.OnchaPaymentRepository;
import com.oncha.oncha_web.domain.payment.model.OnchaPayment;
import com.oncha.oncha_web.domain.user.model.Address;
import com.oncha.oncha_web.domain.user.model.Member;
import com.oncha.oncha_web.domain.user.repository.AddressRepository;
import com.oncha.oncha_web.domain.user.repository.MemberRepository;
import com.oncha.oncha_web.feature.payment.model.OnchaPaymentDTO;
import com.oncha.oncha_web.feature.payment.model.OnchaPaymentInfoDTO;
import com.oncha.oncha_web.feature.payment.model.OnchaPaymentRequest;
import com.oncha.oncha_web.feature.payment.repository.PaymentQueryRepository;
import com.oncha.oncha_web.feature.product.productBoard.model.ProductBoardDTO;
import com.oncha.oncha_web.feature.user.model.AddressDTO;
import com.oncha.oncha_web.feature.user.model.MemberDTO;
import com.oncha.oncha_web.feature.user.service.AddressService;
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
    private final AddressRepository addressRepository;
    private final MemberRepository memberRepository;

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

    @Transactional
    public OnchaPaymentInfoDTO setPaymentData(OnchaPaymentInfoDTO onchaPaymentInfoDTO) {
        Long userId = SecurityUtil.getCurrentId().orElse(null);
        Member member = memberRepository.findById(userId).orElse(null);
        Address address = addressRepository.findByMemberId(userId);

        if (member != null) {
            if (address == null) {
                address = Address.builder().
                         default_zipcode(onchaPaymentInfoDTO.getZip_code())
                        .default_address(onchaPaymentInfoDTO.getAddress())
                        .default_address_detail(onchaPaymentInfoDTO.getAddress_detail())
                        .member(member).build();
        }
            member.getAddressList().add(address);
            addressRepository.save(address);
            memberRepository.save(member);

        }

        return onchaPaymentInfoDTO;
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
