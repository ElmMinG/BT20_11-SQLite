package vn.iotstar.listviewpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class NotesAdapter extends BaseAdapter {
    private SQLiteActivity context;
    private int layout;
    private List<NotesModel> noteList;

    public NotesAdapter(SQLiteActivity context, int layout, List<NotesModel> noteList) {
        this.context = context;
        this.layout = layout;
        this.noteList = noteList;
    }

    @Override
    public int getCount() { return noteList.size(); }
    @Override
    public Object getItem(int position) { return null; }
    @Override
    public long getItemId(int position) { return 0; }

    private class ViewHolder {
        TextView txtName;
        ImageView imgDelete, imgEdit;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtName = convertView.findViewById(R.id.textviewTenNote);
            holder.imgDelete = convertView.findViewById(R.id.imageviewDelete);
            holder.imgEdit = convertView.findViewById(R.id.imageviewEdit);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final NotesModel note = noteList.get(position);
        holder.txtName.setText(note.getNameNote());

        // Bắt sự kiện Sửa
        holder.imgEdit.setOnClickListener(v -> context.DialogCapNhatNoiDung(note.getNameNote(), note.getIdNote()));

        // Bắt sự kiện Xóa
        holder.imgDelete.setOnClickListener(v -> context.DialogXoaCV(note.getNameNote(), note.getIdNote()));

        return convertView;
    }
}