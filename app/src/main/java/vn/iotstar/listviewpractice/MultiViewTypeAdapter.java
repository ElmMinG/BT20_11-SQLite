package vn.iotstar.listviewpractice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MultiViewTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_TEXT = 0;
    private static final int TYPE_IMAGE = 1;
    private static final int TYPE_USER = 2;

    private List<Object> mObjects;

    public MultiViewTypeAdapter(List<Object> objects) {
        this.mObjects = objects;
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = mObjects.get(position);
        if (obj instanceof String) return TYPE_TEXT;
        else if (obj instanceof Integer) return TYPE_IMAGE;
        else if (obj instanceof UserModel) return TYPE_USER;
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {
            case TYPE_TEXT:
                view = inflater.inflate(R.layout.row_text, parent, false);
                return new TextViewHolder(view, this);
            case TYPE_IMAGE:
                view = inflater.inflate(R.layout.row_image, parent, false);
                return new ImageViewHolder(view, this);
            case TYPE_USER:
                view = inflater.inflate(R.layout.row_user, parent, false);
                return new UserViewHolder(view, this);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object obj = mObjects.get(position);
        switch (holder.getItemViewType()) {
            case TYPE_TEXT:
                ((TextViewHolder) holder).tvText.setText((String) obj);
                break;
            case TYPE_IMAGE:
                ((ImageViewHolder) holder).imvImage.setImageResource((Integer) obj);
                break;
            case TYPE_USER:
                UserModel user = (UserModel) obj;
                // --- SỬA LỖI Ở ĐÂY ---
                // Dùng getFirstName() thay cho getName()
                ((UserViewHolder) holder).tvName.setText(user.getFirstName());
                // Dùng getLastName() thay cho getAddress()
                // (Vì trong MainActivity ta truyền địa chỉ vào tham số thứ 2)
                ((UserViewHolder) holder).tvAddress.setText(user.getLastName());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    public void removeItem(int position) {
        mObjects.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mObjects.size());
    }

    // --- CÁC VIEWHOLDER ---

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        TextView tvText;
        public TextViewHolder(@NonNull View itemView, MultiViewTypeAdapter adapter) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tv_text);
            itemView.setOnLongClickListener(v -> {
                adapter.removeItem(getAdapterPosition());
                Toast.makeText(v.getContext(), "Đã xóa Text", Toast.LENGTH_SHORT).show();
                return true;
            });
        }
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imvImage;
        public ImageViewHolder(@NonNull View itemView, MultiViewTypeAdapter adapter) {
            super(itemView);
            imvImage = itemView.findViewById(R.id.imv_image);
            itemView.setOnLongClickListener(v -> {
                adapter.removeItem(getAdapterPosition());
                Toast.makeText(v.getContext(), "Đã xóa Ảnh", Toast.LENGTH_SHORT).show();
                return true;
            });
        }
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAddress;
        public UserViewHolder(@NonNull View itemView, MultiViewTypeAdapter adapter) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
            itemView.setOnLongClickListener(v -> {
                adapter.removeItem(getAdapterPosition());
                Toast.makeText(v.getContext(), "Đã xóa User", Toast.LENGTH_SHORT).show();
                return true;
            });
        }
    }
}