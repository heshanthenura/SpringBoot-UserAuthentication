package com.heshanthenura.userauthentication;

import com.heshanthenura.userauthentication.Database.SQLServices;
import com.heshanthenura.userauthentication.Database.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserAuthenticationApplicationTests {

    @Autowired
    SQLServices sqlServices;

    @Test
    void getUserByName() {
        User user = sqlServices.findUserByUsername("admin");
        System.out.println(user.getId()+" "+user.getUsername()+" "+user.getPassword()+" "+user.getRoles());
    }

}
