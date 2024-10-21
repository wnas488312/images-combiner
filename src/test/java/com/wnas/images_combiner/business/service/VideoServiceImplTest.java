package com.wnas.images_combiner.business.service;

import com.wnas.images_combiner.api.request.InitializeVideoRequest;
import com.wnas.images_combiner.data.VideoEntityRepo;
import com.wnas.images_combiner.data.entity.VideoEntity;
import com.wnas.images_combiner.data.entity.enums.VideoStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class VideoServiceImplTest {

    @Mock
    private VideoEntityRepo repo;

    @InjectMocks
    private VideoServiceImpl service;

    @Test
    void createNewEntryTest() {
        final VideoEntity entity = new VideoEntity();
        entity.setId(1L);

        ArgumentCaptor<VideoEntity> entityArgumentCaptor = ArgumentCaptor.forClass(VideoEntity.class);

        Mockito.when(repo.save(entityArgumentCaptor.capture())).thenReturn(entity);

        final VideoEntity newEntry = service.createNewEntry(new InitializeVideoRequest(Collections.emptyList()));

        Assertions.assertThat(newEntry).isNotNull();
        Assertions.assertThat(newEntry.getId()).isEqualTo(1);

        VideoEntity savedEntity = entityArgumentCaptor.getValue();
        Assertions.assertThat(savedEntity).isNotNull();
        Assertions.assertThat(savedEntity.getCreationDate()).isNotNull();
        Assertions.assertThat(savedEntity.getStatus()).isEqualTo(VideoStatus.QUEUED);
    }
}