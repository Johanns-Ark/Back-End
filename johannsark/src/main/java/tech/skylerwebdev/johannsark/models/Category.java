package tech.skylerwebdev.johannsark.models;

import tech.skylerwebdev.johannsark.logging.Loggable;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Loggable
@Entity
@Table(name = "categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long catid;

  private String name;

  @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<VideoCategories> videocategories;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<VideoCategories> getVideocategories() {
    return videocategories;
  }

  public void setVideocategories(List<VideoCategories> videocategories) {
    this.videocategories = videocategories;
  }

  public long getCatid() {
        return catid;
  }

  public void setCatid(long catid) {
    this.catid = catid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Category category = (Category) o;
    return catid == category.catid &&
        Objects.equals(name, category.name) &&
        Objects.equals(videocategories, category.videocategories);
  }

  @Override
  public int hashCode() {
    return Objects.hash(catid, name, videocategories);
  }

  @Override
  public String toString() {
    return "Category{" +
        "catid=" + catid +
        ", name='" + name + '\'' +
        ", videocategories=" + videocategories +
        '}';
  }
}
