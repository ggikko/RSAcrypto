package ggikko.me.rsacryptotestapp.crypto;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by admin on 2016. 9. 8..
 */
public class RSADecrypt {

  public static String decrypt(String privateK, String text) throws Exception{
    Cipher cipher = Cipher.getInstance("RSA/NONE/PKCS1PADDING");
    PKCS8EncodedKeySpec rkeySpec = new PKCS8EncodedKeySpec(hexToByteArray(privateK));
    KeyFactory rkeyFactory = KeyFactory.getInstance("RSA");
    PrivateKey privateKey = null;
    try {
      privateKey = rkeyFactory.generatePrivate(rkeySpec);
      System.out.println("privKeyHex:"+byteArrayToHex(privateKey.getEncoded()));
    } catch (InvalidKeySpecException e) {
      e.printStackTrace();
    }
    // 개인키를 가지고있는쪽에서 복호화
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte[] plainText = cipher.doFinal(hexToByteArray(text));
    return new String(plainText);
  }

  // hex string to byte[]
  public static byte[] hexToByteArray(String hex) {
    if (hex == null || hex.length() == 0) {
      return null;
    }
    byte[] ba = new byte[hex.length() / 2];
    for (int i = 0; i < ba.length; i++) {
      ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
    }
    return ba;
  }

  // byte[] to hex sting
  public static String byteArrayToHex(byte[] ba) {
    if (ba == null || ba.length == 0) {
      return null;
    }
    StringBuffer sb = new StringBuffer(ba.length * 2);
    String hexNumber;
    for (int x = 0; x < ba.length; x++) {
      hexNumber = "0" + Integer.toHexString(0xff & ba[x]);

      sb.append(hexNumber.substring(hexNumber.length() - 2));
    }
    return sb.toString();
  }

}
