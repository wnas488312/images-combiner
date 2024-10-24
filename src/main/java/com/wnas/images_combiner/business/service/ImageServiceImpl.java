package com.wnas.images_combiner.business.service;

import com.wnas.images_combiner.api.request.ImageRequestTimeMark;
import com.wnas.images_combiner.data.ImageEntityRepo;
import com.wnas.images_combiner.data.entity.ImageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageEntityRepo repo;

    public ImageServiceImpl(ImageEntityRepo repo) {
        this.repo = repo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveImage(MultipartFile image, ImageRequestTimeMark request) {
        try {
            final File tmpFile = File.createTempFile("IComb", ".tmp");
            image.transferTo(tmpFile);

            final ImageEntity entity = new ImageEntity();
            entity.setVideoId(request.videoId());
            entity.setStartFrame(request.startFrame());
            entity.setEndFrame(request.endFrame());
            entity.setZoom(request.zoom());
            entity.setFilePath(tmpFile.getPath());
            repo.save(entity);
        } catch (IOException e) {
            log.error("Error occurred when creating temp file.");
            throw new RuntimeException(e);
        }
    }
}
