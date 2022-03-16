package br.com.alura.forum.config.security;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.forum.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		
		Usuario logado = (Usuario) authentication.getPrincipal();
		LocalDate data = LocalDate.now();
		Date hoje = Date.valueOf(data);
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		
		return Jwts.builder()
				.setIssuer("API do Forum Alura")
				.setSubject(logado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public Boolean isValid(String token) {
		
		try {
			
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
			
		} catch (Exception e) {
			return false;
		}
		
	}

	public Long getIdUsuario(String token) {
		
		Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		
		return Long.parseLong(body.getSubject());
	}

}
