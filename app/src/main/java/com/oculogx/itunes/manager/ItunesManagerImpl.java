package com.oculogx.itunes.manager;

import android.support.annotation.NonNull;

import com.oculogx.itunes.dataHandler.ItunesDataHandler;
import com.oculogx.itunes.model.TrackModel;

public class ItunesManagerImpl implements ItunesManager {
    @Override
    public void getTrackList(@NonNull GetTrackListener callback, String searchTerm) {

        ItunesDataHandler dataHandler = new ItunesDataHandler(new ItunesDataHandler.GetTracksListListener() {
            @Override
            public void onSuccess(TrackModel response) {
                callback.onReceiveTracks(response);
            }

            @Override
            public void onError(Throwable error) {
                callback.onError(error);
            }
        });
        dataHandler.getDealsList(searchTerm);

    }
}
