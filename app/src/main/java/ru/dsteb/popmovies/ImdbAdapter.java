package ru.dsteb.popmovies;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.dsteb.popmovies.model.Movie;


public class ImdbAdapter extends RecyclerView.Adapter<ImdbAdapter.ImbdbViewHolder> {

    private List<Movie> data = new ArrayList<>();

    private ImdbAdapterOnclickHandler onclickHandler;

    public ImdbAdapter(ImdbAdapterOnclickHandler onclickHandler) {
        this.onclickHandler = onclickHandler;
    }

    @NonNull
    @Override
    public ImbdbViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        boolean attachToRoot = false;
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_item, parent, attachToRoot);
        ImbdbViewHolder item = new ImbdbViewHolder(layout);
        layout.setOnClickListener(item);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull ImbdbViewHolder holder, int position) {
        position = position % data.size();
        Movie movie = data.get(position);
        Picasso.get()
                .load(Uri.parse(movie.getPosterUri()))
                .into(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addData(List<Movie> data) {
        this.data.addAll(data);
    }

    public interface ImdbAdapterOnclickHandler {
        void onClick(Movie movie);
    }

    public class ImbdbViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        // each data item is just a string in this case
        public ImageView itemView;

        public ImbdbViewHolder(@NonNull View layout) {
            super(layout);
            this.itemView = layout.findViewById(R.id.iv_view_holder);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Movie movie = data.get(position);
            onclickHandler.onClick(movie);
        }
    }
}
