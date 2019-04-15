package com.gaonsoft.ims.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gaonsoft.ims.domain.User;
import com.gaonsoft.ims.mapper.UserMapper;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User user = null;
    	Map<String, Object> param = new HashMap<>();
    	param.put("username", username);
    	try {
			List<Map<String, Object>> users = userMapper.retrieveUsers(param);
			if(!users.isEmpty()) {
				Map<String, Object> map = users.get(0);
				String userId = map.get("userId").toString();
				String _username = map.get("username").toString();
				String password = map.get("password").toString();
				String salt = (map.get("salt") != null) ? map.get("salt").toString() : "";
				String role = map.get("admin").equals(true) ? "ADMIN" : "USER";
				user = new User(userId, _username, password, new String[] { role }, salt);
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return user;
    }
}
