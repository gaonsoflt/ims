package com.gaonsoft.ims.domain;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Long id;
    private String userId;
    private String username;
    private String password;
    private String salt;
    
    public User(String userId, String username, String password) {
    	this.userId = userId;
    	this.username = username;
    	this.password = password;
    }
    
    public User(String userId, String username, String password, String[] roles) {
    	this.userId = userId;
    	this.username = username;
    	this.password = password;
    	this.roles = Arrays.asList(roles);
    }
    
    public User(String userId, String username, String password, String[] roles, String salt) {
    	this.userId = userId;
    	this.username = username;
    	this.password = password;
    	this.roles = Arrays.asList(roles);
    	this.salt = salt;
    }

    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
    }
    
    public String getUserId() {
		return userId;
	}

	public String getSalt() {
		return salt;
	}

	public List<String> getRoles() {
		return roles;
	}

	@Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
