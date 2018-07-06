package com.pioneers.pathfinder.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.pioneers.pathfinder.R;

import java.util.Set;

/**
 * Created by Sultan Mahmud on 4/30/2017.
 */

public class FindRouteFragment extends Fragment implements View.OnClickListener, View.OnTouchListener, MaterialSpinner.OnItemSelectedListener {
    static int i;
    View view;
    AutoCompleteTextView source, destination;
    Button findPath;
    MaterialSpinner spinner;
    String TAG = "Database setup";
    NoInternetDialog dialogFragment;
    private Set busStops;
    private DatabaseReference busStopRef;
    private ArrayAdapter<String> adapterBusStop;
    private TextInputLayout textInputLayou_dest, textInputLayou_source;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_find_route, container, false);

//        textInputLayou_source= (TextInputLayout) view.findViewById(R.id.edt_text_input_layout_source);
//        textInputLayou_dest= (TextInputLayout) view.findViewById(R.id.edt_text_input_layout_dest);
//        spinner = (MaterialSpinner) view.findViewById(R.id.spinner);
//        spinner.setItems("Cheapest", "Shortest");
//        spinner.setOnItemSelectedListener(this);
//        spinner.setVisibility(View.INVISIBLE);
//        f(2);
//        //f(2.0);
//        f(3);
//        source = (AutoCompleteTextView) view.findViewById(R.id.edt_txt_source);
//        destination = (AutoCompleteTextView) view.findViewById(R.id.edt_txt_destination);
//        findPath = (Button) view.findViewById(R.id.button_findpath);
//        adapterBusStop = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line);
//        try {
//
//
//            //Getting Database reference:
//            busStopRef = FirebaseDatabase.getInstance().getReference("LatLong");
//
//            // Read from the database
//            busStopRef.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    // This method is called once with the initial value and again
//                    // whenever data at this location is updated.
//                    Map value = (Map<String, String>) dataSnapshot.getValue();
//                    busStops = value.keySet();
//                    adapterBusStop.addAll(busStops);
//                    Log.d(TAG, "Value is: " + value);
//                }
//
//                @Override
//                public void onCancelled(DatabaseError error) {
//                    // Failed to read value
//                    Log.w(TAG, "Failed to read value.", error.toException());
//
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        //autocomplete text view on change function
//        source.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                String   sourceText=source.getText().toString();
//                if(sourceText.length()>=1){
//                    textInputLayou_source.setErrorEnabled(false);
//                }
//            }
//        });
//        destination.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                String   destText=destination.getText().toString();
//                if(destText.length()>=1){
//                    textInputLayou_dest.setErrorEnabled(false);
//                }
//            }
//        });
//        source.setAdapter(adapterBusStop);
//        destination.setAdapter(adapterBusStop);
//        findPath.setOnClickListener(this);
//        source.setOnTouchListener(this);
//        destination.setOnTouchListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.button_findpath:{
//                checkConnectivity();
//                break;}
//
//        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
//        final int DRAWABLE_RIGHT = 2;
//        switch (v.getId()) {
//            case R.id.edt_txt_source: {
//                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    if (motionEvent.getX() >= (source.getRight() - source.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
//                        try {
//                            if (!source.getText().toString().equals("")) {
//
//                                source.getText().clear();
//                            }
//                        } catch (Exception e) {
//                        }
//                        return true;
//                    }
//
//
//                }
//                break;
//            }
//            case R.id.edt_txt_destination: {
//                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    if (motionEvent.getX() >= (destination.getRight() - destination.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
//                        try {
//                            if (!destination.getText().toString().equals("")) {
//
//                                destination.getText().clear();
//                            }
//                        } catch (Exception e) {
//                        }
//                        return true;
//                    }
//
//
//                }
//                break;
//            }
//        }
//
//        return false;
        //keep the above code remove the following
        return true;
    }

    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
//        switch (position) {
//            case 0:
//                break;
//            case 1:
//                break;
//        }
    }
//    static  void f(int a){
//        Log.d("go", String.valueOf(++i));
//    }


    //for checking if app is connected
//    public void checkConnectivity() {
//        boolean isConnected = ConnectivityReceiver.isConnected();
//        showactivity(isConnected);
//    }
//    public void showactivity(boolean isConnected) {
//        if (isConnected) {
//            if (source.getText().toString().equals("")||destination.getText().toString().equals("")) {
//                if(source.getText().toString().equals("")) {
//                    textInputLayou_source.setErrorEnabled(true);
//                    textInputLayou_source.setError("Please input valid source bus stop name");
//                }
//                if(destination.getText().toString().equals("")) {
//                    textInputLayou_dest.setErrorEnabled(true);
//                    textInputLayou_dest.setError("Please input valid destination bus stop name");
//                }
//            }
//            else {
//                Log.d("BusFinder", "Bus services found");
//                //Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();
//                Intent showOnMap = new Intent(getActivity(), FindBusServiceDetail.class);
//                showOnMap.putExtra("reqType", getString(R.string.shortestPath));
//                showOnMap.putExtra("Source", source.getText().toString());
//                showOnMap.putExtra("Destination", destination.getText().toString());
//                startActivity(showOnMap);
//            }
//
//        } else {
//            showDialog();
//
//        }
//    }
//    public void showDialog() {
//        dialogFragment = new NoInternetDialog();
//        dialogFragment.setCancelable(false);
//        dialogFragment.show(getFragmentManager(), "DIALOG_FRAGMENT");
//    }

}

