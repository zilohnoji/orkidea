package com.donatoordep.orkidea.configs;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.donatoordep.orkidea.ApplicationContextLoad;
import com.donatoordep.orkidea.entities.Client;
import com.donatoordep.orkidea.repositories.ClientRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTTokenAuthenticationService {

	/* Token validation timer | 2 Days */
	private static final long EXPIRATION_TIME = 172800000;

	/* Unique password component authentication */
	private static final String SECRET = "*JH240Ndewoinjce872?3w#%¨#!dpiuf==-edjnç";

	/* Prefix default for token */
	private static final String TOKEN_PREFIX = "Bearer";

	private static final String HEADER_STRING = "Authorization";

	public void addAuthentication(HttpServletResponse response, String email) throws Exception {

		/* Token builder */
		String JWT = Jwts.builder().setSubject(email)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SECRET).compact();

		String token = TOKEN_PREFIX + " " + JWT;

		response.addHeader(HEADER_STRING, token);

		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);

		if (token != null) {
			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJwt(token.replace(TOKEN_PREFIX, "")).getBody()
					.getSubject();
			if (user != null) {
				Client client = ApplicationContextLoad.getContext().getBean(ClientRepository.class)
						.findClientByEmail(user);

				if (client != null) {
					return new UsernamePasswordAuthenticationToken(
							client.getEmail(), 
							client.getPassword(),
							client.getAuthorities());
				}
			}
		} else {
			return null;
		}
		
		return null;
	}
}
