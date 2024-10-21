package com.wnas.images_combiner.api.endpoint;

import com.wnas.images_combiner.api.VideoApi;
import com.wnas.images_combiner.api.response.CreateVideoResponse;
import com.wnas.images_combiner.business.service.VideoService;
import com.wnas.images_combiner.data.entity.VideoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class VideoEndpoint implements VideoApi {
    private final VideoService videoService;

    public VideoEndpoint(VideoService videoService) {
        this.videoService = videoService;
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
}
