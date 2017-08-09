package eu.matzreal.projectbusnavi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BusStopsAdapter extends ArrayAdapter<BusStop> implements Filterable {

    private List<BusStop> originalList;
    private List<BusStop> filteredList;
    private ItemFilter filter = new ItemFilter();

    public BusStopsAdapter(Context context, List<BusStop> busStops) {
        super(context, 0, busStops);
        this.originalList = busStops;
        this.filteredList = busStops;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        BusStop busStop = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;  // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_bus_stop, parent, false);
            viewHolder.nameTextView = convertView.findViewById(R.id.tv_item_bus_stop_name);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the data from the data object via the viewHolder object into the template view
        viewHolder.nameTextView.setText(busStop.getName());

        // Return the completed view to render the screen
        return convertView;
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Nullable
    @Override
    public BusStop getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return filteredList.get(position).getId();
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return filter;
    }

    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String filterBy = charSequence.toString().toLowerCase();
            FilterResults filterResults = new FilterResults();
            int count = originalList.size();
            final ArrayList<BusStop> filteredBusStopsList = new ArrayList<>(count);
            String filterableString;

            for (int i = 0; i < count; ++i) {
                filterableString = originalList.get(i).getName();
                if (filterableString.toLowerCase().contains(filterBy))
                    filteredBusStopsList.add(originalList.get(i));
            }

            filterResults.values = filteredBusStopsList;
            filterResults.count = filteredBusStopsList.size();

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            filteredList = (ArrayList<BusStop>) filterResults.values;
            notifyDataSetChanged();
        }
    }

    // View lookup cache
    private static class ViewHolder {
        TextView nameTextView;
    }
}
