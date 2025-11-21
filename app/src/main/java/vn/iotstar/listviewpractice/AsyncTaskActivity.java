package vn.iotstar.listviewpractice;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskActivity extends AppCompatActivity {

    Button btnStart;
    TextView tvStatus, tvPercent;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        // Ánh xạ
        btnStart = findViewById(R.id.btn_start_async);
        tvStatus = findViewById(R.id.tv_status);
        tvPercent = findViewById(R.id.tv_percent);
        progressBar = findViewById(R.id.pb_process);

        // Sự kiện click
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khởi chạy AsyncTask
                new MyAsyncTask().execute();
            }
        });
    }

    // Tạo class con kế thừa AsyncTask
    // Tham số 1 (Void): Input (không cần)
    // Tham số 2 (Integer): Progress (kiểu số nguyên để cập nhật %)
    // Tham số 3 (String): Result (kết quả trả về là chuỗi)
    private class MyAsyncTask extends AsyncTask<Void, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // 1. Chạy đầu tiên: Chuẩn bị
            tvStatus.setText("Đang tải dữ liệu...");
            btnStart.setEnabled(false); // Khóa nút lại
        }

        @Override
        protected String doInBackground(Void... voids) {
            // 2. Chạy ngầm (Background Thread): Xử lý tác vụ nặng
            // Giả lập tải dữ liệu bằng vòng lặp
            for (int i = 1; i <= 100; i++) {
                try {
                    Thread.sleep(50); // Nghỉ 50ms (tổng cộng 5 giây)
                    publishProgress(i); // Gửi giá trị i sang onProgressUpdate
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Hoàn thành!"; // Trả về kết quả cho onPostExecute
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // 3. Cập nhật giao diện liên tục (UI Thread)
            int percent = values[0];
            progressBar.setProgress(percent);
            tvPercent.setText(percent + "%");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // 4. Kết thúc (UI Thread)
            tvStatus.setText(s);
            btnStart.setEnabled(true); // Mở lại nút
            Toast.makeText(AsyncTaskActivity.this, "Quá trình tải kết thúc", Toast.LENGTH_SHORT).show();
        }
    }
}