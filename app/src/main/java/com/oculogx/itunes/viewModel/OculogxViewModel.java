package com.oculogx.itunes.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.oculogx.itunes.manager.ItunesManager;
import com.oculogx.itunes.manager.ItunesManagerImpl;
import com.oculogx.itunes.model.TrackModel;

public class OculogxViewModel extends AndroidViewModel {

    public OculogxViewModel (Application application){ super(application);}

    private MutableLiveData<TrackModel> mItunes;

    public LiveData<TrackModel> getDealsDetails(String searchTerm){
        mItunes = new MutableLiveData<>();

        ItunesManager manager = new ItunesManagerImpl();
        manager.getTrackList(new ItunesManager.GetTrackListener() {
            @Override
            public void onReceiveTracks(TrackModel trackModelDetails) {
                mItunes.postValue(trackModelDetails);
            }

            @Override
            public void onError(Throwable responseStatus) {

            }
        },searchTerm);


        return mItunes;
    }
}
