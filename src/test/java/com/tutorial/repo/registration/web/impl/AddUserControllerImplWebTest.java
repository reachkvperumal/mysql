package com.tutorial.repo.registration.web.impl;

import com.google.gson.Gson;
import com.tutorial.repo.registration.dao.UserDO;
import com.tutorial.repo.registration.dao.UserRegistration;
import com.tutorial.repo.registration.web.AddUserReq;
import com.tutorial.repo.registration.web.ListUserRes;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AddUserControllerImpl.class)
public class AddUserControllerImplWebTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mvc;

    private List<UserDO> userDOList;

    private UserDO userDO;

    @MockBean
    private UserRegistration userRegistration;

    private String getFile(String resource) throws IOException {
        return new String(IOUtils.toByteArray(wac.getResource("classpath:" + resource).getInputStream()));
    }

    @Before
    public void setup() throws Exception {
        UserDO[] userDOS = new Gson().fromJson(getFile("ListUser.json"), UserDO[].class);
        this.userDOList = Arrays.asList(userDOS);
        this.userDO = this.userDOList.stream().filter(obj -> obj.getUserName().equals("1")).findFirst().get();
    }

    @Test
    public void listAllSuccess() throws Exception {
        given(userRegistration.addUser(any(UserDO.class))).willReturn(true);

        AddUserReq userReq = new AddUserReq();
        userReq.setDob(userDO.getDob());
        userReq.setEmail(userDO.getEmail());
        userReq.setFirstName(userDO.getFirstName());
        userReq.setLastName(userDO.getLastName());
        userReq.setPassword(userDO.getPassword());
        userReq.setUserName(userDO.getUserName());
        userReq.setMiddleName(userDO.getMiddleName());

        mvc.perform(post("/add/user")
                .contentType(APPLICATION_JSON)
                .content(new Gson().toJson(userReq))
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void setUserRes() {
        ListUserRes userRes = new ListUserRes();
        userRes.setDob(userDO.getDob());
        userRes.setEmail(userDO.getEmail());
        userRes.setFirstName(userDO.getFirstName());
        userRes.setLastName(userDO.getFirstName());
        userRes.setMiddleName(userDO.getMiddleName());
        userRes.setUserName(userDO.getUserName());
        userRes.setPassword(userDO.getPassword());
    }
}
