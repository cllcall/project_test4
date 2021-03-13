package com.itheima.service;

import com.itheima.pojo.CheckGroup;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface CheckGroupService {
    public void add(CheckGroup checkGroup,Integer[] checkitemIds);
}
