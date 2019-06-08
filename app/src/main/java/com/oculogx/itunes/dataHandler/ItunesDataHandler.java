package com.oculogx.itunes.dataHandler;

import android.support.annotation.NonNull;
import android.util.Log;

import com.oculogx.itunes.client.RetroFitClient;
import com.oculogx.itunes.client.RetroFitInterface;
import com.oculogx.itunes.model.TrackModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItunesDataHandler {

    private static final String TAG = ItunesDataHandler.class.getSimpleName();

    private GetTracksListListener listener;

    public interface GetTracksListListener  {
        void onSuccess(TrackModel response);

        void onError(Throwable error);
    }

    /**
     * Constructor for data handler.
     *
     * @param listener callback listener
     */
    public ItunesDataHandler(
            @NonNull GetTracksListListener listener) {
        this.listener = listener;
    }

    public void getDealsList(String searchTerm){

        RetroFitInterface sevice = RetroFitClient.getRetrofit().create(RetroFitInterface.class);
        Call<TrackModel> call = sevice.getTracks(searchTerm);

        call.enqueue(new Callback<TrackModel>() {
            @Override
            public void onResponse(Call<TrackModel> call, Response<TrackModel> response) {
                Log.d(",","");
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TrackModel> call, Throwable t) {
                Log.d("","");
                listener.onError(t);

            }
        });

    }
}
