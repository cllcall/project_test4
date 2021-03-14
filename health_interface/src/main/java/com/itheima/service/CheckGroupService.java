package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface CheckGroupService {
    public void add(CheckGroup checkGroup,Integer[] checkitemIds);
    public PageResult pageQuery(QueryPageBean queryPageBean);
    public CheckGroup findById(Integer id);
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
}
