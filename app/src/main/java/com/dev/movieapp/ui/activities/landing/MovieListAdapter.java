package com.dev.movieapp.ui.activities.landing;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.movieapp.R;
import com.dev.movieapp.models.Result;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter class for managing list items
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ListViewHolder>{

    private List<Result> mValues;
    private OnItemClickListener mListener;

    public MovieListAdapter(List<Result> items,
                            OnItemClickListener listener
                            ){
        mValues = items;
        mListener = listener;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_list_content, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, int position) {
        holder.click(mValues.get(position), mListener);
        holder.title.setText(mValues.get(position).getTitle());
        holder.itemView.setTag(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /**
     * Interface for item click
     */
    public interface OnItemClickListener {
        void onClick(Result Item);
    }
    /**
     * View Holder for list view
     */
    public class ListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.id_text)
        TextView title;

        public ListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void click(final Result popularMovies, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(popularMovies);
                }
            });
        }
    }
}
