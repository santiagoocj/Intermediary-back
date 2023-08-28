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
			.antMatchers(HttpMethod.POST, "/api/empresas/registro/{id-solicitud-registro}", "/api/solicitud-registro/{id-empresa}/{id-representante}").permitAll()
			.antMatchers(HttpMethod.POST, "/api/registro", "/api/representantelegal").permitAll()
			.antMatchers(HttpMethod.POST, "api/registro/documento/{empresa}").permitAll()
			.antMatchers(HttpMethod.POST, "api/documento").permitAll()
			.antMatchers(HttpMethod.GET, "/api/producto/{idProducto}", "/api/producto/activo/page/{page}", "/api/producto/categoria/{categoria}/{page}").permitAll()
			.antMatchers(HttpMethod.GET, "/api/categorias").permitAll()
			.antMatchers(HttpMethod.GET, "/api/membresia/lista").permitAll()
			.antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll()
			.antMatchers("/h2-console/**").permitAll()
			.anyRequest().authenticated();
		// Desactivar X-frame-options para visualizar base de datos en memoria
		http.headers().frameOptions().disable();
	}	
}
