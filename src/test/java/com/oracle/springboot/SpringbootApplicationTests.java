package com.oracle.springboot;

import com.oracle.springboot.bean.Properties;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {
    @Autowired
    Properties properties;

    @Test
    public void contextLoads() {

        System.out.println(properties);


    }
}