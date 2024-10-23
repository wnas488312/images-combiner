package com.wnas.images_combiner.data.entity;

import com.wnas.images_combiner.data.entity.enums.VideoStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "video")
@Getter
@Setter
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private VideoStatus status;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;
}
