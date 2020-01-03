package tech.skylerwebdev.johannsark.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import tech.skylerwebdev.johannsark.models.Video;

public interface VideoRepository extends PagingAndSortingRepository<Video, String> {
}
