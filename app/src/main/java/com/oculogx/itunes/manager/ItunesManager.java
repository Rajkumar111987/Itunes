package com.oculogx.itunes.manager;

import android.support.annotation.NonNull;

import com.oculogx.itunes.model.TrackModel;

public interface ItunesManager {


    /**
     * Callback for Get Notification.
     */
    interface GetTrackListener {
        void onReceiveTracks(TrackModel dealsDetails);

        void onError(Throwable responseStatus);
    }

    /**
     * Get the Notification Preference.
     *
     * @param callback response callback
     */
    void getTrackList(@NonNull GetTrackListener callback, String searchTerm);
}
