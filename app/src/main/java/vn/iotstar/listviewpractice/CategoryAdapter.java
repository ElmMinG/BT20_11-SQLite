package vn.iotstar.listviewpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> categoryList;
    private Context context;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Bạn có thể dùng lại layout list_item_monhoc.xml hoặc tạo layout mới
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_monhoc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.tvName.setText(category.getName());
        holder.tvDesc.setText(category.getDescription());

        // Dùng Glide để load ảnh từ URL
        Glide.with(context)
                .load(category.getImages())
                .into(holder.imgImage);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgImage;
        TextView tvName, tvDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Đảm bảo ID này khớp với file list_item_monhoc.xml của bạn
            imgImage = itemView.findViewById(R.id.image_view_logo);
            tvName = itemView.findViewById(R.id.text_view_ten);
            tvDesc = itemView.findViewById(R.id.text_view_mota);
        }
    }
}