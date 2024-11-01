package com.wnas.images_combiner.business.service;

import com.wnas.images_combiner.data.entity.VideoEntity;

/**
 * Service used to handle video entries.
 */
public interface VideoService {
    /**
     * Creates new DB entry.
     * @return          Newly created DB entry.
     */
    VideoEntity createNewEntry();

    /**
     * Submits processing task for video with given id.
     * @param videoId   Identifier of a video process.
     */
    void submitProcessingTask(Long videoId);
}
