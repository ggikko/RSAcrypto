package ggikko.me.rsacryptotestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import ggikko.me.rsacryptotestapp.crypto.RSA;
import ggikko.me.rsacryptotestapp.crypto.RSADecrypt;

public class MainActivity extends AppCompatActivity {

  private TextView word;
  private TextView encrypted_word;
  private TextView decrypted_word;
  private EditText edit_word;

  private String TEMP_PUBLIC_KEY ="LS0tLS1CRUdJTiBQVUJMSUMgS0VZLS0tLS0KTUlHZk1BMEdDU3FHU0liM0RRRUJBUVVBQTRHTkFEQ0JpUUtCZ1FEWXdQZVBJOEtDTldYc3lsbGtud2lrTko5dQphOHFZa1o2eXU1V28zTGUvS01YQjZHZytEd24vYUNnQkRHTFdvT1lhbnR5ZVIzd2lpdkprb3JjdFNENDFtMkVQCks2ZWpkWlEvNWxFa1B4YnY3OC8zWndBSlZmNEdxWUNqNmc0b25jY3JFNWgrRDlUdzVWMG56Zmg3MGdud0RLakkKM3pScHcvcXJjSmYvS09uaEpRSURBUUFCCi0tLS0tRU5EIFBVQkxJQyBLRVktLS0tLQ==";
  private String TEMP_SALT = "b19e8583-b919-485c-8f6d-e270bf4253a8";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    edit_word = (EditText) findViewById(R.id.edit_word);
    word = (TextView) findViewById(R.id.word);
    encrypted_word = (TextView) findViewById(R.id.encrypted_word);
    decrypted_word = (TextView) findViewById(R.id.decrypted_word);

    edit_word.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String w = charSequence.toString();
        word.setText(w);
        try {

          //Encrypt
          String encrypt_w = RSA.RSAEncrypt(TEMP_PUBLIC_KEY, charSequence.toString() + "TEMP_SALT");
          encrypted_word.setText(encrypt_w);

          //Decrypt
//          String temporaryPrivatekey = "";
//          String decrypt_w = RSADecrypt.decrypt(temporaryPrivatekey, encrypt_w);
//          decrypted_word.setText(decrypt_w);

        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });
  }
}
