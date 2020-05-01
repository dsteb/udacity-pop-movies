package ru.dsteb.popmovies;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


public class ImdbAdapter extends RecyclerView.Adapter<ImdbAdapter.ImbdbViewHolder> {

    private static final String TAG = ImdbAdapter.class.getCanonicalName();

    private static final String IMG_URL = "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_CR0,0,704,1000_AL_.jpg";

    private final String[] mDataset = new String[100];

    @NonNull
    @Override
    public ImbdbViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        boolean attachToRoot = false;
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_item, parent, attachToRoot);
        return new ImbdbViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ImbdbViewHolder holder, int position) {
        Picasso
                .get()
                .load(Uri.parse(IMG_URL))
                .placeholder(R.drawable.demo)
                .into(holder.itemView);
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
