package eu.matzreal.projectbusnavi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RouteSearchFragment extends Fragment {

    private TextView directionFromTextView;
    private TextView directionToTextView;

    public RouteSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_route_search, container, false);

        directionFromTextView = view.findViewById(R.id.tv_direction_from);
        directionFromTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstBusStop = getResources().getString(R.string.bus_stop_first);
                showBusStopsDialog(firstBusStop, Direction.FROM);
            }
        });

        directionToTextView = view.findViewById(R.id.tv_direction_to);
        directionToTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lastBusStop = getResources().getString(R.string.bus_stop_last);
                showBusStopsDialog(lastBusStop, Direction.TO);
            }
        });

        return view;
    }

    private void showBusStopsDialog(String dialogTitle, Direction direction) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        BusStopsDialogFragment fragment;
        fragment = BusStopsDialogFragment.newInstance(dialogTitle, direction);
        fragment.show(fm, "fragment_bus_stops_dialog");
    }

    public enum Direction {
        FROM, TO
    }

}
