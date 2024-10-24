package com.wnas.images_combiner.api.endpoint;

import com.wnas.images_combiner.api.VideoApi;
import com.wnas.images_combiner.api.request.ImageRequestTimeMark;
import com.wnas.images_combiner.api.response.CreateVideoResponse;
import com.wnas.images_combiner.api.response.SimpleResponse;
import com.wnas.images_combiner.business.service.ImageService;
import com.wnas.images_combiner.business.service.VideoService;
import com.wnas.images_combiner.data.entity.VideoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class VideoEndpoint implements VideoApi {
    private final VideoService videoService;
    private final ImageService imageService;

    public VideoEndpoint(VideoService videoService, ImageService imageService) {
        this.videoService = videoService;
        this.imageService = imageService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreateVideoResponse Initialize() {
        log.info("Got request for new Video");
        final VideoEntity newEntry = videoService.createNewEntry();
        return new CreateVideoResponse(newEntry.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SimpleResponse uploadFileAndData(
            @RequestPart("file") MultipartFile image,
            @RequestPart("data") ImageRequestTimeMark request
    ) {
        imageService.saveImage(image, request);
        return new SimpleResponse("OK");
    }
}
