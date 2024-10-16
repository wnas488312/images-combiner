package com.wnas.images_combiner.api;

import com.wnas.images_combiner.api.request.InitializeVideoRequest;
import com.wnas.images_combiner.api.response.CreateVideoResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * API used to manage and interact with generated videos.
 */
@RequestMapping("/videos")
public interface VideoApi {

    /**
     * Initialises the process of video generation.
     * Creates new DB entry and sets it to queued stage.
     * @param request Request object with data needed to create video.
     * @return Object with identifier of newly created video.
     */
    @PostMapping("/initialize")
    CreateVideoResponse Initialize(@RequestBody InitializeVideoRequest request);
}
