package com.tanayagrawal.popularmoviesstageone.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tanayagrawal.popularmoviesstageone.R;
import com.tanayagrawal.popularmoviesstageone.model.MovieInformation;
import com.tanayagrawal.popularmoviesstageone.utility.TypefaceUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieInformationActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.title_toolbar)
    TextView titleToolbar;

    @Bind(R.id.movie_poster)
    ImageView moviePoster;

    @Bind(R.id.movie_release_date_header)
    TextView movieReleaseDateHeader;

    @Bind(R.id.movie_release_date)
    TextView movieReleaseDate;

    @Bind(R.id.movie_synopsis_header)
    TextView movieSynopsisHeader;

    @Bind(R.id.movie_synopsis)
    TextView movieSynopsis;

    @Bind(R.id.movie_title_header)
    TextView movieTitleHeader;

    @Bind(R.id.movie_title)
    TextView movieTitle;

    @Bind(R.id.movie_vote_average_header)
    TextView movieVoteAverageHeader;

    @Bind(R.id.movie_vote_average)
    TextView movieVoteAverage;

    private TypefaceUtils mTypefaceUtils;
    private Context context;
    private MovieInformation movieInformation;
    private String baseImageURL = "http://image.tmdb.org/t/p/w185/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_information);
        ButterKnife.bind(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initialize();
    }

    private void initialize() {
        context = this;
        movieInformation = new MovieInformation();
        mTypefaceUtils = new TypefaceUtils(this);
        setTypeface();
        setupToolbar();
        getObjectFromIntent();
    }

    private void getObjectFromIntent() {
        movieInformation = (MovieInformation) getIntent().getSerializableExtra("movieInformationObject");
        showMovieData();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void setTypeface() {
        mTypefaceUtils.setTextViewTypeface(movieReleaseDateHeader);
        mTypefaceUtils.setTextViewTypeface(movieSynopsisHeader);
        mTypefaceUtils.setTextViewTypeface(movieTitleHeader);
        mTypefaceUtils.setTextViewTypeface(movieVoteAverageHeader);
        mTypefaceUtils.setTextViewTypeface(movieReleaseDate);
        mTypefaceUtils.setTextViewTypeface(movieSynopsis);
        mTypefaceUtils.setTextViewTypeface(movieTitle);
        mTypefaceUtils.setTextViewTypeface(movieVoteAverage);
        mTypefaceUtils.setTextViewTypeface(titleToolbar);
    }

    public void showMovieData() {
        titleToolbar.setText(movieInformation.getTitle());
        Picasso.with(this)
                .load(baseImageURL + movieInformation.getPosterPath())
                .resize(800,800)
                .centerInside()
                .placeholder(R.drawable.placeholder_image)
                .into(moviePoster);
        movieTitle.setText(movieInformation.getTitle());
        movieSynopsis.setText(movieInformation.getOverview());
        movieReleaseDate.setText(movieInformation.getReleaseDate());
        movieVoteAverage.setText(String.valueOf(movieInformation.getVoteAverage()));
    }
}
