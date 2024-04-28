package JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.SecureRandom;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class GeraToken {
	public String retornaToken(String role, String user) throws JSONException {
		Date expirationDate = new Date(System.currentTimeMillis() + 3600000);

		String chaveSecreta = geraPalavraSecreta();
		
		JSONObject subjects = new JSONObject();
		subjects.put("role", role);	
		subjects.put("username", user);

		String jwt = Jwts.builder()
				.claim("subjects", subjects.toString())
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, chaveSecreta).compact();

		return jwt;
	}

	protected String geraPalavraSecreta() {
		String caracteresValidos = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

		SecureRandom aleatorizar = new SecureRandom();

		StringBuilder secret = new StringBuilder();

		for (int i = 0; i < 256; i++) {
			int randomIndex = aleatorizar.nextInt(caracteresValidos.length());

			secret.append(caracteresValidos.charAt(randomIndex));
		}

		return secret.toString();
	}
}