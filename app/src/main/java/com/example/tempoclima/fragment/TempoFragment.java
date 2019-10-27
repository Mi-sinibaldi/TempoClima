package com.example.tempoclima.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tempoclima.Model.WeatherResponse;
import com.example.tempoclima.Model.WeatherWeather;
import com.example.tempoclima.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TempoFragment extends Fragment {
    private TextView textViewCity;
    private TextView textViewTemp, textViewTempMin, textViewTempMax;
    private TextView textViewClima, textViewPorcent, textViewHoraNascer, textViewHoraPorSol;
    private ImageView imageView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        WeatherResponse weatherResponse = (WeatherResponse) getArguments().getSerializable("response");

        View view = inflater.inflate(R.layout.fragment_tempo, container, false);

        loadUi(view);
        setUi(weatherResponse);

        return view;
    }

    private void loadUi(View view) {
        textViewCity = view.findViewById(R.id.textViewCity);
        textViewTemp = view.findViewById(R.id.textViewTemp);
        textViewTempMin = view.findViewById(R.id.textViewTempMin);
        textViewTempMax = view.findViewById(R.id.textViewTempMax);
        textViewClima = view.findViewById(R.id.textViewClima);
        textViewPorcent = view.findViewById(R.id.textViewPorcent);
        textViewHoraNascer = view.findViewById(R.id.textViewHoraNascer);
        textViewHoraPorSol = view.findViewById(R.id.textViewHoraPorSol);
        imageView = view.findViewById(R.id.imageView);
    }

    private void setUi(WeatherResponse weatherResponse) {
        textViewCity.setText(weatherResponse.getName());
        textViewTemp.setText(Integer.toString(Math.round(weatherResponse.getMain().getTemp())) + " °C");
        textViewTempMin.setText(Integer.toString(Math.round(weatherResponse.getMain().getTemp_min())) + " °C");
        textViewTempMax.setText(Integer.toString(Math.round(weatherResponse.getMain().getTemp_max())) + " °C");

        WeatherWeather weatherWeather = weatherResponse.getWeather().get(weatherResponse.getWeather().size() - 1);
        textViewClima.setText(weatherWeather.getDescription());
        String url = "https://openweathermap.org/img/wn/" + weatherWeather.getIcon() + "@2x.png";
        Picasso.get().load(url)
                .into(imageView);

        textViewPorcent.setText(Integer.toString(weatherResponse.getMain().getHumidity()) + "%");

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        textViewHoraNascer.setText(dateFormat.format(new Date(weatherResponse.getSys().getSunrise() * 1000)));
        textViewHoraPorSol.setText(dateFormat.format(new Date(weatherResponse.getSys().getSunset() * 1000)));

    }
}
