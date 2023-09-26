package pl.maciejak.charity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import pl.maciejak.charity.service.LoginSuccessHandler;

import java.util.List;

import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.FORWARD;

@Configuration
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector handlerMappingIntrospector) throws Exception {

        MvcRequestMatcher.Builder mvcMatcherBuilder =
                new MvcRequestMatcher
                        .Builder(handlerMappingIntrospector);


        http.csrf(AbstractHttpConfigurer::disable);

        http.cors(c -> c.configurationSource(req -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowedMethods(List.of("*"));
            corsConfiguration.setAllowedHeaders(List.of("*"));
            corsConfiguration.addAllowedOrigin("http://localhost:8080");
            corsConfiguration.setAllowCredentials(true);
            return corsConfiguration;
        }));

        http.authorizeHttpRequests(
                c -> c.dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/resources/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/WEB-INF/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/register")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/form")).hasAnyRole("USER", "ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern("/profile/**")).hasAnyRole("USER", "ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern("/donations/**")).hasAnyRole("USER", "ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern("/admin/**")).hasRole("ADMIN")
        );

        http.formLogin(login ->
                login.loginPage("/login")
                        .defaultSuccessUrl("/form")
                        .successHandler(new LoginSuccessHandler())
                        .permitAll()
        );

        http.logout((logout) -> logout.logoutUrl("/logout").permitAll());

        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

}