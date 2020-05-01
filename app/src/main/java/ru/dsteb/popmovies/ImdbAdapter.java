package ru.dsteb.popmovies;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ImdbAdapter extends RecyclerView.Adapter<ImdbAdapter.ImbdbViewHolder> {

    private static final String TAG = ImdbAdapter.class.getCanonicalName();

    private final String[] mDataset = new String[100];

    @NonNull
    @Override
    public ImbdbViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        boolean attachToRoot = false;
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_item, parent, attachToRoot);
        Log.d(TAG, "onCreateViewHolder: ");
        return new ImbdbViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ImbdbViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + mDataset.length);
        return mDataset.length;
    }

    public static class ImbdbViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView itemView;

        public ImbdbViewHolder(@NonNull View layout) {
            super(layout);
            this.itemView = layout.findViewById(R.id.iv_view_holder);
        }
    }
}
