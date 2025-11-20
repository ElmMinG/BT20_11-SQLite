package vn.iotstar.listviewpractice; // Giữ nguyên tên package của bạn

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MonHocRecyclerAdapter extends RecyclerView.Adapter<MonHocRecyclerAdapter.MyViewHolder> {
    private List<MonHoc> monHocList;
    private Context context;

    public MonHocRecyclerAdapter(Context context, List<MonHoc> monHocList) {
        this.context = context;
        this.monHocList = monHocList;
    }

    @NonNull @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_monhoc, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MonHoc monHoc = monHocList.get(position);
        holder.txtTen.setText(monHoc.getTen());
        holder.txtMoTa.setText(monHoc.getMoTa());
        holder.imgLogo.setImageResource(monHoc.getHinhAnh());
    }

    @Override
    public int getItemCount() { return monHocList.size(); }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgLogo;
        TextView txtTen;
        TextView txtMoTa;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLogo = itemView.findViewById(R.id.image_view_logo);
            txtTen = itemView.findViewById(R.id.text_view_ten);
            txtMoTa = itemView.findViewById(R.id.text_view_mota);
        }
    }
}