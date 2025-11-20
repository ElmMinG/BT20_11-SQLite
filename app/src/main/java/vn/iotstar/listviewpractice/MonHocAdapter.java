package vn.iotstar.listviewpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class MonHocAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<MonHoc> monHocList;

    public MonHocAdapter(Context context, int layout, List<MonHoc> monHocList) {
        this.context = context;
        this.layout = layout;
        this.monHocList = monHocList;
    }

    @Override
    public int getCount() { return monHocList.size(); }
    @Override
    public Object getItem(int position) { return null; }
    @Override
    public long getItemId(int position) { return 0; }

    private static class ViewHolder {
        ImageView imgLogo;
        TextView txtTen;
        TextView txtMoTa;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.imgLogo = convertView.findViewById(R.id.image_view_logo);
            holder.txtTen = convertView.findViewById(R.id.text_view_ten);
            holder.txtMoTa = convertView.findViewById(R.id.text_view_mota);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MonHoc monHoc = monHocList.get(position);
        holder.imgLogo.setImageResource(monHoc.getHinhAnh());
        holder.txtTen.setText(monHoc.getTen());
        holder.txtMoTa.setText(monHoc.getMoTa());

        return convertView;
    }
}