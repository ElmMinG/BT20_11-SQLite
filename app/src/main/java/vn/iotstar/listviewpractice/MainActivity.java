package vn.iotstar.listviewpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView myListView;
    GridView myGridView;
    RecyclerView myRecyclerView;
    LinearLayout layoutButtons;

    ArrayList<MonHoc> monHocArrayList;
    List<Object> multiData;
    MultiViewTypeAdapter multiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView = findViewById(R.id.my_list_view);
        myGridView = findViewById(R.id.my_grid_view);
        myRecyclerView = findViewById(R.id.my_recycler_view);
        layoutButtons = findViewById(R.id.layout_buttons);

        Button btnAddText = findViewById(R.id.btn_add_text);
        Button btnAddImage = findViewById(R.id.btn_add_image);
        Button btnAddUser = findViewById(R.id.btn_add_user);
        Button btnOpenBinding = findViewById(R.id.btn_open_databinding);
        Button btnPref = findViewById(R.id.btn_pref);

        Button btnSQLite = findViewById(R.id.btn_sqlite);


        // 1. Chuyển sang DataBinding
        btnOpenBinding.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DataBindingActivity.class);
            startActivity(intent);
        });

        // 2. Chuyển sang Shared Preferences
        btnPref.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SharedPreferencesActivity.class);
            startActivity(intent);
        });

        // 3. Chuyển sang SQLite (Ghi chú)
        btnSQLite.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SQLiteActivity.class);
            startActivity(intent);
        });

        Button btnFile = findViewById(R.id.btn_file);
        btnFile.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FileStorageActivity.class);
            startActivity(intent);
        });

        // --- CHUẨN BỊ DỮ LIỆU CHO LISTVIEW/GRIDVIEW ---
        monHocArrayList = new ArrayList<>();
        monHocArrayList.add(new MonHoc("Java", "Ngôn ngữ lập trình hướng đối tượng", R.drawable.logo_java));
        monHocArrayList.add(new MonHoc("Kotlin", "Ngôn ngữ chính thức cho Android", R.drawable.logo_kotlin));
        monHocArrayList.add(new MonHoc("Python", "Ngôn ngữ cho AI và khoa học dữ liệu", R.drawable.logo_python));
        monHocArrayList.add(new MonHoc("C#", "Ngôn ngữ của Microsoft", R.drawable.logo_csharp));

        // --- XỬ LÝ CÁC NÚT THÊM (Cho bài Multi-Type) ---
        btnAddText.setOnClickListener(v -> {
            multiData.add("Text mới thêm " + new Random().nextInt(100));
            multiAdapter.notifyItemInserted(multiData.size() - 1);
            myRecyclerView.scrollToPosition(multiData.size() - 1);
        });

        btnAddImage.setOnClickListener(v -> {
            multiData.add(R.drawable.logo_java);
            multiAdapter.notifyItemInserted(multiData.size() - 1);
            myRecyclerView.scrollToPosition(multiData.size() - 1);
        });

        btnAddUser.setOnClickListener(v -> {
            multiData.add(new UserModel("User Mới " + new Random().nextInt(100), "Đà Nẵng"));
            multiAdapter.notifyItemInserted(multiData.size() - 1);
            myRecyclerView.scrollToPosition(multiData.size() - 1);
        });

        // ============================================================
        // CHỌN DEMO ĐỂ CHẠY
        // ============================================================

        // runListViewDemo();
        // runGridViewDemo();
        // runRecyclerViewDemo();

        runMultiViewTypeDemo(); // Đang chạy bài này
    }

    private void runListViewDemo() {
        layoutButtons.setVisibility(View.GONE);
        myListView.setVisibility(View.VISIBLE);
        MonHocAdapter adapter = new MonHocAdapter(this, R.layout.list_item_monhoc, monHocArrayList);
        myListView.setAdapter(adapter);
    }

    private void runGridViewDemo() {
        layoutButtons.setVisibility(View.GONE);
        myGridView.setVisibility(View.VISIBLE);
        MonHocAdapter adapter = new MonHocAdapter(this, R.layout.list_item_monhoc, monHocArrayList);
        myGridView.setAdapter(adapter);
    }

    private void runRecyclerViewDemo() {
        layoutButtons.setVisibility(View.GONE);
        myRecyclerView.setVisibility(View.VISIBLE);
        MonHocRecyclerAdapter adapter = new MonHocRecyclerAdapter(this, monHocArrayList);
        myRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        myRecyclerView.setAdapter(adapter);
    }

    private void runMultiViewTypeDemo() {
        layoutButtons.setVisibility(View.VISIBLE);
        myRecyclerView.setVisibility(View.VISIBLE);
        myListView.setVisibility(View.GONE);
        myGridView.setVisibility(View.GONE);

        // Tạo dữ liệu hỗn hợp
        multiData = new ArrayList<>();
        multiData.add("Dòng Text khởi tạo");
        multiData.add(new UserModel("Huỳnh Lê Minh", "TP.HCM"));
        multiData.add(R.drawable.logo_kotlin);

        multiAdapter = new MultiViewTypeAdapter(multiData);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setAdapter(multiAdapter);
    }
}