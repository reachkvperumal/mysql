package com.tutorial.repo.registration.dao;

import com.tutorial.repo.registration.dao.mapper.UserDORowMapper;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRegistration {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean addUser(UserDO userDO) {
        String SQL = "INSERT INTO USER_INFO VALUES(?,?,?,?,?,?,?)";

        int update = jdbcTemplate.update(SQL, userDO.getFirstName(), userDO.getLastName(), userDO.getMiddleName(),
                userDO.getDob(), userDO.getEmail(), userDO.getUserName(), userDO.getPassword());

        logger.debug("IS INSERT SUCCESS :: {}", update);

        return true;

    }

    public UserDO list(String key){
        String SQL = "SELECT * FROM USER_INFO WHERE USER_NAME = ?";
        List<UserDO> query = jdbcTemplate.query(SQL, new Object[]{key}, new UserDORowMapper());
        return query.get(0);
    }

}
