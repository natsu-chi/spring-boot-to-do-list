package com.chi.demo.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {
    // LDAP or DB
    // InMemoryUserDetailsManager(UserDetails.. users)
    // UserDetails 是 interface，所以用 User (Builder) 建構物件

    // but withDefaultPasswordEncoder 是不推薦用法

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {
        // UserDetails userDetails = User.withDefaultPasswordEncoder()
        // .username("chi")
        // .password("hithere")
        // .roles("USER", "ADMIN")
        // .build();

        UserDetails userDetail1 = createOneUser("chi", "hithere");
        UserDetails userDetail2 = createOneUser("web", "hithere");
        
        return new InMemoryUserDetailsManager(userDetail1, userDetail2);
    }

    private UserDetails createOneUser(String username, String password) {
        Function<String, String> passwordEncoder = input->passwordEncoder().encode(input);
        UserDetails userDetails = User.builder()
                                    .passwordEncoder(passwordEncoder)
                                    .username(username)
                                    .password(password)
                                    .roles("USER", "ADMIN")
                                    .build();
        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                auth->auth.anyRequest().authenticated());
        http.formLogin(withDefaults());

        // H2 一開始不能開是因為所有 urls 都被 Security 保護，都會導到 login page
        // 這邊做的是覆蓋接口（上面三行補齊基本功能，下面改設定）
        http.csrf().disable(); // 禁用 csrf
        http.headers().frameOptions().disable(); // 禁用
        return http.build();
    }
    
}