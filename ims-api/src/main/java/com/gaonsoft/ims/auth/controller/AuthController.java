package com.gaonsoft.ims.auth.controller;

import static org.assertj.core.api.Assertions.fail;
import static org.springframework.http.ResponseEntity.ok;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaonsoft.ims.auth.vo.AuthenticationRequest;
import com.gaonsoft.ims.domain.User;
import com.gaonsoft.ims.security.encrypt.SHA;
import com.gaonsoft.ims.security.jwt.JwtTokenProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    UserDetailsService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody AuthenticationRequest data) throws NoSuchAlgorithmException {

        try {
            String username = data.getUsername();
            User user = (User) userService.loadUserByUsername(username);
            if(user != null) {
            	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, user.getPassword()));
				String hashedPassword = SHA.sha1(user.getSalt() + data.getPassword());
//				String p1 = SHA.sha1(data.getPassword());
//				String hashedPassword = SHA.sha1(user.getSalt() + p1);

				// 패스워드 확인
            	if(user.getPassword().equals(hashedPassword)) {
            		Claims claims = Jwts.claims();
            		claims.put("userId", user.getUserId());
            		claims.put("username", user.getUsername());
            		claims.put("roles", user.getRoles());

            		String token = jwtTokenProvider.createToken(claims);
            		
            		Map<String, Object> model = new HashMap<>();
            		model.put("username", user.getUsername());
            		model.put("userId", user.getUserId());
            		model.put("token", token);
            		return ok(model);
            	} else {
            		return fail("Incorrected password");
            	}
            }
            return fail("Not found user");
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
