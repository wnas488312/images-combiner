package com.wnas.images_combiner.business.service;

import com.wnas.images_combiner.api.request.ImageRequestTimeMark;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service used to handle image entries.
 */
public interface ImageService {

    /**
     * Saves image on the disk, and additional information and file path in the database.
     * @param image     Image to be saved.
     * @param request   Additional data to be saved in DB.
     */
    void saveImage(MultipartFile image, ImageRequestTimeMark request);
}
