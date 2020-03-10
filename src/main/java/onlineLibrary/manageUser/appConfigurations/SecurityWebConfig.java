package onlineLibrary.manageUser.appConfigurations;

import onlineLibrary.manageUser.models.User;
import onlineLibrary.manageUser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Optional;

@Configuration
@EnableWebSecurity

public class SecurityWebConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Autowired
    private UserService userService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth,
                                        @RequestParam("s") Optional<String> s,
                                        @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
                                            @PageableDefault(size = 5) Pageable pageable)
            throws Exception {
      Page<User> names;
      Page<User> passwords;
      Page<User> roles;
      if(s.isPresent()) {
          names = userService.findAllByName(s.get(), pageable);
          passwords = userService.findAllByPassword(s.get(), pageable);
          roles = userService.findAllByRole(s.get(), pageable);

          auth.inMemoryAuthentication().
                  withUser(String.valueOf(names)).
                  password(String.valueOf(passwords)).
                  roles(String.valueOf(roles));
      }
//        auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN","DBA");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home").access("hasRole('GUEST')")
                .antMatchers("/user/**").access("hasRole('USER')")
                .antMatchers("/admin/**").access("hasRole('USER') and hasRole('ADMIN')")
                .and().formLogin().successHandler(customSuccessHandler)
                .usernameParameter("ssoId").passwordParameter("password")
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/Access_Denied");
    }
}
