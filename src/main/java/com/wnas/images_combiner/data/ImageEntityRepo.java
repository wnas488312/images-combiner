package com.wnas.images_combiner.data;

import com.wnas.images_combiner.data.entity.ImageEntity;
import com.wnas.images_combiner.data.entity.VideoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageEntityRepo extends CrudRepository<ImageEntity, Long> {
}
