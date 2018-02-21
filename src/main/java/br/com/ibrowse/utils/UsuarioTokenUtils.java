package br.com.ibrowse.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;

import com.google.gson.Gson;

import br.com.ibrowse.bean.jpa.UsuariosEntity;
import br.com.ibrowse.dto.InformacoesTokenDTO;

public class UsuarioTokenUtils {

	private static final String ALGORITHM = "HMACSHA256";

	private static final String SECRET_KEY = "CHAVESECRETA";

	private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

	private static final MacSigner macSigner = new MacSigner(ALGORITHM,
			new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM));

	private static final String SEPARATOR = ";";

	public static String encode(UsuariosEntity usuario) {
		String password = usuario.getSenha();
		String encodedPassword = encoder.encode(password);
		String validity = LocalDateTime.now().plus(60, ChronoUnit.MINUTES).toString();
		final String content = usuario.getNmUsuario() + SEPARATOR + encodedPassword + SEPARATOR + validity;
		Jwt encode = JwtHelper.encode(content, macSigner);
		return encode.getEncoded();
	}

	public static String encode(UsuariosEntity usuario, Integer timeValue, ChronoUnit timeUnit) {
		String password = usuario.getSenha();
		String encodedPassword = encoder.encode(password);
		String validity = LocalDateTime.now().plus(timeValue, timeUnit).toString();
		final String content = usuario.getNmUsuario() + SEPARATOR + encodedPassword + SEPARATOR + validity;
		Jwt encode = JwtHelper.encode(content, macSigner);
		return encode.getEncoded();
	}
	
	public static String encode(InformacoesTokenDTO token){
		Gson gson = new Gson();
		String payload = gson.toJson(token);
		String validity = LocalDateTime.now().plus(60, ChronoUnit.MINUTES).toString();
		final String content = payload + SEPARATOR + validity;
		Jwt encode = JwtHelper.encode(content, macSigner);
		return encode.getEncoded();
	}

	public static boolean validateToken(UsuariosEntity usuario, String token) {
		if (usuario == null) {
			return false;
		}
		String[] decoded = decode(token);
		String encodedPasswd = decoded[1];
		boolean passMatches = encoder.matches(usuario.getSenha(), encodedPasswd);
		String validity = decoded[2];
		LocalDateTime parse = LocalDateTime.parse(validity);
		boolean before = LocalDateTime.now().isBefore(parse);
		return passMatches && before;
	}

	public static String extractUsername(String token) {
		String[] decoded = decode(token);
		return decoded[0];
	}

	public static String[] decode(String token) {
		Jwt decode = JwtHelper.decodeAndVerify(token, macSigner);
		String claims = decode.getClaims();
		String[] split = claims.split(SEPARATOR);
		return split;
	}
	
	public static Long getCpf(String token){
		String[] decoded = decode(token);
		return Long.parseLong(decoded[0]);
	}
}
