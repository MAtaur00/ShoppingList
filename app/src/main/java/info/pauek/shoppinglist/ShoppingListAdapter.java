package info.pauek.shoppinglist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ItemHolder> {
    Context context;
    List<ShoppingItem> items;
    private OnClickListener onClickListener;
    //todo4: creates one onLongClick
    private OnLongClickListener onLongClickListener;

    public ShoppingListAdapter(Context context, List<ShoppingItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        //todo4: add onLongClick to the function call (ItemHolder constructor)
        return new ItemHolder(itemView, onClickListener, onLongClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int pos) {
        holder.bind(items.get(pos));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.onClickListener = listener;
    }

    //todo4: setter for onLongClick
    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public interface OnClickListener {
        void onClick(int position);
    }

    //todo4: creates onLongClick
    public interface OnLongClickListener {
        void onLongClick(int position);
    }
}
