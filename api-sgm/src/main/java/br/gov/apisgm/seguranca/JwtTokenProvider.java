package br.gov.apisgm.seguranca;

import javax.servlet.http.HttpServletRequest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import br.gov.apisgm.aplicacao.dominio.Usuario;
import br.gov.apisgm.aplicacao.exception.CustomException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {

	@Value("${security.jwt.url}")
	  private String host;
		  

	  public Authentication getAutenticacao(String token) {
		    Usuario usuario = consultaToken(token);
		    
		    UserDetails userDetails = User
		    .withUsername(token)
		    .username(usuario.getNome())
		    .authorities(usuario.getRoles())
		    .password("teste")
		    .accountExpired(false)
		    .accountLocked(false)
		    .credentialsExpired(false)
		    .disabled(false)
		    .build();
		    
	    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	  }

	  public String configuraHeaderToken(HttpServletRequest req) {
	    String bearerToken = req.getHeader("Authorization");
	    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
	      return bearerToken.substring(7);
	    }
	    return null;
	  }

	  public boolean isTokenValido(String token) {
	    try {
	    	consultaToken(token);
	      return true;
	    } catch (JwtException | IllegalArgumentException e) {
	      throw new CustomException("Expirado ou invalido JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  
	  private Usuario consultaToken(String token) {
		  RestTemplate restTemplate = new RestTemplate();
	 		String url = host+"/me";
		   	try {
		   		String accessToken = "Bearer "+token; 

		        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		        headers.add("Authorization", accessToken);

		        RequestEntity<Object> request = new RequestEntity<>(headers, HttpMethod.GET, URI.create(url));

		        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
		        
		        Gson gson = new Gson();
		        
		        Usuario usuario = gson.fromJson(response.getBody(), Usuario.class);
		   		
		   		return usuario;
		   		
			} catch (Exception e) {
				log.error("Erro ao buscar dados do usuário: "+e.getMessage());
				return null;
			}
		}
}
