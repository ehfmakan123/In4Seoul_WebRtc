package com.ssafy.config;

import com.ssafy.api.service.AdminService;
import com.ssafy.api.service.StaffService;
import com.ssafy.common.auth.JwtAuthenticationFilter;
import com.ssafy.common.auth.SsafyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 인증(authentication) 와 인가(authorization) 처리를 위한 스프링 시큐리티 설정 정의.
 */
@Configuration
@EnableWebSecurity  // 기본적인 Web보안을 활성화 하겠다는 어노테이션
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {  //추가적인 설정을 위해서 WebSecurityConfigurerAdapter를 상속받는다
    @Autowired
    private SsafyUserDetailService ssafyUserDetailService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private StaffService staffService;

    // Password 인코딩 방식에 BCrypt 암호화 방식 사용
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }





    // DAO 기반으로 Authentication Provider를 생성
    // BCrypt Password Encoder와 UserDetailService 구현체를 설정
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.ssafyUserDetailService);
        return daoAuthenticationProvider;
    }

    // DAO 기반의 Authentication Provider가 적용되도록 설정
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }




//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .httpBasic().disable()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 사용 하지않음
//                .and()
//                .addFilter(new JwtAuthenticationFilter(authenticationManager(), userService)) //HTTP 요청에 JWT 토큰 인증 필터를 거치도록 필터를 추가
//                .authorizeRequests()
//                .anyRequest().hasRole("ADMIN")
//                .and().cors();
//
//
//        /*
//               antMatchers = 특정 리소스에 대해서 권한을 설정한다
//               permitAll = 설정한 리소스의 접근을 인증절차 없이 허용한다는 의미
//
//
//
//         */
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 사용 하지않음
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(),adminService,staffService)) //HTTP 요청에 JWT 토큰 인증 필터를 거치도록 필터를 추가
                .authorizeRequests() //HttpServletRequest를 사용하는 요청들에 대한 접근제한을 설정하겠다는 의미이다
                .antMatchers("/api/staff/idcheck").permitAll()
                .antMatchers("/api/staff/signup").permitAll()
                .antMatchers("/api/staff/login").permitAll()
                .antMatchers("/api/admin/login").permitAll()
                .antMatchers("/api/desk/login").permitAll()
                .antMatchers("/api/desk/**").hasAnyAuthority("ROLE_DESK")
                .antMatchers("/api/admin/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/api/staff/**").hasAnyAuthority("ROLE_STAFF")
                  //인증이 필요한 URL과 필요하지 않은 URL에 대하여 설정  // 이 요청에 대해서는 인증을 받아야한다
//    	        	    .anyRequest().authenticated() // 나머지 요청에 대해서는 인증절차 없이 접근 허용
                .and().cors();

        /*
               antMatchers = 특정 리소스에 대해서 권한을 설정한다
               permitAll = 설정한 리소스의 접근을 인증절차 없이 허용한다는 의미
         */
    }
}