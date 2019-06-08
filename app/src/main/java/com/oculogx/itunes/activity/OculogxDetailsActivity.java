package com.oculogx.itunes.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.oculogx.itunes.R;
import com.oculogx.itunes.model.Track;

public class OculogxDetailsActivity extends AppCompatActivity {

    Context context;
    LinearLayout main;
    ImageView imgArtwork;
    TextView txtArtistName, txtGenre, txtPrice;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oculogx_details_activity);
        init();
    }

    private void init() {
        main =  findViewById(R.id.song_detail_main);
        imgArtwork = findViewById(R.id.imgArtworkDetail);
        txtArtistName = findViewById(R.id.artist_name_detail);
        txtGenre =  findViewById(R.id.genre_detail);
        txtPrice =  findViewById(R.id.price_detail);
        videoView = findViewById(R.id.videoView);

        try {
            displayTrack((Track) getIntent().getSerializableExtra("track"));
        } catch (Exception e) {
            Toast.makeText(this,"Session TIme Out, Please try after some time ...",Toast.LENGTH_LONG).show();
        }

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

    public void displayTrack(Track track) {

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(track.trackName);
        }

        String artworkUrl = track.artworkUrl100;
        Glide.with(this).load(artworkUrl).placeholder(R.drawable.itunes_).into(imgArtwork);

        txtArtistName.setText(track.artistName);
        txtGenre.setText(track.primaryGenreName);
        txtPrice.setText(String.format("US $ %s", String.valueOf(track.trackPrice)));

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri video = Uri.parse(track.previewUrl);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(video);
        videoView.start();
    }

}
