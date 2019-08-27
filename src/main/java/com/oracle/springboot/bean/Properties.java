package com.oracle.springboot.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
@PropertySource(value = {"classpath:pro.properties"})
@Component
@ConfigurationProperties(prefix = "properties")

//@Validated  /*JSR303数据校验，支持复杂类型*/
public class Properties {
    /*@Email*/
    private String name;
    private Integer age;
    private Boolean is;
    private String sex;



    @Override
    public String toString() {
        return "Properties{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", is=" + is +
                ", sex=" + sex +
                '}';
    }
}
