package com.wnas.images_combiner.business.service;

import com.wnas.images_combiner.business.processing.VideoProcessingTask;
import com.wnas.images_combiner.business.provider.FFmpegProvider;
import com.wnas.images_combiner.data.VideoEntityRepo;
import com.wnas.images_combiner.data.entity.VideoEntity;
import com.wnas.images_combiner.data.entity.enums.VideoStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class VideoServiceImpl implements VideoService {
    private final VideoEntityRepo repo;
    private final ExecutorService executorService;
    private final FFmpegProvider fFmpegProvider;

    public VideoServiceImpl(VideoEntityRepo repo, FFmpegProvider fFmpegProvider) {
        this.repo = repo;
        this.executorService = Executors.newFixedThreadPool(10);;
        this.fFmpegProvider = fFmpegProvider;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VideoEntity createNewEntry() {
        VideoEntity entity = new VideoEntity();
        entity.setCreationDate(LocalDateTime.now());
        entity.setStatus(VideoStatus.QUEUED);
        return repo.save(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void submitProcessingTask(Long videoId) {
        final VideoProcessingTask processingTask = new VideoProcessingTask(
                repo,
                videoId,
                fFmpegProvider
        );
        executorService.submit(processingTask);
    }
}
