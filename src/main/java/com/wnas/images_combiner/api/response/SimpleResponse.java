package com.wnas.images_combiner.api.response;

/**
 * Simple response with status message.
 * @param message output message.
 */
public record SimpleResponse(String message) {

    /**
     * Default object with OK message
     * @return Object with OK message
     */
    public static SimpleResponse ok() {
        return new SimpleResponse("OK");
    }
}
