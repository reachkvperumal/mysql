package com.tutorial.repo.registration.web.impl;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.tutorial.repo.registration.dao.UserDO;
import com.tutorial.repo.registration.dao.UserRegistration;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ListUserControllerImpl.class)
public class ListUserControllerImplWebTest {

    @Autowired
    private Environment env;

    @Autowired
    private WebApplicationContext wac;


    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRegistration userRegistration;

    private String getFile(String resource) throws IOException {
        return new String(IOUtils.toByteArray(wac.getResource("classpath:" + resource).getInputStream()));
    }

    @Test
    public void listAllSuccess() throws Exception {

        UserDO[] userDOS = new GsonBuilder().registerTypeAdapter(byte[].class,
                (JsonDeserializer<byte[]>) (json, typeOfT, context) -> Base64.decodeBase64(json.getAsString()))
                .create().fromJson(getFile("ListUser.json"), UserDO[].class);
        given(userRegistration.listAll()).willReturn(Arrays.asList(userDOS));

        mvc.perform(post("/list/all")
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());


    }

}
