package com.znzn.book.springboot.domain.posts;

import com.znzn.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor  // 기본 생성자 자동 추가
@Entity
public class Posts extends BaseTimeEntity {
    /*
     PK의 생성규칙을 나타냄,
     GenerationType.IDENTITY 가 있어야 auto_increment 가 적용
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    /*
    해당 클래스의 빌더 패턴 클래스를 생성
    생성자 상단에 생성 시 생성자에 포함된 필드만 빌드에 포함
     */
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
    
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
