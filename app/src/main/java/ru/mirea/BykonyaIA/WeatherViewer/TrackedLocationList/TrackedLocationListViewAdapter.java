package ru.mirea.BykonyaIA.WeatherViewer.TrackedLocationList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import ru.mirea.BykonyaIA.WeatherViewer.R;
import ru.mirea.bykonyaia.domain.dto.TrackedLocationInfo;

public class TrackedLocationListViewAdapter extends RecyclerView.Adapter<TrackedLocationListViewHolder> {
    private final List<TrackedLocationInfo> trackedLocationInfos;
    private final LayoutInflater layoutInflater;
    public TrackedLocationListViewAdapter(Context context, List<TrackedLocationInfo> trackedLocationInfos) {
        this.trackedLocationInfos = trackedLocationInfos;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TrackedLocationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.tracked_location_short_info, parent, false);
        return new TrackedLocationListViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TrackedLocationListViewHolder holder, int position) {
        var info = trackedLocationInfos.get(position);

        Picasso.get()
            .load(info.iconUri())
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .fit().into(holder.iconView);
        holder.titleTextView.setText(info.Geoposition().toString());
        holder.descriptionTextView.setText(String.valueOf(info.Temperature()));
    }
    @Override
    public int getItemCount() {
        return trackedLocationInfos.size();
    }
}
