package com.epam.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
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
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@RequiredArgsConstructor
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  @Value("${app.security.tokenSigningKey}")
  private String tokenSigningKey;

  @Value("${app.security.validitySeconds.accessToken}")
  private int accessTokenValiditySeconds;

  @Value("${app.security.validitySeconds.refreshToken}")
  private int refreshTokenValiditySeconds;

  @Value("${app.security.client.webClient.clientId}")
  private String webClientId;

  @Value("${app.security.client.webClient.clientSecret}")
  private String webClientSecret;

  @Value("#{'${app.security.client.webClient.grantType}'.split(',')}")
  private List<String> webClientGrantTypes;

  @Value("#{'${app.security.client.webClient.scope}'.split(',')}")
  private List<String> webClientScopes;

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
        .withClient(webClientId)
//          .secret(passwordEncoder.encode(webClientSecret))
          .authorizedGrantTypes(webClientGrantTypes.toArray(new String[]{}))
          .scopes(webClientScopes.toArray(new String[]{}))
          .accessTokenValiditySeconds(accessTokenValiditySeconds)
          .refreshTokenValiditySeconds(refreshTokenValiditySeconds);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    endpoints
        .tokenStore(tokenStore())
        .authenticationManager(authenticationManager)
        .accessTokenConverter(accessTokenConverter());
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) {
    security
        .allowFormAuthenticationForClients();
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(accessTokenConverter());
  }

  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setSigningKey(tokenSigningKey);
    return converter;
  }

}