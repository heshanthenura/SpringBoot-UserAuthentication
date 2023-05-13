package com.heshanthenura.userauthentication.Authentication;

import com.heshanthenura.userauthentication.Database.SQLServices;
import com.heshanthenura.userauthentication.Database.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private SQLServices sqlServices;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=null;
        try{
             user = sqlServices.findUserByUsername(username);
            if (user ==null){
                throw new UsernameNotFoundException("User not Found");
            }
            return new CustomUserDetails(user);
        }catch (Exception e){
            throw new UsernameNotFoundException("User not Found");
        }

    }

}
