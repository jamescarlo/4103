package rem;

import griffon.core.artifact.GriffonService;
import griffon.core.resources.ResourceHandler;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonService;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.crypto.cipher.CryptoCipher;
import org.apache.commons.crypto.utils.Utils;

@javax.inject.Singleton
@ArtifactProviderFor(GriffonService.class)
public class CipherCrypt extends AbstractGriffonService {

	Properties properties = new Properties();

	final SecretKeySpec key = new SecretKeySpec(getUTF8Bytes("1234567890123456"), "AES");
	final IvParameterSpec iv = new IvParameterSpec(getUTF8Bytes("1234567890123456"));
	final String transform = "AES/CBC/PKCS5Padding";
	ByteBuffer outBuffer;
	int bufferSize = 1024;
	int updateBytes;
	int finalBytes;

	public byte[] encrypt(String text) {
		try {
			CryptoCipher encipher = Utils.getCipherInstance(transform, properties);
			ByteBuffer inBuffer = ByteBuffer.allocateDirect(bufferSize);
			outBuffer = ByteBuffer.allocateDirect(bufferSize);
			inBuffer.put(getUTF8Bytes(text));
			inBuffer.flip(); 
			encipher.init(Cipher.ENCRYPT_MODE, key, iv);
			updateBytes = encipher.update(inBuffer, outBuffer);
			finalBytes = encipher.doFinal(inBuffer, outBuffer);
		} catch (Exception e) {
			System.out.println(e);
		}
		return new byte[updateBytes + finalBytes];
	}

	public String decrypt(byte[] encoded) {
		String decrypted = "";
		try {
			CryptoCipher decipher = Utils.getCipherInstance(transform, properties);
			ByteBuffer decoded = ByteBuffer.allocateDirect(bufferSize);
			outBuffer.flip();
			outBuffer.duplicate().get(encoded);
			decipher.init(Cipher.DECRYPT_MODE, key, iv);
			decipher.update(outBuffer, decoded);
			decipher.doFinal(outBuffer, decoded);
			decoded.flip();
			decrypted = asString(decoded);
		} catch (Exception e) {
			System.out.println(e);
		}
		return decrypted;
	}

	private static byte[] getUTF8Bytes(String input) {
		return input.getBytes(StandardCharsets.UTF_8);
	}

	private static String asString(ByteBuffer buffer) {
		final ByteBuffer copy = buffer.duplicate();
		final byte[] bytes = new byte[copy.remaining()];
		copy.get(bytes);
		return new String(bytes, StandardCharsets.UTF_8);
	}
}