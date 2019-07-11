package com.tutorial.repo.registration.dao.mapper;

import com.tutorial.repo.registration.dao.UserDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDORowMapper implements RowMapper<UserDO> {
    @Override
    public UserDO mapRow(ResultSet resultSet, int i) throws SQLException {
        return new UserDO(resultSet.getString("FIRST_NAME"),
                resultSet.getString("LAST_NAME"),
                resultSet.getString("MIDDLE_NAME"),
                resultSet.getString("DOB"),
                resultSet.getString("EMAIL"),
                resultSet.getString("USER_NAME"),
                resultSet.getString("PWD"));
    }
}
