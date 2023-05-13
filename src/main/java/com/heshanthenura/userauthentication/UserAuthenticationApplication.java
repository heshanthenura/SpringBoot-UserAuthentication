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
            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT,full_name TEXT NOT NULL,username TEXT NOT NULL,password TEXT NOT NULL,email TEXT,roles TEXT NOT NULL);");
            jdbcTemplate.execute("INSERT OR IGNORE INTO users (username, password, roles) SELECT 'admin', '$2a$12$hm8ReKiyVIzHT/kNrEcVvecWgElGJVjJH4oW.MbcOwvM2I8nV2sMS', 'ADMIN' WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'admin')");
            jdbcTemplate.execute("INSERT OR IGNORE INTO users (username, password, roles) SELECT 'user', '$2a$12$T7IcFP5vvVthBq5y1QdGhu5RnYhOqnzC6DnBJ8hiecKYtr411MLmu', 'USER' WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'user')");
            System.out.println("Added Data");
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Error in Creating or Inserting Data. May be data already exists");
        }

    List<User> users = jdbcTemplate.query("SELECT * FROM users",(resultSet, rowNum) -> new User(resultSet.getLong("id"), resultSet.getString("full_name"), resultSet.getString("username"),resultSet.getString("password"), resultSet.getString("email"), resultSet.getString("roles")));

        for (User user : users) {
            System.out.println(user.getId()+" "+user.getFull_name()+" "+user.getUsername()+" "+user.getPassword()+" "+user.getEmail()+" "+user.getRoles());
        }

    }
}