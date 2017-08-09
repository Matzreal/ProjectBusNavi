package eu.matzreal.projectbusnavi;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link DialogFragment} subclass.
 */
public class BusStopsDialogFragment extends DialogFragment {

    private static String DIALOG_TITLE = "eu.matzreal.projectbusnavi.BusStopsDialogFragment.DIALOG_TITLE";
    private static String DIRECTION = "eu.matzreal.projectbusnavi.BusStopsDialogFragment.DIRECTION";

    private BusStopsAdapter busStopsAdapter;
    private BusStopsDialogListener listener;

    private TextView busStopsFilterEditText;
    private ListView busStopsListView;

    public interface BusStopsDialogListener {
        void onItemSelected(BusStop busStop, RouteSearchFragment.Direction direction);
    }

    public BusStopsDialogFragment() {
        // Required empty public constructor
    }

    public static BusStopsDialogFragment newInstance(String dialogTitle, RouteSearchFragment.Direction direction) {
        BusStopsDialogFragment fragment = new BusStopsDialogFragment();

        Bundle args = new Bundle();
        args.putString(DIALOG_TITLE, dialogTitle);
        args.putSerializable(DIRECTION, direction);
        fragment.setArguments(args);

        return fragment;
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bus_stops_dialog, container, false);

        // Set the title for this dialog
        getDialog().setTitle(getArguments().getString(DIALOG_TITLE));
        TextView dialogTitle = getDialog().findViewById(android.R.id.title);
        dialogTitle.setSingleLine(false);

        // Create the adapter to convert the array to views
        busStopsAdapter = new BusStopsAdapter(getActivity(), ((MainActivity) getActivity()).getBusStopsList());

        busStopsListView = view.findViewById(R.id.lv_bus_stops);
        busStopsListView.setAdapter(busStopsAdapter);
        busStopsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BusStop busStop = (BusStop) adapterView.getItemAtPosition(i);
                // Send the data back and close the dialog
                listener.onItemSelected(busStop, (RouteSearchFragment.Direction) getArguments().getSerializable(DIRECTION));
                dismiss();
            }
        });

        busStopsFilterEditText = view.findViewById(R.id.et_bus_stops_filter);
        busStopsFilterEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                busStopsAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the listener so we can send events to the host
            listener = (BusStopsDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString() + " must implement BusStopsDialogListener");
        }
    }
}
