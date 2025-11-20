package vn.iotstar.listviewpractice;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStorageActivity extends AppCompatActivity {

    EditText edtFileName, edtContent;
    Button btnSaveInternal, btnReadInternal;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_storage);

        edtFileName = findViewById(R.id.edtFileName);
        edtContent = findViewById(R.id.edtContent);
        btnSaveInternal = findViewById(R.id.btnSaveInternal);
        btnReadInternal = findViewById(R.id.btnReadInternal);
        tvResult = findViewById(R.id.tvResult);

        btnSaveInternal.setOnClickListener(v -> {
            String filename = edtFileName.getText().toString();
            String data = edtContent.getText().toString();

            if (filename.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên file", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
                fos.write(data.getBytes());
                fos.close();
                Toast.makeText(this, "Đã lưu thành công!", Toast.LENGTH_SHORT).show();
                edtContent.setText(""); // Xóa nội dung sau khi lưu
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Lỗi khi lưu file", Toast.LENGTH_SHORT).show();
            }
        });

        btnReadInternal.setOnClickListener(v -> {
            String filename = edtFileName.getText().toString();
            if (filename.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên file để đọc", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                FileInputStream fis = openFileInput(filename);
                StringBuilder buffer = new StringBuilder();
                int read;
                while ((read = fis.read()) != -1) {
                    buffer.append((char) read);
                }
                fis.close();

                tvResult.setText("Nội dung file:\n" + buffer.toString());
                Toast.makeText(this, "Đã đọc xong!", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                tvResult.setText("Không tìm thấy file!");
                Toast.makeText(this, "Lỗi: Không tìm thấy file", Toast.LENGTH_SHORT).show();
            }
        });
    }
}