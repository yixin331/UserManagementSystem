package com.usermanagementsystem.management;

import com.usermanagementsystem.management.entity.User;
import com.usermanagementsystem.management.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ManagementApplicationTests {

    @Resource
    private UserMapper userMapper;

	@Test
	void testMapper() {
	    List<User> users = userMapper.selectList(null);
	    for (User i: users){
	        System.out.println(i);
        }
	}

}
