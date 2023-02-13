package com.sohwakmo.hr.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@DynamicInsert
@Entity(name= "POSTS")
@SequenceGenerator(name = "POSTS_SEQ_GEN", sequenceName = "POSTS_SEQ", initialValue = 1, allocationSize = 1)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSTS_SEQ_GEN")
    private Integer postNo;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(length = 1000000000)
    private String content;

    @Column(nullable = false)
    private String writer;

    @ColumnDefault("0")
    private Integer viewCnt;

    @ColumnDefault("4")
    private Integer noticeLv;

    @ColumnDefault("0")
    private Boolean deleteYn;

    public Post update(String title, String content){
        this.title= title;
        this.content= content;
        return this;
    }

    public Post viewCount(Integer viewCnt){
        this.viewCnt= viewCnt;
        return this;
    }

}
