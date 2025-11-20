package vn.iotstar.listviewpractice; // Tên package của bạn

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView detailImageView = findViewById(R.id.detail_image_view);
        TextView detailTenTextView = findViewById(R.id.detail_text_view_ten);
        TextView detailMoTaTextView = findViewById(R.id.detail_text_view_mota);

        Intent intent = getIntent();

        if (intent != null) {
            String tenMonHoc = intent.getStringExtra("TEN_MON_HOC");
            String moTaMonHoc = intent.getStringExtra("MO_TA_MON_HOC");
            int hinhAnhId = intent.getIntExtra("HINH_ANH_ID", 0);

            detailTenTextView.setText(tenMonHoc);
            detailMoTaTextView.setText(moTaMonHoc);

            if (hinhAnhId != 0) {
                detailImageView.setImageResource(hinhAnhId);
            }
        }
    }
}