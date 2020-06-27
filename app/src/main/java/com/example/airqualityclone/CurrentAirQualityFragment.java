package com.example.airqualityclone;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentAirQualityFragment extends Fragment {

    ImageView airQualityPicture;
    TextView cityName, countryName, cityTimeStamp, cityAQI, cityAQIRating;
    LinearLayout currentPanel;
    ProgressBar currentProgressBar;
    SwipeRefreshLayout pullToRefresh;
    RelativeLayout currentLayout;

    public CurrentAirQualityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_current_air_quality, container, false);

        currentLayout = itemView.findViewById(R.id.current_bg);

        cityName = itemView.findViewById(R.id.cityName);
        cityTimeStamp = itemView.findViewById(R.id.cityTimestamp);
        cityAQI = itemView.findViewById(R.id.cityAQI);
        cityAQIRating = itemView.findViewById(R.id.cityAQIRating);

        currentPanel = itemView.findViewById(R.id.current_panel);
        currentProgressBar = itemView.findViewById(R.id.current_progress_bar);
        pullToRefresh = itemView.findViewById(R.id.current_swipeRefresh);

        pullToRefresh.setOnRefreshListener(() -> {
            Toast.makeText(getContext(), "Refreshed", Toast.LENGTH_SHORT).show();
            getCurrentAirQualityData();
            pullToRefresh.setRefreshing(false);
        });

//        if (savedInstanceState == null && stationJson.equals("")) {
//        if (savedInstanceState == null) {
//            getCurrentAirQualityData();
//            Log.d("saved", "first time");
//        } else {
//            //Using Gson to turn JSON to Java object of Station
//            //Create new GsonBuilder and Gson objects
//            GsonBuilder gsonBuilder = new GsonBuilder();
//            Gson gson = gsonBuilder.create();
//            //Create a new Station object and use Gson to deserialize JSON data
//            //into the Station object
//            Station station = gson.fromJson(stationJson, Station.class);
//            //Call the loadCurrentData method to display the data
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                loadCurrentDataTargetApi26();
//            } else {
//                loadCurrentDataTarget(station);
//        }

        return itemView;
    }

    private void getCurrentAirQualityData() {
//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        //Request a string response from the provided URL, create a new StringRequest object
//        /*
//         * @param response - This is the response (JSON file) from the API
//         */
//        //This is what will happen when there is an error during the response
//        /*
//         * @param error - This is the error that Volley encountered
//         */
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                response -> {
//                    //Using Gson to turn JSON to Java object of Station
//                    //Create new GsonBuilder and Gson objects
//                    GsonBuilder gsonBuilder = new GsonBuilder();
//                    Gson gson = gsonBuilder.create();
//                    //Create a new Station object and use Gson to deserialize JSON data
//                    //into the Station object
//                    Station station = gson.fromJson(response, Station.class);
//                    stationJson = response;
//                    //Call the loadCurrentData method to display the data
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                        loadCurrentDataTargetApi26(station);
//                    } else {
//                        loadCurrentDataTarget(station);
//                    }
//                    //Write the API response data to the log console
//                    Log.d("API RESPONSE", response);
//                }, error -> {
//            //Write the error from Volley to the log console
//            //TODO: add a try - catch block in case too many API requests
//            Log.d("VOLLEY ERROR", error.toString());
//        });
//        //Add the request to the RequestQueue
//        requestQueue.add(stringRequest);
    }

    @TargetApi(26)
    private void loadCurrentDataTargetApi26() {
          cityName.setText("Ho Chi Minh City");
          cityTimeStamp.setText(decodeTimestamp("2020-06-27T13:00:00.000Z"));
          cityAQI.setText("30");
          cityAQIRating.setText("Good");
//
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
//
        Log.d("Time", "24 hours: " + hour);
//
//        //Changing the background image depending on the time of day
//        //Also changes the status bar color if the device has
//        //Android Lollipop or above
        if (getContext() != null) {
            if (hour >= 0 && hour < 5) {
                currentLayout.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.bg_night));
                Window window = Objects.requireNonNull(getActivity()).getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor("#041b20"));
            } else if (hour >= 5 && hour < 7) {
                currentLayout.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.bg_sunrise));
                Window window = Objects.requireNonNull(getActivity()).getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor("#060f18"));
            } else if (hour >= 7 && hour < 17) {
                currentLayout.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.bg_sunny));
                Window window = Objects.requireNonNull(getActivity()).getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor("#08253b"));
            } else if (hour >= 17 && hour < 20) {
                currentLayout.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.bg_sunset));
                Window window = Objects.requireNonNull(getActivity()).getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor("#20244c"));
            } else if (hour >= 20 && hour < 24) {
                currentLayout.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.bg_night));
                Window window = Objects.requireNonNull(getActivity()).getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor("#041b20"));
            }
        }

//        //Display the information after it's been loaded
//        //Hide the progress bar and show the info.
        currentPanel.setVisibility(View.VISIBLE);
        currentProgressBar.setVisibility(View.GONE);
    }

    @TargetApi(26)
    private String decodeTimestamp(String timestamp) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM d", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(timestamp, inputFormatter);
        String formattedDate = outputFormatter.format(date);
        return formattedDate;
    }
}
