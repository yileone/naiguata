/**
 *
 */
package archivos;

/**
 * Clase que genera clave publica y privada . Asi como encripta y desencripta usandolos
 * @author yisheng
 *
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Claves {

	private static SecretKeySpec secretKey;
	private static byte[] key;
	private static Cipher rsa;
	private final static String secret = "e44iufGA_Hi34ja_9'8";

	public static String AESdecrypt(String strToDecrypt) {
		try {
			AESsetKey(secret);
			final Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (final Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	public static String AESencrypt(String strToEncrypt) {
		try {
			AESsetKey(secret);
			final Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (final Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	public static void AESsetKey(String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static String encrypt(String strToEncrypt) {
		try {
			setKey(secret);
			final Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (final Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	/**
	 *
	 * @param key
	 * @param fileName
	 * @throws Exception
	 */
	private static void saveKey(Key key, String fileName) throws Exception {
		final byte[] publicKeyBytes = key.getEncoded();
		final FileOutputStream fos = new FileOutputStream(fileName);
		fos.write(publicKeyBytes);
		fos.close();
	}

	public static void setKey(String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private PublicKey publicKey;

	private PrivateKey privateKey;

	public Claves() {
		try {
			rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 * @param encriptado
	 * @return
	 */
	public String desencripta(byte[] encriptado) {
		try {
			loadPrivateKey("privatekey.dat");
			rsa.init(Cipher.DECRYPT_MODE, privateKey);
			return rsa.doFinal(encriptado).toString();

		} catch (final InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		}

		// Se escribe el texto desencriptado
		return "";

	}

	/**
	 *
	 * @param text
	 * @return
	 */
	public String encripta(String text) {

		String result = "";
		byte[] encriptado;
		try {

			loadPublicKey("publickey.dat");
			rsa.init(Cipher.ENCRYPT_MODE, publicKey);
			encriptado = rsa.doFinal(text.getBytes());
			result = new String(encriptado);
		} catch (final InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 *
	 */
	public void generaKey() {
		KeyPairGenerator keyPairGenerator;
		try {
			keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			final KeyPair keyPair = keyPairGenerator.generateKeyPair();
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
			saveKey(publicKey, "publickey.dat");
			loadPublicKey("publickey.dat");

			// Se salva y recupera de fichero la clave privada
			saveKey(privateKey, "privatekey.dat");
			loadPrivateKey("privatekey.dat");

		} catch (final NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 *
	 * @return
	 */
	public String generarClave() {
		final String[] symbols = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
		final int length = 10;
		Random random;
		try {
			random = SecureRandom.getInstanceStrong();
			final StringBuilder sb = new StringBuilder(length);
			for (int i = 0; i < length; i++) {
				final int indexRandom = random.nextInt(symbols.length);
				sb.append(symbols[indexRandom]);
			}
			return sb.toString();
		} catch (final NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // as of JDK 8, this should return the strongest algorithm available to the JVM
		return "";
	}

	/**
	 *
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	private void loadPrivateKey(String fileName) throws Exception {
		final FileInputStream fis = new FileInputStream(fileName);
		final int numBtyes = fis.available();
		final byte[] bytes = new byte[numBtyes];
		fis.read(bytes);
		fis.close();

		final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		final KeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
		privateKey = keyFactory.generatePrivate(keySpec);

	}

	/**
	 *
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	private void loadPublicKey(String fileName) throws Exception {
		final FileInputStream fis = new FileInputStream(fileName);
		final int numBtyes = fis.available();
		final byte[] bytes = new byte[numBtyes];
		fis.read(bytes);
		fis.close();

		final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		final KeySpec keySpec = new X509EncodedKeySpec(bytes);
		publicKey = keyFactory.generatePublic(keySpec);
	}

}
