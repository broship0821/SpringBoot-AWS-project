package com.peter.broship.boot.config.auth;

import com.peter.broship.boot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity//Spring security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable().headers().frameOptions().disable()// h2-console 화면 사용하기 위함
                .and()
                    .authorizeRequests()//URL별 권한 관리를 설정하는 옵션의 시작점점
                    .antMatchers("/", "/css/**","/images/**","/js/**","/h2-console/**").permitAll()//전체 열람 가능
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())//USER 권한을 가진 사람만 가능
                    .anyRequest().authenticated()//나머지 URL은 인증된 사용자들에게만 허용
                .and()
                    .logout().logoutSuccessUrl("/")//로그아웃 성공시 "/"
                .and()
                    .oauth2Login()//로그인 설정 시작
                        .userInfoEndpoint()//로그인 성공 이후 사용자 정보 가져옴
                            .userService(customOAuth2UserService);//소셜 로그인 성공시 후속 조치 진행할 UserService 인터페이스의 구현체 등록, 소셜 서비스에서 사용자 정보를 가져온 후 추가로 진행하고자 하는 기능 명시 가능
    }
}
