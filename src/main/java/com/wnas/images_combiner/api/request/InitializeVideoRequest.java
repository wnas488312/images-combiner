package com.wnas.images_combiner.api.request;

import java.util.List;

/**
 * Request object with data needed to create video.
 * @param timeMarks List with data about avery image displayed.
 */
public record InitializeVideoRequest(List<InitializeVideoTimeMark> timeMarks) {
}
