package com.peter.broship.boot.domain.posts;

import com.peter.broship.boot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter//롬복 애노테이션
@NoArgsConstructor//롬복 -> 기본 생성자 추가
@Entity//JPA 애노테이션 -> 이 클래스를 토대로 table이 만들어짐
public class Posts extends BaseTimeEntity {
    @Id//pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)//pk의 생성 규칙
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    //따로 설정하는것이 없으면 @Column 생략 가능
    private String author;

    @Builder//생성자와 비슷한 역할을 하는 빌더
    public Posts(String title,String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}