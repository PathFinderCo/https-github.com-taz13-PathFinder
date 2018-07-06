package com.pioneers.pathfinder.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pioneers.pathfinder.R;

import java.util.Map;
import java.util.Set;

/**
 * Created by Sultan Mahmud on 4/30/2017.
 */

public class BusStopFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {
    String TAG = "Database setup";
    TextInputLayout textInputLayout;
    ProgressDialog progressDialog;
    NoInternetDialog dialogFragment;
    View view;
    private Button findBusServices;
    private TextInputLayout textInputLayou_BusStop;
    private AutoCompleteTextView autoCompleteTextView_BusStop;
    private AdView mAdView;
    private Set busStops;
    private DatabaseReference busStopRef;
    private ArrayAdapter<String> adapterBusStop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_bus_stop, container, false);
        textInputLayou_BusStop = (TextInputLayout) view.findViewById(R.id.textinputlayout_busStop);
        adapterBusStop = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line);
        try {


            busStopRef = FirebaseDatabase.getInstance().getReference("LatLong");

            busStopRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Map value = (Map<String, String>) dataSnapshot.getValue();
                    busStops = value.keySet();
                    adapterBusStop.addAll(busStops);
                    Log.d(TAG, "Value is: " + value);
                }

                @Override
                public void onCancelled(DatabaseError error) {

                    Log.w(TAG, "Failed to read value.", error.toException());

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        findBusServices = (Button) view.findViewById(R.id.btn_busServices);


        findBusServices.setOnClickListener(this);


        autoCompleteTextView_BusStop = (AutoCompleteTextView) view.findViewById(R.id.autocompletetextview_busStop);

        autoCompleteTextView_BusStop.setAdapter(adapterBusStop);

        autoCompleteTextView_BusStop.setOnTouchListener(this);
        autoCompleteTextView_BusStop.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                String ss = autoCompleteTextView_BusStop.getText().toString();
                if (ss.length() == 1) {
                    textInputLayou_BusStop.setErrorEnabled(false);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_busServices: {

                checkConnectivity();
                break;
            }
            default:
                break;

        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        final int DRAWABLE_RIGHT = 2;
        switch (v.getId()) {
            case R.id.autocompletetextview_busStop:
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getX() >= (autoCompleteTextView_BusStop.getRight() - autoCompleteTextView_BusStop.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        try {
                            if (!autoCompleteTextView_BusStop.getText().toString().equals("")) {

                                autoCompleteTextView_BusStop.getText().clear();
                            }
                        } catch (Exception e) {
                        }
                        return true;
                    }

                }


        }


        return false;
    }

    public void checkConnectivity() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showactivity(isConnected);
    }

    public void showactivity(boolean isConnected) {
        if (isConnected) {
            if (autoCompleteTextView_BusStop.getText().toString().equals("")) {
                textInputLayou_BusStop.setErrorEnabled(true);
                textInputLayou_BusStop.setError("Please input valid bus stop name");
            } else {
                Log.d("BusFinder", "Bus services found");
                //Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();
                Intent findBusServiceDetail = new Intent(getActivity(), FindBusServiceDetail.class);
                findBusServiceDetail.putExtra("reqType", getString(R.string.busService));
                findBusServiceDetail.putExtra("stopName", autoCompleteTextView_BusStop.getText().toString());
                startActivity(findBusServiceDetail);
            }

        } else {
            showDialog();

        }
    }

    public void showDialog() {
        dialogFragment = new NoInternetDialog();
        dialogFragment.setCancelable(false);
        dialogFragment.show(getFragmentManager(), "DIALOG_FRAGMENT");
    }
}
