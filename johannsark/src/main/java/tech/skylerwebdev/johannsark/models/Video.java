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

  private String internalurl;

  private String externalurl;

  private String description;

  @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<VideoCategories> videocategories;
  @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<SavedVideos> savedVideos;
  public Video() {
  }

  public Video(String title, String internalurl, String externalurl, String description, List<VideoCategories> videocategories, List<SavedVideos> savedVideos) {
    this.title = title;
    this.internalurl = internalurl;
    this.externalurl = externalurl;
    this.description = description;
    this.videocategories = videocategories;
    this.savedVideos = savedVideos;
  }

  public long getVidid() {
    return vidid;
  }

  public void setVidid(long vidid) {
    this.vidid = vidid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getInternalurl() {
    return internalurl;
  }

  public void setInternalurl(String internalurl) {
    this.internalurl = internalurl;
  }

  public String getExternalurl() {
    return externalurl;
  }

  public void setExternalurl(String externalurl) {
    this.externalurl = externalurl;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<VideoCategories> getVideocategories() {
    return videocategories;
  }

  public void setVideocategories(List<VideoCategories> videocategories) {
    this.videocategories = videocategories;
  }

  public List<SavedVideos> getSavedVideos() {
    return savedVideos;
  }

  public void setSavedVideos(List<SavedVideos> savedVideos) {
    this.savedVideos = savedVideos;
  }

  @Override
  public String toString() {
    return "Video{" +
        "vidid=" + vidid +
        ", title='" + title + '\'' +
        ", internalurl='" + internalurl + '\'' +
        ", externalurl='" + externalurl + '\'' +
        ", description='" + description + '\'' +
        ", videocategories=" + videocategories +
        ", savedVideos=" + savedVideos +
        '}';
  }
}
