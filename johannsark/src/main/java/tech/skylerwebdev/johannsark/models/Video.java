package tech.skylerwebdev.johannsark.models;

import org.springframework.beans.factory.annotation.Lookup;
import tech.skylerwebdev.johannsark.logging.Loggable;

import javax.persistence.*;
import java.util.List;

@Loggable
@Entity
@Table(name = "videos")
public class Video {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long vidid;

  private String title;

  private String category;

  private String internalurl;

  private String externalurl;

  private String description;

  @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<VideoCategories> videocategories;
  @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<SavedVideos> savedVideos;
  public Video() {
  }

  public Video(String title, String category, String internalurl, String externalurl, String description, List<VideoCategories> videocategories) {
    this.title = title;
    this.category = category;
    this.internalurl = internalurl;
    this.externalurl = externalurl;
    this.description = description;
    this.videocategories = videocategories;
  }

  @Override
  public String toString() {
    return "Video{" +
        "vidid=" + vidid +
        ", title='" + title + '\'' +
        ", category='" + category + '\'' +
        ", internalurl='" + internalurl + '\'' +
        ", externalurl='" + externalurl + '\'' +
        ", description='" + description + '\'' +
        ", videocategories=" + videocategories +
        '}';
  }
}
