package com.wnas.images_combiner.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "image")
@Getter
@Setter
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "start_frame")
    private Integer startFrame;

    @Column(name = "end_frame")
    private Integer endFrame;

    @Column(name = "zoom")
    private Integer zoom;

    @Column(name = "file_path")
    private String filePath;

    @ManyToOne
    @JoinColumn(name="video_id", nullable=false)
    private VideoEntity videoEntity;
}
