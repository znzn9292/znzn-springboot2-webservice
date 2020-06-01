package com.znzn.book.springboot.config.auth;

import com.znzn.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity      // Spring Security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable().headers().frameOptions().disable()
                .and()
                    // URL 별 권한 관리 설정 옵션의 시작 (시큐리티 처리에 HttpServletRequest를 이용한다는 것을 의미)
                    .authorizeRequests()

                    /* 권한 관리 대상을 지정하는 옵션
                       permitAll() 옵션을 통해 전체 열람 권한을 주고,
                       "/api/v1/**" 주소를 가진 api 는 USER 권한 부여
                     */
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())

                    // 설정된 값들 이외 나머지 URL (authenticated() - 모두 인증된 사용자들에게만 허용)
                    .anyRequest().authenticated()
                .and()
                    .logout()
                         // 로그아웃 성공시 "/" 주소로 이동
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);
    }
}
