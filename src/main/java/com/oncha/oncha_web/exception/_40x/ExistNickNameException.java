package com.oncha.oncha_web.exception._40x;

public class ExistNickNameException extends BadRequestException{
    public ExistNickNameException(String nickName) {
        super(String.format("%s 에 해당하는 닉네임은 이미 존재합니다.", nickName));
    }
}
