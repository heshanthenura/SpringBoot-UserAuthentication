package com.heshanthenura.userauthentication;

import com.heshanthenura.userauthentication.Database.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class UserAuthenticationApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(UserAuthenticationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        try{
            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (id NUMERIC PRIMARY KEY UNIQUE,username TEXT NOT NULL,password TEXT NOT NULL,roles TEXT NOT NULL);");
            jdbcTemplate.execute("INSERT INTO users VALUES ('1','admin','$2a$12$hm8ReKiyVIzHT/kNrEcVvecWgElGJVjJH4oW.MbcOwvM2I8nV2sMS','ADMIN')");
            jdbcTemplate.execute("INSERT INTO users VALUES ('2','user','$2a$12$T7IcFP5vvVthBq5y1QdGhu5RnYhOqnzC6DnBJ8hiecKYtr411MLmu','USER')");
        }catch (Exception e){
            System.out.println("Error in Creating or Inserting Data. May be data already exists");
        }

        List<User> users = jdbcTemplate.query("SELECT * FROM users",(resultSet, rowNum) -> new User(resultSet.getLong("id"),resultSet.getString("username"),resultSet.getString("password"),resultSet.getString("roles")));

        for (User user : users) {
            System.out.println(user.getId()+" "+user.getUsername()+" "+user.getPassword()+" "+user.getRoles());
        }

    }
}