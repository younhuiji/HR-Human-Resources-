package com.sohwakmo.hr.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name = "MESSAGE")
@SequenceGenerator(name = "MESSAGE_SEQ_GEN", sequenceName = "MESSAGE_SEQ", allocationSize = 1)
public class Message extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_SEQ_GEN")
    private Integer messageNo;
    private String messageType;
    private String title;
    private String content;
    private String filePath1;
    private String fileName1;
    private String filePath2;
    private String fileName2;
    private String filePath3;
    private String fileName3;
    private Integer receiveNo;
    @ColumnDefault("0")
    private Integer receiveReadCheck;
    @ColumnDefault("0")
    private Integer receiveTrash;
    @ColumnDefault("0")
    private Integer receiveDelete;
    private Integer senderNo;
    @ColumnDefault("0")
    private Integer senderTrash;
    @ColumnDefault("0")
    private Integer senderDelete;

    @ManyToOne
    @JoinColumn(name = "employeeNo")
    private Employee employee;

}
