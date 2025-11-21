package vn.iotstar.listviewpractice;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest; // Hoặc StringRequest tùy API
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VolleyActivity extends AppCompatActivity {

    Button btnGetData;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        btnGetData = findViewById(R.id.btn_get_data);
        tvResult = findViewById(R.id.tv_result);

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchDataUsingVolley();
            }
        });
    }

    private void fetchDataUsingVolley() {
        // 1. Tạo RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);

        // URL API
        String url = "https://minh-android-test.free.beeceptor.com/monhoc";

        // 2. SỬ DỤNG StringRequest THAY VÌ JsonObjectRequest
        // StringRequest linh hoạt hơn, nó nhận mọi loại dữ liệu trả về dưới dạng chuỗi
        com.android.volley.toolbox.StringRequest stringRequest = new com.android.volley.toolbox.StringRequest(
                Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // 3. Xử lý kết quả
                        try {
                            // Tự tay chuyển đổi chuỗi String thành JSONObject
                            JSONObject jsonObject = new JSONObject(response);

                            // Lấy mảng "monhoc"
                            JSONArray jsonArray = jsonObject.getJSONArray("monhoc");
                            StringBuilder result = new StringBuilder();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject monhoc = jsonArray.getJSONObject(i);
                                String ten = monhoc.getString("ten");
                                result.append("- ").append(ten).append("\n");
                            }

                            tvResult.setText(result.toString());
                            Toast.makeText(VolleyActivity.this, "Tải thành công!", Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            tvResult.setText("Lỗi phân tích JSON: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // 4. Xử lý lỗi
                        tvResult.setText("Lỗi tải dữ liệu: " + error.toString());
                        error.printStackTrace(); // In lỗi ra Logcat để kiểm tra
                    }
                });

        // 5. Thêm request vào hàng đợi
        queue.add(stringRequest);
    }
}