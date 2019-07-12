package com.tutorial.repo.registration.dao;

import com.google.gson.Gson;
import com.tutorial.repo.registration.dao.mapper.UserDORowMapper;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTest {

    @InjectMocks
    private UserRegistration userRegistration;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private ResultSet resultSet;

    private List<UserDO> userDOList;

    private UserDO userDO;

    private String getFile(String resource) throws IOException {
        return new String(IOUtils.toByteArray(MethodHandles.lookup().lookupClass().getClassLoader().getResourceAsStream(resource)));
    }

    @Before
    public void init() throws Exception {
        UserDO[] userDOS = new Gson().fromJson(getFile("ListUser.json"), UserDO[].class);
        this.userDOList = Arrays.asList(userDOS);
        this.userDO = this.userDOList.stream().filter(obj -> obj.getUserName().equals("1")).findFirst().get();
    }

    @Test
    public void addUserSuccess() {
        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(1);
        assertTrue(userRegistration.addUser(userDO));
    }

    @Test
    public void listSuccess() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(UserDORowMapper.class))).thenReturn(Arrays.asList(userDO));
        UserDO userDO = userRegistration.list("1");

        assertNotNull(userDO);
        assertEquals("SUN", userDO.getFirstName());
        assertEquals("ORACLE", userDO.getLastName());
        assertEquals("8", userDO.getMiddleName());
        assertEquals("19990318", userDO.getDob());
        assertEquals("1@outlook.com", userDO.getEmail());
        assertEquals("1", userDO.getUserName());
        assertEquals("password", userDO.getPassword());
    }

    @Test
    public void listAllSuccess() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(UserDORowMapper.class))).thenReturn(userDOList);
        List<UserDO> userDOS = userRegistration.listAll();
        assertNotNull(userDOS);
        assertEquals(3, userDOS.size());
    }

    @Test
    public void rowMappingSuccess() throws Exception {
        when(resultSet.getString("FIRST_NAME")).thenReturn(userDO.getFirstName());
        when(resultSet.getString("LAST_NAME")).thenReturn(userDO.getLastName());
        when(resultSet.getString("MIDDLE_NAME")).thenReturn(userDO.getMiddleName());
        when(resultSet.getString("DOB")).thenReturn(userDO.getDob());
        when(resultSet.getString("EMAIL")).thenReturn(userDO.getEmail());
        when(resultSet.getString("USER_NAME")).thenReturn(userDO.getUserName());
        when(resultSet.getString("PWD")).thenReturn(userDO.getPassword());

        UserDORowMapper rowMapper = new UserDORowMapper();

        assertEquals("SUN", rowMapper.mapRow(resultSet, 1).getFirstName());
        assertEquals("ORACLE", rowMapper.mapRow(resultSet, 2).getLastName());
        assertEquals("8", rowMapper.mapRow(resultSet, 3).getMiddleName());
        assertEquals("19990318", rowMapper.mapRow(resultSet, 4).getDob());
        assertEquals("1@outlook.com", rowMapper.mapRow(resultSet, 5).getEmail());
        assertEquals("1", rowMapper.mapRow(resultSet, 6).getUserName());
        assertEquals("password", rowMapper.mapRow(resultSet, 7).getPassword());
    }

    @Test
    public void UserDOSetterTest() {
        UserDO user = new UserDO();

        user.setFirstName("SUN");
        user.setLastName("ORACLE");
        user.setMiddleName("8");
        user.setDob("19990318");
        user.setEmail("1@outlook.com");
        user.setUserName("1");
        user.setPassword("password");

        assertEquals("SUN", user.getFirstName());
        assertEquals("ORACLE", user.getLastName());
        assertEquals("8", user.getMiddleName());
        assertEquals("19990318", user.getDob());
        assertEquals("1@outlook.com", user.getEmail());
        assertEquals("1", user.getUserName());
        assertEquals("password", user.getPassword());
    }
}
