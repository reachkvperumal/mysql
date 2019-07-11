package com.tutorial.repo.registration.web;

import java.util.List;

public interface ListUserController {

    ListUserRes list(ListUserReq req);

    List<ListUserRes> listAll();
}
