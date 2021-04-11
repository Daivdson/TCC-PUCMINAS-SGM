package br.gov.apisgm.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtTokenProvider jwtTokenProvider;
  
  @Autowired  
  private AuthenticationManager authenticationManager;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable();
    http.cors().and()
    .sessionManagement()
    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    .and()
    .authorizeRequests().antMatchers(
    		"/api/cidadao/iptu",
    		"/api/cidadao/itr",
    		"/docs/**",
    		"/swagger**",
    		"/swagger-resources/**",
    		"/v2/api-docs",
    		"/webjars/**"
    		)
    .permitAll()
    .anyRequest().authenticated();

    http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

  }
  
  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
  
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
      final CorsConfiguration config = new CorsConfiguration();
      config.setAllowedOrigins(Arrays.asList("*"));
      config.setAllowedMethods(Arrays.asList("*"));
      config.setAllowCredentials(true);
      config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

      final UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
      configSource.registerCorsConfiguration("/**", config);

      return configSource;
  }


}
