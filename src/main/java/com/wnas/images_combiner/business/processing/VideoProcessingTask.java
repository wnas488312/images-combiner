package com.wnas.images_combiner.business.processing;

import com.wnas.images_combiner.business.provider.FFmpegProvider;
import com.wnas.images_combiner.data.ImageEntityRepo;
import com.wnas.images_combiner.data.VideoEntityRepo;
import com.wnas.images_combiner.data.entity.ImageEntity;
import com.wnas.images_combiner.data.entity.VideoEntity;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;

@Slf4j
public class VideoProcessingTask implements Runnable {
    private final VideoEntityRepo videoRepo;
    private final Long videoId;
    private final FFmpegProvider fFmpegProvider;

    public VideoProcessingTask(VideoEntityRepo videoRepo, Long videoId, FFmpegProvider fFmpegProvider) {
        this.videoRepo = videoRepo;
        this.videoId = videoId;
        this.fFmpegProvider = fFmpegProvider;
    }

    @Override
    public void run() {
        log.info("Started processing of video for id: {}", videoId);

        final VideoEntity entity = videoRepo.findById(videoId)
                .orElseThrow();

        final Set<ImageEntity> imageEntities = entity.getImageEntities();

        final int width = entity.getWidth();
        final int height = entity.getHeight();

        final File videoTempFile = createVideoTempFile();

        try (FFmpegFrameRecorder frameRecorder = createRecorder(videoTempFile.getPath(), width, height, 24);
             Java2DFrameConverter converter = new Java2DFrameConverter()) {
            frameRecorder.start();
            for (int i = 0; i < getLastFrameIndex(imageEntities); i++) {
                final BufferedImage image = getImageInRange(i, imageEntities);
                final Frame frame = converter.convert(image);
                frameRecorder.record(frame);
            }
        } catch (FrameRecorder.Exception e) {
            log.error("Error occurred when generation video for id: {}", videoId, e);
            throw new RuntimeException(e);
        }
        log.info("Processing of video with id: {} finished successfully.", videoId);
    }

    private FFmpegFrameRecorder createRecorder(String filePath, int width, int height, double frameRate) {
        FFmpegFrameRecorder recorder = fFmpegProvider.getRecorder(filePath, width, height);
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
        recorder.setFrameRate(frameRate);
        recorder.setFormat("mp4");
        return recorder;
    }

    private File createVideoTempFile() {
        try {
            return File.createTempFile("ICombVideo", ".mp4");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int getLastFrameIndex(Set<ImageEntity> imageEntities) {
        int index = 0;

        for (ImageEntity entity: imageEntities) {
            final Integer endFrame = entity.getEndFrame();
            if (endFrame > index) {
                index = endFrame;
            }
        }

        return index;
    }

    private BufferedImage getImageInRange(int currentFrame, Set<ImageEntity> imageEntities) {
        for (ImageEntity entity: imageEntities) {
            if (currentFrame >= entity.getStartFrame() && currentFrame < entity.getEndFrame()) {
                try {
                    final File imageFile = new File(entity.getFilePath());
                    return ImageIO.read(imageFile);
                } catch (IOException e) {
                    log.error("Error occurred when getting image for video with id: {}", videoId, e);
                    throw new RuntimeException(e);
                }
            }
        }

        throw new RuntimeException("Cannot find Image in range");
    }
}
