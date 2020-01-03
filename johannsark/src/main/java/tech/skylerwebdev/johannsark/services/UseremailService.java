package tech.skylerwebdev.johannsark.services;

import tech.skylerwebdev.johannsark.models.UserEmail;

import java.util.List;

public interface UseremailService
{
    List<UserEmail> findAll();

    UserEmail findUseremailById(long id);

    List<UserEmail> findByUserName(String username,
                                   boolean isAdmin);

    void delete(long id,
                boolean isAdmin);

    UserEmail update(long useremailid,
                     String emailaddress,
                     boolean isAdmin);

    // note emails are added through the user process
}
