package tech.skylerwebdev.johannsark.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import tech.skylerwebdev.johannsark.models.Role;
import tech.skylerwebdev.johannsark.models.SavedVideos;
import tech.skylerwebdev.johannsark.models.Video;
import tech.skylerwebdev.johannsark.view.JustTheCount;

public interface VideoRepository extends PagingAndSortingRepository<Video, String> {
//  @Query(value = "SELECT COUNT(*) as count FROM savedvideos WHERE uuid = :uuid AND vidid= :vidid",
//      nativeQuery = true)
//  JustTheCount checkUserRolesCombo(long uuid,
//                                   long vidid);
//  @Transactional
//  @Modifying
//  @Query(value = "DELETE FROM savedvideos WHERE uuid = :uuid AND vidid = :vidid")
//  void deleteUserRoles(long uuid,
//                       long roleid);
//
//  @Transactional
//  @Modifying
//  @Query(value = "INSERT INTO savedvideos (uuid, vidid) VALUES (:uuid, :vidid)",
//      nativeQuery = true)
//  void insertUserRoles(long uuid,
//                       long vidid);
//
//  SavedVideos findByNameIgnoreCase(String name);

//  @Transactional
//  @Modifying
//  // user Role instead of roles in order to use Hibernate SQL
//  @Query(value = "UPDATE Role SET NAME = :name WHERE roleid = :roleid")
//  void updateRoleName(long roleid,
//                      String name);






}
