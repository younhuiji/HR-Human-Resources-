package com.sohwakmo.hr.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

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

    @Column
    private String content;

    @Column(nullable = false)
    private String writer;

    @ColumnDefault("0")
    private Integer views;

    public Post update(String title, String content){
        this.title= title;
        this.content= content;
        return this;
    }

    public Post viewCount(Integer views){
        this.views= views;
        return this;
    }

}
