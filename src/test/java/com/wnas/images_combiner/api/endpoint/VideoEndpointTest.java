package com.wnas.images_combiner.api.endpoint;

import com.wnas.images_combiner.api.response.CreateVideoResponse;
import com.wnas.images_combiner.business.service.VideoService;
import com.wnas.images_combiner.data.entity.VideoEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VideoEndpointTest {

    @Mock
    private VideoService videoService;

    @InjectMocks
    private VideoEndpoint endpoint;

    @Test
    void initializeTest() {
        final VideoEntity entity = new VideoEntity();
        entity.setId(1L);

        Mockito.when(videoService.createNewEntry()).thenReturn(entity);

        final CreateVideoResponse initialized = endpoint.Initialize();
        Assertions.assertThat(initialized).isNotNull();
        Assertions.assertThat(initialized.id()).isEqualTo(1);
    }
}