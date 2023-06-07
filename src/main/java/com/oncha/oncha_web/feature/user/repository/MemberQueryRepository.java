package com.oncha.oncha_web.feature.user.repository;

import com.oncha.oncha_web.domain.user.model.Member;
import com.oncha.oncha_web.feature.user.model.AddressDTO;
import com.oncha.oncha_web.feature.user.model.MemberDTO;
import com.oncha.oncha_web.util.Querydsl4RepositorySupport;
import com.querydsl.core.types.Projections;
import org.springframework.stereotype.Repository;

import static com.oncha.oncha_web.domain.user.model.QAddress.address;
import static com.oncha.oncha_web.domain.user.model.QMember.member;

@Repository
public class MemberQueryRepository extends Querydsl4RepositorySupport {

    public MemberQueryRepository(){super((Member.class));}

    public MemberDTO findById(Long id) {
        return select(Projections.constructor(
                MemberDTO.class,
                member.id,
                member.userId,
                member.name,
                member.phoneNumber,
                member.email,
                member.birth,
                member.gender,
                member.grade,
                member.disable,
                member.provider,
                member.providerId,
                Projections.list(Projections.constructor(
                        AddressDTO.class,
                        address.id,
                        address.default_zipcode,
                        address.default_address,
                        address.default_address_detail,
                        address.spare_zipcode,
                        address.spare_address,
                        address.spare_address_detail,
                        address.spare2_zipcode,
                        address.spare2_address,
                        address.spare2_address_detail
                ))
        ))
                .from(member)
                .leftJoin(address).on(address.member.eq(member))
                .where(member.id.eq(id))
                .fetchJoin().fetchOne();
    }

}
