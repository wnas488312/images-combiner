package com.wnas.images_combiner.data.entity;

import com.wnas.images_combiner.data.entity.enums.VideoStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "video")
@Getter
@Setter
@Convert(attributeName = "time_marks", converter = TimeMarkConverter.class)
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "zoom")
    private Float zoom;

    @Column(name = "time_marks", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<TimeMark> timeMarks;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private VideoStatus status;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;
}
