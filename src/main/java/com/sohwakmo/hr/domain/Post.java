package com.sohwakmo.hr.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name= "POSTS")
@SequenceGenerator(name= "POSTS_SEQ_GEN", sequenceName = "POSTS_SEQ", initialValue = 1, allocationSize = 1)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSTS_SEQ_GEN")
    private Integer postNo;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @ColumnDefault("0")
    private Integer clickCount;

}
