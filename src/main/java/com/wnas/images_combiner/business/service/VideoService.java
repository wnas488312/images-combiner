package com.wnas.images_combiner.business.service;

import com.wnas.images_combiner.api.request.InitializeVideoRequest;
import com.wnas.images_combiner.data.entity.VideoEntity;

/**
 * Service used to handle video entries.
 */
public interface VideoService {
    /**
     * Creates new DB entry based on Request data.
     * @param request   Request with video data.
     * @return          Newly created DB entry.
     */
    VideoEntity createNewEntry(InitializeVideoRequest request);
}
