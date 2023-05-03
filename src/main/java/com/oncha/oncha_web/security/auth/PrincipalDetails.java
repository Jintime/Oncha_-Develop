package com.oncha.oncha_web.security.auth;


//시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
// 로그인이 진행이 완료가 되면 security session을 만들어줌
// 세션은 세션인데 이건 시큐리티가 자신만의 세션을 만들어줌 - (Security ContextHolder)
// 세션에 들어갈 수 있는 객체는 Authentication 객체로 정해져있음
// Authentication 안에는 유저정보가 있어야함.
// User 오브젝트 타입은 => userDetails 타입 객체

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

// Security Session => Authentication => UserDetails
// 세션안에 들어갈수있는건 어쎈티케이션 객체고 그안에서 유저정보를 저장하는건 UserDetails 객체
//Authentication 객체는 PrincipalDetailsService 에서 만듬
@Data
public class PrincipalDetails implements OAuth2User { //이렇게 하면 Authentication 객체안에 넣을수잇음

    private Long id;
    private String role;

    //Oauth의 어트리뷰트를 저장하는것.
    private Map <String, Object> attributes;

    //JWT 필터에 사용되는 생성자
    public PrincipalDetails(Long id ,String role) {
        this.id = id;
        this.role = role;
    }

    //Oauth 로그인시 사용되는 생성자
    public PrincipalDetails (Long id, String role , Map<String, Object> attributes) {
        this.id = id;
        this.role = role;
        this.attributes = attributes;
    }

    //해당 유저의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
               return role;
            }
        });
        return collect;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    //이쪽 아래는 oauthUser method
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    @Deprecated
    public String getName() {
        return String.valueOf(id);
    }
}


