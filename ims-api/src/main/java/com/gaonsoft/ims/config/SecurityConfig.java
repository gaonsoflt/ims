package com.gaonsoft.ims.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gaonsoft.ims.security.jwt.JwtConfigurer;
import com.gaonsoft.ims.security.jwt.JwtTokenProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @SuppressWarnings("deprecation")
	@Bean
    public PasswordEncoder noOpPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    
    @Override
	// configure(HttpSecurity http) 보다 우선순위가 높음, 보통 리소스 위주의 경로를 설정함
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/resources/**")
			.antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/auth/signin").permitAll()
                .antMatchers("/web/login").permitAll()
                .antMatchers("/web/logout").permitAll()
                .antMatchers("/web/**").permitAll()
//                .antMatchers("/api/test/open/**").permitAll()
//                .antMatchers("/api/test/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/**").authenticated()
                .anyRequest().authenticated()
            .and()
            .apply(new JwtConfigurer(jwtTokenProvider));
        //@formatter:on
    }


}