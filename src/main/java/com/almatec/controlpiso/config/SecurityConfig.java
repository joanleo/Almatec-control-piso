package com.almatec.controlpiso.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.almatec.controlpiso.security.services.CustomUserDetailsService;


@Configuration
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true, 
		  jsr250Enabled = true)
public class SecurityConfig {
	
	@Bean
    UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
    
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(withDefaults());
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests(requests -> requests
                		.antMatchers("/css/**", "/js/**", "/imgs/**").permitAll()
                		.antMatchers("/login").permitAll()
                		.antMatchers("/api/**").permitAll()
                		.antMatchers("/produccion/operarios-registrados/**").permitAll()
                		.antMatchers("/operarios/generar-codigos-barra/**").permitAll()
                		.antMatchers("/centros-trabajo/generar-codigos-barra/**").permitAll()
                		.antMatchers("/ingenieria/crear-op/**").permitAll()
                		.antMatchers("/logout").permitAll()
                		.antMatchers("/comercial").hasAuthority("ACCESS_PEDIDOS")
                        .antMatchers("/comercial/pedidos/**").hasAuthority("ACCESS_ESTADO_PEDIDOS")
                        .antMatchers("/comercial/crear-op/**").hasAuthority("ACCESS_ESTADO_PEDIDOS")
                        .antMatchers("/ingenieria/status/proyectos").hasAuthority("ACCESS_ESTATUS_DE_PROYECTOS")
                        .antMatchers("/ingenieria/op/**").hasAuthority("ACCESS_ESTATUS_DE_PROYECTOS")
                        .antMatchers("/ingenieria/memos/nuevo").hasAuthority("ACCESS_CREAR_MEMO")
                        .antMatchers(HttpMethod.POST, "/ingenieria/memos").hasAuthority("ACCESS_CREAR_MEMO")
                        .antMatchers(HttpMethod.POST, "/ingenieria/memos/{idMemo}/aprobar-memo").hasAuthority("ACCESS_APROBAR_MEMO")
                        .antMatchers("/ingenieria/memos/aprobar").hasAuthority("ACCESS_APROBAR_MEMO")
                        .antMatchers("/ingenieria/memos").hasAuthority("ACCESS_HISTORIAL_MEMOS")
                        .antMatchers("/programacion/**").hasAuthority("ACCESS_PRIORIDAD")
                        .antMatchers("/programacion/consulta-materia-prima").hasAuthority("ACCESS_CONSULTA_MATERIA_PRIMA_PROGRAMACION")
                        .antMatchers("/programacion/resumen-fabricado").hasAuthority("ACCESS_RESUMEN_FABRICADO")
                        .antMatchers("/produccion/**").hasAuthority("ACCESS_PLANTA")
                        .antMatchers("/centros-trabajo/carga").hasAuthority("ACCESS_CAGA_X_CT")
                        .antMatchers("/produccion/materia-prima/solicitud").hasAuthority("ACCESS_CREAR_SOLICITUD_MATERIA_PRIMA")
                        .antMatchers("/produccion/operarios-registrados").hasAuthority("ACCESS_OPERARIOS_REGISTRADOS")
                        .antMatchers("/produccion/impresion-etiquetas").hasAuthority("ACCESS_IMPRESION_DE_ETIQUETAS")
                        .antMatchers("/informes/produccion/excel-general").hasAuthority("ACCESS_GENERAR_INFORME_GENERAL")
                        .antMatchers("/informes/produccion/excel-mes").hasAuthority("ACCESS_GENERAR_INFORME_MENSUAL")
                        .antMatchers("/almacen/solicitudes/**").hasAuthority("ACCESS_VER_SOLICITUDES_DE_MATERIA_PRIMA")
                        .antMatchers("/almacen/detalle/**").hasAuthority("ACCESS_VER_SOLICITUDES_DE_MATERIA_PRIMA")
                        .antMatchers("/almacen/remisiones/nueva").hasAuthority("ACCESS_CREAR_REMISION")
                        .antMatchers("/almacen/remisiones/**").hasAuthority("ACCESS_VER_REMISIONES")
                        .antMatchers("/almacen/consulta-materia-prima").hasAuthority("ACCESS_CONSULTA_MATERIA_PRIMA_ALMACEN")
                        .antMatchers("/calidad/formularios").hasAuthority("ACCESS_LISTAR_REPORTES_CALIDAD")
                        .antMatchers("/centros-trabajo").hasAuthority("ACCESS_CENTROS_DE_TRABAJO")
                        .antMatchers("/operarios/listar").hasAuthority("ACCESS_OPERARIOS")
                        .antMatchers("/operarios/editar/*").hasAuthority("ACCESS_OPERARIOS")
                        .antMatchers(HttpMethod.POST, "/operarios/guardar").hasAuthority("ACCESS_OPERARIOS")
                        .antMatchers(HttpMethod.DELETE, "/operarios/*").hasAuthority("ACCESS_OPERARIOS")
                        .antMatchers("/roles/**").hasAuthority("ACCESS_PERFILES_O_ROLES")
                        .antMatchers("/usuarios/**").hasAuthority("ACCESS_USUARIOS")
                        .antMatchers("/parametros/listar").hasAuthority("ACCESS_PARAMETROS")
                        .antMatchers("/**").authenticated()
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/",true).permitAll()
                        )
                
                .logout(logout -> logout.logoutSuccessUrl("/login?logout").permitAll());
		return http.build();
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH", "PUT", "DELETE", "OPTIONS", "HEAD"));
        configuration.setAllowedHeaders(Arrays.asList("Origin,Accept", "X-Requested-With", "Content-Type", "Access-Control-Request-Method", "Access-Control-Request-Headers","Authorization"));
        configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")); 
        configuration.setMaxAge(3600L);
        configuration.addAllowedOrigin("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
    @Configuration
    public class WebConfig implements WebMvcConfigurer {

        @Override
        public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
            configurer.defaultContentType(MediaType.APPLICATION_JSON)
                      .mediaType("mjs", MediaType.valueOf("application/javascript"));
        }
    }

}
