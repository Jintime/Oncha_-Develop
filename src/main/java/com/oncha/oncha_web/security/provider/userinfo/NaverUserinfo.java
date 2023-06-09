package com.oncha.oncha_web.security.provider.userinfo;

import java.util.Map;

public class NaverUserinfo implements OAuth2UserInfo{

    private Map<String, Object> attributes; //getAttributes()를 받을것

    public NaverUserinfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("id");
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }
}
