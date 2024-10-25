package com.wnas.images_combiner.api;

import com.wnas.images_combiner.api.request.ImageRequestTimeMark;
import com.wnas.images_combiner.api.response.CreateVideoResponse;
import com.wnas.images_combiner.api.response.SimpleResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * Endpoint used to upload image that will be displayed in the video.
     * @param image     Image to be used.
     * @param request   Additional date used in image display.
     * @return          Status of the upload.
     */
    @PostMapping("/upload")
    SimpleResponse uploadFileAndData(
            @RequestPart("file") MultipartFile image,
            @RequestPart("data") ImageRequestTimeMark request);

    /**
     * Starts processing of a video with provided id.
     * @param id    Identifier of a video.
     * @return      Status of a processing initialization.
     */
    @PatchMapping("/{id}/process")
    SimpleResponse startProcessing(@PathVariable Long id);
}
