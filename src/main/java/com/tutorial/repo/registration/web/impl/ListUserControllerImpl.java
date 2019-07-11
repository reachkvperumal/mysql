package com.tutorial.repo.registration.web.impl;

import com.google.gson.GsonBuilder;
import com.tutorial.repo.registration.dao.UserDO;
import com.tutorial.repo.registration.dao.UserRegistration;
import com.tutorial.repo.registration.web.ListUserController;
import com.tutorial.repo.registration.web.ListUserReq;
import com.tutorial.repo.registration.web.ListUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/list")
public class ListUserControllerImpl implements ListUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRegistration userRegistration;

    @PostMapping(value = "/user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ListUserRes list(@RequestBody ListUserReq req) {
        UserDO userDO = userRegistration.list(req.getUserName());

        logger.debug("PAYLOAD :: {}",new GsonBuilder().setPrettyPrinting().create().toJson(req));

        return new ListUserRes(userDO.getFirstName(), userDO.getLastName(), userDO.getMiddleName(),
                userDO.getDob(), userDO.getEmail(),userDO.getUserName(),userDO.getPassword());
    }

    @PostMapping(value = "/all",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public List<ListUserRes> listAll() {

        return userRegistration.listAll()
                .stream()
                .map(o -> new ListUserRes(o.getFirstName(), o.getLastName(), o.getMiddleName(),
                        o.getDob(), o.getEmail(), o.getUserName(), o.getPassword()))
                .collect(toList());


    }
}
