package com.wnas.images_combiner.business.service;

import com.wnas.images_combiner.business.processing.VideoProcessingTask;
import com.wnas.images_combiner.business.provider.FFmpegProvider;
import com.wnas.images_combiner.data.VideoEntityRepo;
import com.wnas.images_combiner.data.entity.VideoEntity;
import com.wnas.images_combiner.data.entity.enums.VideoStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class VideoServiceImplTest {

    @Mock
    private VideoEntityRepo repo;

    @Mock
    private ExecutorService executorService;

    @Mock
    private FFmpegProvider fFmpegProvider;

    @InjectMocks
    private VideoServiceImpl service;

    @BeforeEach
    public void setup() throws Exception {
        Field executorServiceField = VideoServiceImpl.class.getDeclaredField("executorService");
        executorServiceField.setAccessible(true);
        executorServiceField.set(service, executorService);
    }

    @Test
    void createNewEntryTest() {
        final VideoEntity entity = new VideoEntity();
        entity.setId(1L);

        ArgumentCaptor<VideoEntity> entityArgumentCaptor = ArgumentCaptor.forClass(VideoEntity.class);

        Mockito.when(repo.save(entityArgumentCaptor.capture())).thenReturn(entity);

        final VideoEntity newEntry = service.createNewEntry();

        Assertions.assertThat(newEntry).isNotNull();
        Assertions.assertThat(newEntry.getId()).isEqualTo(1);

        VideoEntity savedEntity = entityArgumentCaptor.getValue();
        Assertions.assertThat(savedEntity).isNotNull();
        Assertions.assertThat(savedEntity.getCreationDate()).isNotNull();
        Assertions.assertThat(savedEntity.getStatus()).isEqualTo(VideoStatus.QUEUED);
    }

    @Test
    void submitProcessingTaskTest() {
        ArgumentCaptor<VideoProcessingTask> taskArgumentCaptor = ArgumentCaptor.forClass(VideoProcessingTask.class);
        Mockito.when(executorService.submit(taskArgumentCaptor.capture())).thenReturn(new CompletableFuture<>());

        service.submitProcessingTask(1L);
        Mockito.verify(executorService, Mockito.times(1)).submit(any(Runnable.class));

        final VideoProcessingTask task = taskArgumentCaptor.getValue();
        Assertions.assertThat(task.getVideoId()).isEqualTo(1L);
        Assertions.assertThat(task.getVideoRepo()).isEqualTo(repo);
        Assertions.assertThat(task.getFFmpegProvider()).isEqualTo(fFmpegProvider);
    }
}