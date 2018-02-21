package br.com.ibrowse.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.jboss.jandex.Main;

public class CriptografiaUtils {
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(criptografarSenhaSHA1("mustarda"));
	}

	public static String criptografarSenhaSHA1(String senha) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
		String s = hash.toString(16);
		if (s.length() % 2 != 0)
			s = "0" + s;
		//s = s.toUpperCase();
		return s;
	}
}
