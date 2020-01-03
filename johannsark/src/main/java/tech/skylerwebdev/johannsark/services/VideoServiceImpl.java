package tech.skylerwebdev.johannsark.services;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.skylerwebdev.johannsark.logging.Loggable;
import tech.skylerwebdev.johannsark.models.Video;

import java.util.List;

@Loggable
@Service(value = "videoService")
public class VideoServiceImpl implements VideoService {
  @Override
  public List<Video> findAll(Pageable pageable) {
    return null;
  }

  @Override
  public List<Video> findByNameContaining(String username, Pageable pageable) {
    return null;
  }

  @Override
  public Video findUserByVideoId(long id) {
    return null;
  }

  @Override
  public Video findByVideoName(String name) {
    return null;
  }

  @Override
  public void delete(long id) {

  }

  @Override
  public Video save(Video user) {
    return null;
  }

  @Override
  public Video update(Video user, long id, boolean isAdmin) {
    return null;
  }
}
