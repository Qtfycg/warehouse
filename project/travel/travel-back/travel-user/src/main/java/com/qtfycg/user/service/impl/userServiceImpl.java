package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.user;
import generator.service.userService;
import generator.mapper.userMapper;
import org.springframework.stereotype.Service;

/**
* @author 12643
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-02-25 21:52:34
*/
@Service
public class userServiceImpl extends ServiceImpl<userMapper, user>
    implements userService{

}




