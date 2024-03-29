package com.tutorial.repo.registration.web.impl;

import com.google.gson.Gson;
import com.tutorial.repo.registration.dao.UserDO;
import com.tutorial.repo.registration.dao.UserRegistration;
import com.tutorial.repo.registration.web.ListUserReq;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ListUserControllerImpl.class)
public class ListUserControllerImplWebTest {

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
        given(userRegistration.listAll()).willReturn(this.userDOList);

        mvc.perform(post("/list/all")
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void listSuccess() throws Exception {
        given(userRegistration.list(anyString())).willReturn(userDO);
        mvc.perform(post("/list/user")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(new Gson().toJson(new ListUserReq("1"))))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
