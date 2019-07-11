package com.tutorial.repo.registration.web.impl;

import com.google.gson.GsonBuilder;
import com.tutorial.repo.registration.dao.UserDO;
import com.tutorial.repo.registration.dao.UserRegistration;
import com.tutorial.repo.registration.web.AddUserController;
import com.tutorial.repo.registration.web.AddUserReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/add")
public class AddUserControllerImpl implements AddUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRegistration userRegistration;

    @PostMapping(value = "/user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean addUser(@RequestBody AddUserReq req) {

        logger.debug("PAYLOAD :: {}",new GsonBuilder().setPrettyPrinting().create().toJson(req));

        UserDO userDO = new UserDO(req.getFirstName(), req.getLastName(), req.getMiddleName(),
                req.getDob(), req.getEmail(), req.getUserName(), req.getPassword());

        userRegistration.addUser(userDO);

        return true;
    }

}
