package com.oncha.oncha_web.feature.user.service;

import com.oncha.oncha_web.domain.user.model.Address;
import com.oncha.oncha_web.domain.user.repository.AddressRepository;
import com.oncha.oncha_web.feature.user.model.AddressDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public void SaveAddress(AddressDTO addressDTO){
        Address address =addressRepository.save(Address.toAddress(addressDTO));
    }
}
