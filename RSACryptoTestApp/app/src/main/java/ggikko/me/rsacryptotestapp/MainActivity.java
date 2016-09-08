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

public class MainActivity extends AppCompatActivity {

  private TextView word;
  private TextView encrypted_word;
  private EditText edit_word;

  private String TEMP_PUBLIC_KEY ="LS0tLS1CRUdJTiBQVUJMSUMgS0VZLS0tLS0KTUlHZk1BMEdDU3FHU0liM0RRRUJBUVVBQTRHTkFEQ0JpUUtCZ1FEWXdQZVBJOEtDTldYc3lsbGtud2lrTko5dQphOHFZa1o2eXU1V28zTGUvS01YQjZHZytEd24vYUNnQkRHTFdvT1lhbnR5ZVIzd2lpdkprb3JjdFNENDFtMkVQCks2ZWpkWlEvNWxFa1B4YnY3OC8zWndBSlZmNEdxWUNqNmc0b25jY3JFNWgrRDlUdzVWMG56Zmg3MGdud0RLakkKM3pScHcvcXJjSmYvS09uaEpRSURBUUFCCi0tLS0tRU5EIFBVQkxJQyBLRVktLS0tLQ==";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    edit_word = (EditText) findViewById(R.id.edit_word);
    word = (TextView) findViewById(R.id.word);
    encrypted_word = (TextView) findViewById(R.id.encrypted_word);

    edit_word.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String w = charSequence.toString();
        word.setText(w);
        try {
          String encrypted_w = RSA.RSAEncrypt(TEMP_PUBLIC_KEY, charSequence.toString());
          encrypted_word.setText(encrypted_w);
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
