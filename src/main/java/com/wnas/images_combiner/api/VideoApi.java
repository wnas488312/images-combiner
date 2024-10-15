package com.wnas.images_combiner.api;

import com.wnas.images_combiner.api.response.CreateVideoResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * API used to manage and interact with generated videos.
 */
@RequestMapping("/videos")
public interface VideoApi {

    /**
     * Initialises the process of video generation.
     * Creates new DB entry and sets it to queued stage.
     * @return Object with identifier of newly created video.
     */
    @PostMapping("/initialize")
    CreateVideoResponse Initialize();
}
