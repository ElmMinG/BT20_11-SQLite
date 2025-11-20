package vn.iotstar.listviewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SharedPreferencesActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    CheckBox cbRemember;
    Button btnLogin;
    TextView tvStatus;

    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        cbRemember = findViewById(R.id.cb_remember);
        btnLogin = findViewById(R.id.btn_login);
        tvStatus = findViewById(R.id.tv_status);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean isRemembered = settings.getBoolean("REMEMBER_ME", false);

        if (isRemembered) {
            String savedUser = settings.getString("USERNAME", "");
            String savedPass = settings.getString("PASSWORD", "");

            edtUsername.setText(savedUser);
            edtPassword.setText(savedPass);
            cbRemember.setChecked(true);
            tvStatus.setText("Đã tự động điền thông tin đã lưu!");
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = edtUsername.getText().toString();
                String p = edtPassword.getText().toString();

                if (cbRemember.isChecked()) {
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("USERNAME", u);
                    editor.putString("PASSWORD", p);
                    editor.putBoolean("REMEMBER_ME", true);

                    editor.apply();

                    Toast.makeText(SharedPreferencesActivity.this, "Đã lưu thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = settings.edit();
                    editor.clear(); // Xóa hết
                    editor.apply();
                    Toast.makeText(SharedPreferencesActivity.this, "Đã xóa thông tin lưu trữ!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}