package tech.skylerwebdev.johannsark.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import tech.skylerwebdev.johannsark.logging.Loggable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Loggable
@Entity
@Table(name = "savedvideos", uniqueConstraints = {@UniqueConstraint(columnNames = {"uuid", "videoid"})})
public class SavedVideos implements Serializable
{
@Id
@ManyToOne
@JoinColumn(name = "uuid")
@JsonIgnoreProperties("savedVideos")
private User user;

@Id
  @ManyToOne
  @JoinColumn(name = "videoid")
  @JsonIgnoreProperties("savedVideos")
  private Video video;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SavedVideos that = (SavedVideos) o;
    return Objects.equals(user, that.user) &&
        Objects.equals(video, that.video);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, video);
  }

  @Override
  public String toString() {
    return "SavedVideos{" +
        "user=" + user +
        ", video=" + video +
        '}';
  }
}
