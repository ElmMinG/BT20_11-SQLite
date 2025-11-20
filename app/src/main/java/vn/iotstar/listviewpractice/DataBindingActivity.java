package vn.iotstar.listviewpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import vn.iotstar.listviewpractice.databinding.ActivityDataBindingBinding;

public class DataBindingActivity extends AppCompatActivity {

    private UserModel userModel; // Sửa User thành UserModel
    private ActivityDataBindingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

        userModel = new UserModel("Chống", "Lệ Vân");

        binding.setUser(userModel);

        binding.btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cập nhật dữ liệu
                userModel.setFirstName("Huỳnh");
                userModel.setLastName("Lê Minh");
            }
        });
    }
}