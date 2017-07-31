package eu.matzreal.projectbusnavi;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link DialogFragment} subclass.
 */
public class BusStopsFragment extends DialogFragment {

    public BusStopsFragment() {
        // Required empty public constructor
    }

    public static BusStopsFragment newInstance() {
        return new BusStopsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int dialogStyle = DialogFragment.STYLE_NORMAL;
        int dialogTheme = android.R.style.Theme_Material_Light_Dialog;
        setStyle(dialogStyle, dialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Set the title for this dialog
        getDialog().setTitle("Wybierz przystanek początkowy");
        TextView dialogTitle = getDialog().findViewById(android.R.id.title);
        dialogTitle.setSingleLine(false);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bus_stops, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();


    }
}
