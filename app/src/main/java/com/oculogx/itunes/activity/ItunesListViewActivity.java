package com.oculogx.itunes.activity;

import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.oculogx.itunes.R;
import com.oculogx.itunes.adapter.ItunesListAdpater;
import com.oculogx.itunes.model.TrackModel;
import com.oculogx.itunes.viewModel.OculogxViewModel;

public class ItunesListViewActivity extends AppCompatActivity {

    private OculogxViewModel mViewModel;
    private RecyclerView mRecycler;
    private ItunesListAdpater mAdapter;
    private RelativeLayout rlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itunes_list_view_activity);
        intialize();
    }

    private void intialize() {
        mViewModel = ViewModelProviders.of(this).get(OculogxViewModel.class);
        mRecycler = findViewById(R.id.rvItunes);
        rlView = findViewById(R.id.rlView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search for Songs, Artists & More");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    private void search(String query) {
        final Observer<TrackModel> TrackObserver = TrackResponse -> {
            if (TrackResponse != null) {
                setTracks(TrackResponse);

            }else{
                Toast.makeText(this,"Session TIme Out, Please try after some time ...",Toast.LENGTH_LONG).show();
            }
        };
        mViewModel.getDealsDetails(query).observe(this, TrackObserver);
    }

    private void setTracks(TrackModel trackResponse) {
        rlView.setVisibility(View.GONE);
        mAdapter = new ItunesListAdpater(this,trackResponse.tracks);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setAdapter(mAdapter);
    }
}
