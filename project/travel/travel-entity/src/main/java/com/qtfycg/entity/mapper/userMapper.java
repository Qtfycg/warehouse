package com.qtfycg.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qtfycg.entity.domain.user;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface userMapper extends BaseMapper<user> {
    user login(String username, String password);

}




