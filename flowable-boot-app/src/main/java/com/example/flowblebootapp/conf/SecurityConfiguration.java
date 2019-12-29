/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.flowblebootapp.conf;

import org.flowable.rest.security.BasicAuthenticationProvider;
import org.flowable.rest.security.SecurityConstants;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public AuthenticationProvider authenticationProvider() {
        BasicAuthenticationProvider basicAuthenticationProvider = new BasicAuthenticationProvider();
        basicAuthenticationProvider.setVerifyRestApiPrivilege(true);
        return basicAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        HttpSecurity httpSecurity = http.authenticationProvider(authenticationProvider())
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable();

        httpSecurity
            .authorizeRequests()
            .requestMatchers(EndpointRequest.to(InfoEndpoint.class, HealthEndpoint.class)).authenticated()
            .requestMatchers(EndpointRequest.toAnyEndpoint()).hasAnyAuthority(SecurityConstants.ACCESS_ADMIN);

        httpSecurity
            .authorizeRequests()
            .anyRequest()
            .hasAuthority(SecurityConstants.PRIVILEGE_ACCESS_REST_API).and().httpBasic();

    }

}
