package vn.iotstar.listviewpractice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class SQLiteActivity extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    ListView listView;
    ArrayList<NotesModel> arrayList;
    NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        // Ánh xạ ListView
        listView = findViewById(R.id.listViewNotes);
        arrayList = new ArrayList<>();
        adapter = new NotesAdapter(this, R.layout.row_notes, arrayList);
        listView.setAdapter(adapter);

        // 1. Tạo Database và Bảng
        databaseHandler = new DatabaseHandler(this, "GhiChu.sqlite", null, 1);
        databaseHandler.QueryData("CREATE TABLE IF NOT EXISTS Notes(Id INTEGER PRIMARY KEY AUTOINCREMENT, NameNotes VARCHAR(200))");

        // 2. Load dữ liệu
        GetData();

        // 3. Bắt sự kiện nút Thêm (Floating Button) - SỬA Ở ĐÂY
        FloatingActionButton fabAdd = findViewById(R.id.fab_add_note);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogThem();
            }
        });
    }

    private void GetData() {
        // Xóa list cũ để load mới
        Cursor cursor = databaseHandler.GetData("SELECT * FROM Notes");
        arrayList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            arrayList.add(new NotesModel(id, ten));
        }
        adapter.notifyDataSetChanged();
    }

    // Dialog Thêm
    private void DialogThem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        EditText edtNote = new EditText(this);
        edtNote.setHint("Nhập nội dung công việc...");
        builder.setView(edtNote);
        builder.setTitle("Thêm công việc");
        builder.setPositiveButton("Thêm", (dialog, which) -> {
            String ten = edtNote.getText().toString();
            if (ten.equals("")) {
                Toast.makeText(SQLiteActivity.this, "Vui lòng nhập tên!", Toast.LENGTH_SHORT).show();
            } else {
                databaseHandler.QueryData("INSERT INTO Notes VALUES(null, '" + ten + "')");
                Toast.makeText(SQLiteActivity.this, "Đã thêm!", Toast.LENGTH_SHORT).show();
                GetData();
            }
        });
        builder.setNegativeButton("Hủy", null);
        builder.show();
    }

    // Dialog Sửa
    public void DialogCapNhatNoiDung(String ten, int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        EditText edtNote = new EditText(this);
        edtNote.setText(ten);
        builder.setView(edtNote);
        builder.setTitle("Cập nhật công việc");
        builder.setPositiveButton("Sửa", (dialog, which) -> {
            String tenMoi = edtNote.getText().toString();
            databaseHandler.QueryData("UPDATE Notes SET NameNotes = '" + tenMoi + "' WHERE Id = '" + id + "'");
            Toast.makeText(SQLiteActivity.this, "Đã cập nhật!", Toast.LENGTH_SHORT).show();
            GetData();
        });
        builder.setNegativeButton("Hủy", null);
        builder.show();
    }

    // Dialog Xóa
    public void DialogXoaCV(String ten, int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn xóa " + ten + " không?");
        builder.setPositiveButton("Có", (dialog, which) -> {
            databaseHandler.QueryData("DELETE FROM Notes WHERE Id = '" + id + "'");
            Toast.makeText(SQLiteActivity.this, "Đã xóa!", Toast.LENGTH_SHORT).show();
            GetData();
        });
        builder.setNegativeButton("Không", null);
        builder.show();
    }
}