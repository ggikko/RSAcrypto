package ggikko.me.rsacryptotestapp.crypto;

import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by ggikko on 2016. 9. 8..
 */
public class RSA {

  public static PublicKey getRSAPublicPEMKeyFromString(String publicPEMKey) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException {

    byte[] decodedPublicPEMKeyBytes = Base64.decode(publicPEMKey.getBytes("UTF-8"), Base64.DEFAULT);
    String publicDERKeyString = stripPublicKeyHeaders(new String(decodedPublicPEMKeyBytes, "UTF-8"));
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    byte[] publicDERKeyBytes = Base64.decode(publicDERKeyString.getBytes("UTF-8"), Base64.DEFAULT);
    KeySpec x509KeySpec = new X509EncodedKeySpec(publicDERKeyBytes);
    return keyFactory.generatePublic(x509KeySpec);
  }

  private static String stripPublicKeyHeaders(String key) {
    // strip ASN.1 public key headers from the key string
    key = key.replace("-----BEGIN PUBLIC KEY-----", "");
    key = key.replace("-----END PUBLIC KEY-----", "");
    key = key.replace("\r", "");
    key = key.replace("\r", "");
    key = key.replace("\n", "");
    key = key.replace("\t", "");
    key = key.replace(" ", "");
    return key.trim();
  }

  public static String RSAEncrypt(final String publicPEMKey, final String plain) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeySpecException {
    PublicKey publicKey = getRSAPublicPEMKeyFromString(publicPEMKey);
    return RSAEncrypt(publicKey, plain);
  }

  public static String RSAEncrypt(final PublicKey publicKey, final String plain) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {

    if (publicKey != null) {
      // Supported Cipher: https://developer.android.com/reference/javax/crypto/Cipher.html
      final Cipher cipher = Cipher.getInstance("RSA/NONE/PKCS1PADDING");
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
      byte[] encryptedBytes = cipher.doFinal(plain.getBytes());
      Log.d("BYTES", new String(encryptedBytes));
      return new String(Base64.encode(encryptedBytes, 0));
    } else
      return null;
  }
}
