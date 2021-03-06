package tech.skylerwebdev.johannsark.controllers;

import tech.skylerwebdev.johannsark.logging.Loggable;
import tech.skylerwebdev.johannsark.models.UserEmail;
import tech.skylerwebdev.johannsark.services.UseremailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Loggable
@RestController
@RequestMapping("/useremails")
public class UseremailController
{
    private static final Logger logger = LoggerFactory.getLogger(UseremailController.class);

    @Autowired
    UseremailService useremailService;

    // http://localhost:2019/useremails/useremails
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/useremails",
                produces = {"application/json"})
    public ResponseEntity<?> listAllUseremails(HttpServletRequest request)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<UserEmail> allUserEmails = useremailService.findAll();
        return new ResponseEntity<>(allUserEmails,
                                    HttpStatus.OK);
    }

    // http://localhost:2019/useremails/useremail/8
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/useremail/{useremailId}",
                produces = {"application/json"})
    public ResponseEntity<?> getUserEmailById(HttpServletRequest request,
                                              @PathVariable
                                                      Long useremailId)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        UserEmail ue = useremailService.findUseremailById(useremailId);
        return new ResponseEntity<>(ue,
                                    HttpStatus.OK);
    }


    // http://localhost:2019/useremails/username/cinnamon
    @GetMapping(value = "/username/{userName}",
                produces = {"application/json"})
    public ResponseEntity<?> findUseremailByUserName(HttpServletRequest request,
                                                     @PathVariable
                                                             String userName)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<UserEmail> theUserEmails = useremailService.findByUserName(userName,
                                                                        request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(theUserEmails,
                                    HttpStatus.OK);
    }

    // http://localhost:2019/useremails/useremail/8
    @DeleteMapping("/useremail/{useremailid}")
    public ResponseEntity<?> deleteUserEmailById(HttpServletRequest request,
                                                 @PathVariable
                                                         long useremailid)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        useremailService.delete(useremailid,
                                request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // http://localhost:2019/useremails/useremail/9/email/favbun@hops.local
    @PutMapping("/useremail/{useremailid}/email/{emailaddress}")
    public ResponseEntity<?> updateUserEmail(HttpServletRequest request,
                                             @PathVariable
                                                     long useremailid,
                                             @PathVariable
                                                     String emailaddress)
    {
        useremailService.update(useremailid,
                                emailaddress,
                                request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // note emails are added through the user process
}
