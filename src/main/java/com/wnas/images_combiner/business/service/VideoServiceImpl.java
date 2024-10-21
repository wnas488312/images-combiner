package com.wnas.images_combiner.business.service;

import com.wnas.images_combiner.api.request.InitializeVideoRequest;
import com.wnas.images_combiner.data.VideoEntityRepo;
import com.wnas.images_combiner.data.entity.VideoEntity;
import com.wnas.images_combiner.data.entity.enums.VideoStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VideoServiceImpl implements VideoService{
    private final VideoEntityRepo repo;

    public VideoServiceImpl(VideoEntityRepo repo) {
        this.repo = repo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VideoEntity createNewEntry(InitializeVideoRequest request) {
        VideoEntity entity = new VideoEntity();
        entity.setCreationDate(LocalDateTime.now());
        entity.setStatus(VideoStatus.QUEUED);
        return repo.save(entity);
    }
}
