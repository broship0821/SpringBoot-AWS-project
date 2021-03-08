package com.peter.broship.boot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter//모든 getter 생성
@RequiredArgsConstructor//모든 final 필드 포함하는 생성자 생성
public class HelloResponseDto {
    private final String name;
    private final int amount;
    //setter 는 작성하지 않음
}
