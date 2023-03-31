package com.greenart.lms_service.security.config;

import com.greenart.lms_service.security.filter.JwtAuthenticationFilter;
import com.greenart.lms_service.security.provider.JwtTokenProvider;
import com.greenart.lms_service.security.vo.PermitSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    private final PermitSettings permitSettings;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and()
                .httpBasic().disable().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                // 프론트 개발이 얼추 마무리되면 아래 3줄 삭제하고 아래 주석 부활시켜서 시큐리티 적용
                .authorizeHttpRequests()
                .requestMatchers("/**", "/api/login", "/swagger", "/swagger-ui", "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**").permitAll()
                .anyRequest().authenticated().and()
                // 프론트 개발이 얼추 마무리되면 위 3줄 삭제하고 아래 주석 부활시켜서 시큐리티 적용
                // .authorizeHttpRequests(authorize -> authorize
                //     .requestMatchers("/*", "/api/login", "/swagger", "/swagger-ui", "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**").permitAll()
                //     .requestMatchers("/api/mygrade/**").hasRole("S")
                //     .requestMatchers("/api/pro/**", "/api/lec/**", "/api/sco/**", "/api/final/**", "/api/atd/**").hasRole("P")
                //     .requestMatchers("/api/stf/lectures/**").hasRole("U")
                //     .requestMatchers("/api/timetable/**").hasAnyRole("S", "P")
                //     .anyRequest().authenticated()
                // )
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("*"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
