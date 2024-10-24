package com.wnas.images_combiner.api.request;

/**
 * Object with data about every image displayed in the video.
 * @param videoId       Id of a video generation process.
 * @param zoom          Zoom value for image. Will start with zero and end with provided value.
 * @param startFrame    Frame number when image will start.
 * @param endFrame      Frame number when image will end.
 */
public record ImageRequestTimeMark(Long videoId, Float zoom, Integer startFrame, Integer endFrame) {
}
