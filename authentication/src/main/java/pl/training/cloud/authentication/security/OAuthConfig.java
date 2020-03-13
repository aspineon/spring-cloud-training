package pl.training.cloud.authentication.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import pl.training.cloud.authentication.UsersService;

@EnableAuthorizationServer
@Configuration
@RequiredArgsConstructor
public class OAuthConfig extends AuthorizationServerConfigurerAdapter {

    private static final String CLIENT_ID = "training";
    private static final String PASSWORD_GRANT_TYPE = "password";
    private static final String SCOPE = "api";

    private final JwtAccessTokenConverter jwtAccessTokenConverter;
    private final TokenStore tokenStore;
    private final UsersService usersService;
    private final AuthenticationManager authenticationManagerBean;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
       endpoints.accessTokenConverter(jwtAccessTokenConverter)
               .tokenStore(tokenStore)
               .authenticationManager(authenticationManagerBean)
               .userDetailsService(usersService);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(CLIENT_ID)
                .authorizedGrantTypes(PASSWORD_GRANT_TYPE)
                .scopes(SCOPE);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
       security.allowFormAuthenticationForClients().passwordEncoder(passwordEncoder);
    }

}
