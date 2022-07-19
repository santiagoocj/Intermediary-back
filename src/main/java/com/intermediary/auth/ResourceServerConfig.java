package com.intermediary.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			/*.antMatchers(HttpMethod.POST, "/api/send").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/api/empresas").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/api/empresas/{idEmpresa}").hasRole("ADMIN")
			.antMatchers(HttpMethod.PUT, "/api/empresas/{idEmpresa}").hasAnyRole("ADMIN","EMPRESA")
			.antMatchers(HttpMethod.POST, "/api/empresas/{idRepresentante}/{idMembresia}/{idRegistro}").hasAnyRole("ADMIN","EMPRESA")
			.antMatchers(HttpMethod.GET, "/api/registro").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/api/representantelegal/{id}").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/api/solicitud-registro").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/api/solicitud-registro/{id}").hasRole("ADMIN")*/
			.antMatchers(HttpMethod.POST, "/api/empresas/registro/{id-solicitud-registro}", "/api/solicitud-registro/{id-empresa}/{id-representante}").permitAll()
			.antMatchers(HttpMethod.POST, "/api/registro", "/api/representantelegal").permitAll()
			.antMatchers(HttpMethod.GET, "/api/producto/{idProducto}").permitAll()
			.antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll()
			.antMatchers("/h2-console/**").permitAll()
			.anyRequest().authenticated();
		// Desactivar X-frame-options para visualizar base de datos en memoria
		http.headers().frameOptions().disable();
	}	
}
