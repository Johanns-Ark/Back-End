package tech.skylerwebdev.johannsark.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import tech.skylerwebdev.johannsark.logging.Loggable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Loggable
@Entity
@Table(name = "videocategories",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"vidid", "catid"})})
public class VideoCategories extends Auditable implements Serializable {

  @Id
  @ManyToOne
  @JoinColumn(name = "vidid")
  @JsonIgnoreProperties("videocategories")
  private Video video;

  @Id
  @ManyToOne
  @JoinColumn(name= "catid")
  @JsonIgnoreProperties("videocategories")
  private Category category;



  public VideoCategories() {

  }

  public VideoCategories(Video video, Category category) {
    this.video = video;
    this.category = category;
  }

  public Video getVideo() {
    return video;
  }

  public void setVideo(Video video) {
    this.video = video;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    VideoCategories that = (VideoCategories) o;
    return Objects.equals(video, that.video) &&
        Objects.equals(category, that.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(video, category);
  }

  @Override
  public String toString() {
    return "VideoCategories{" +
        "video=" + video +
        ", category=" + category +
        '}';
  }
}
