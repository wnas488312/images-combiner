package com.wnas.images_combiner.business.service;

import com.wnas.images_combiner.api.request.ImageRequestTimeMark;
import com.wnas.images_combiner.data.ImageEntityRepo;
import com.wnas.images_combiner.data.VideoEntityRepo;
import com.wnas.images_combiner.data.entity.ImageEntity;
import com.wnas.images_combiner.data.entity.VideoEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {

    @Mock
    private ImageEntityRepo repo;

    @InjectMocks
    private ImageServiceImpl service;

    @Test
    void saveImageTest() throws IOException {
        final MultipartFile dummyFile = Mockito.mock(MultipartFile.class);
        Mockito.doNothing().when(dummyFile).transferTo(any(File.class));

        ArgumentCaptor<ImageEntity> entityArgumentCaptor = ArgumentCaptor.forClass(ImageEntity.class);
        Mockito.when(repo.save(entityArgumentCaptor.capture())).thenReturn(new ImageEntity());

        final ImageRequestTimeMark request = new ImageRequestTimeMark(1L, 0.01f, 0, 100);
        service.saveImage(dummyFile, request);

        Mockito.verify(repo, Mockito.times(1)).save(any(ImageEntity.class));

        final ImageEntity savedEntity = entityArgumentCaptor.getValue();
        Assertions.assertThat(savedEntity).isNotNull();
        Assertions.assertThat(savedEntity.getVideoId()).isEqualTo(request.videoId());
        Assertions.assertThat(savedEntity.getStartFrame()).isEqualTo(request.startFrame());
        Assertions.assertThat(savedEntity.getEndFrame()).isEqualTo(request.endFrame());
        Assertions.assertThat(savedEntity.getZoom()).isEqualTo(request.zoom());
        Assertions.assertThat(savedEntity.getFilePath()).isNotBlank();

        // Cleanup
        Files.delete(new File(savedEntity.getFilePath()).toPath());
    }
}