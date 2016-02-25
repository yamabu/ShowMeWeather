package moe.yamabu.showmeweather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import moe.yamabu.showmeweather.R;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> implements View
        .OnClickListener {
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public MyRecyclerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_recycler, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setText("点我");
        holder.itemView.setTag(position + "");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (String) v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data);
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.tv)
    TextView mTextView;

    public MyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
