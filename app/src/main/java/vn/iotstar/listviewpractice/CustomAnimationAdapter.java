package vn.iotstar.listviewpractice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CustomAnimationAdapter extends RecyclerView.Adapter<CustomAnimationAdapter.ViewHolder> {

    private List<String> mDatas;

    public CustomAnimationAdapter(List<String> data) {
        mDatas = data;
    }

    // --- CÁC HÀM ĐỂ THAO TÁC DỮ LIỆU ---
    public void addItem(String item) {
        mDatas.add(item);
        notifyItemInserted(mDatas.size() - 1);
    }

    public void removeItem(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    public void replaceItem(int position, String item) {
        mDatas.set(position, item);
        notifyItemChanged(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.row_animation, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = mDatas.get(position);
        holder.tvItem.setText(item);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_item);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    removeItem(getAdapterPosition());
                    Toast.makeText(v.getContext(), "Removed Animation", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replaceItem(getAdapterPosition(), "Item changed");
                    Toast.makeText(v.getContext(), "Changed Animation", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
