package vn.iotstar.listviewpractice;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView tvUsername, tvEmail;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Nếu chưa đăng nhập thì đá về trang Login
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginAPIActivity.class));
        }

        tvUsername = findViewById(R.id.tvUsernameProfile);
        tvEmail = findViewById(R.id.tvEmailProfile);
        btnLogout = findViewById(R.id.btnLogout);

        // Lấy thông tin user đã lưu
        User user = SharedPrefManager.getInstance(this).getUser();

        tvUsername.setText("Username: " + user.getUsername());
        tvEmail.setText("Email: " + user.getEmail());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
            }
        });
    }
}