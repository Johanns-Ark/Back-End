package tech.skylerwebdev.johannsark.services;

import org.springframework.data.domain.Pageable;
import tech.skylerwebdev.johannsark.models.Video;

import java.util.List;

public interface VideoService {
  List<Video> findAll(Pageable pageable);

  List<Video> findByNameContaining(String username,
                                  Pageable pageable);

  Video findUserByVideoId(long id);

  Video findByVideoName(String name);

  void delete(long id);

  Video save(Video user);

  Video update(Video user,
              long id,
              boolean isAdmin);


}
