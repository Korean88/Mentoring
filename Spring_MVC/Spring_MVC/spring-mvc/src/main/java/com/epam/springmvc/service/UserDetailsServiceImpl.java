package com.epam.springmvc.service;

import com.epam.springmvc.model.Role;
import com.epam.springmvc.model.User;
import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Andrey on 14.05.2016.
 */
@Service(value = "customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(UserDetailsServiceImpl.class);

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LOGGER.info("loadUserByUsername() method was accessed");
        User user = userService.findByLogin(s);
        if (user == null) {
            throw new UsernameNotFoundException("User with login " + s + " was not found");
        }
        org.springframework.security.core.userdetails.User res = new org.springframework.security.core.userdetails.User(
                user.getLogin(), user.getPassword(), fetchAuthorities(user));
        return res;
    }

    private Collection<? extends GrantedAuthority> fetchAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role r : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + r.getName()));
        }
        return authorities;
    }
}
