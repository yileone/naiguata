/**
 *
 */
package archivos;

/**
 * @author yisheng
 *
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Claves {

	private static Cipher rsa;

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
		byte[] bytesDesencriptados;
		try {
			rsa.init(Cipher.DECRYPT_MODE, privateKey);
			bytesDesencriptados = rsa.doFinal(encriptado);
			return new String(bytesDesencriptados);

		} catch (final InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
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
			rsa.init(Cipher.ENCRYPT_MODE, publicKey);
			encriptado = rsa.doFinal(text.getBytes());
			for (final byte b : encriptado) {
				result = result + Integer.toHexString(0xFF & b);
			}
		} catch (final InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
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
