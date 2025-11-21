package vn.iotstar.listviewpractice;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginAPIActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_api);

        // Nếu đã đăng nhập rồi thì vào thẳng Profile
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
            return;
        }

        edtUsername = findViewById(R.id.edtUsernameAPI);
        edtPassword = findViewById(R.id.edtPasswordAPI);
        btnLogin = findViewById(R.id.btnLoginAPI);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    private void userLogin() {
        final String username = edtUsername.getText().toString();
        final String password = edtPassword.getText().toString();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // URL API
        String url = "https://gist.githubusercontent.com/kienvip/9325f9d7f6f188cc3c882c4d58b31884/raw/login_success.json";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // In log ra để xem server trả về cái gì
                        Log.d("API_RESPONSE", response);

                        try {
                            JSONObject obj = new JSONObject(response);

                            // Kiểm tra xem có lỗi không
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                // Lấy object user từ JSON
                                JSONObject userJson = obj.getJSONObject("user");

                                // Tạo đối tượng User
                                User user = new User(
                                        userJson.getInt("id"),
                                        userJson.getString("username"),
                                        userJson.getString("email"),
                                        userJson.getString("gender")
                                );

                                // Lưu vào SharedPref
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                                // Chuyển sang Profile
                                finish();
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Lỗi xử lý JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Hiển thị lỗi chi tiết
                        Toast.makeText(getApplicationContext(), "Lỗi: " + error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                });

        // Thêm request vào hàng đợi (Dòng này phải nằm ngoài ErrorListener)
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}