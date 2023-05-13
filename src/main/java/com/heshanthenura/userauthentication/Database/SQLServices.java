package com.heshanthenura.userauthentication.Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SQLServices {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public User findUserByUsername(String username){
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE username = "+"\'"+username+"\'",(resultSet, rowNum) -> new User(resultSet.getLong("id"), resultSet.getString("full_name"), resultSet.getString("username"),resultSet.getString("password"), resultSet.getString("email"), resultSet.getString("roles")));
        System.out.println(users.size());
        System.out.println(users.get(0).getId()+" "+users.get(0).getUsername()+" "+users.get(0).getPassword()+" "+users.get(0).getRoles());
        return users.get(0);
    }
}
