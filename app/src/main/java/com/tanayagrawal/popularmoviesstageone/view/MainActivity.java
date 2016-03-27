package com.tanayagrawal.popularmoviesstageone.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tanayagrawal.popularmoviesstageone.R;
import com.tanayagrawal.popularmoviesstageone.helper.NetworkClient;
import com.tanayagrawal.popularmoviesstageone.model.MovieInformation;
import com.tanayagrawal.popularmoviesstageone.model.Results;
import com.tanayagrawal.popularmoviesstageone.utility.TypefaceUtils;
import com.tanayagrawal.popularmoviesstageone.utility.Utilities;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.title_toolbar)
    TextView titleToolbar;

    @Bind(R.id.movie_grid_view)
    GridView moviePosterGridView;

    @Bind(R.id.error_textView)
    TextView errorTextView;

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;


    TextView chooseSortingOption;
    RadioGroup sortOptionsRadioGroup;
    RadioButton sortByRatingRadioButton;
    RadioButton sortByPopularityRadioButton;

    private GridViewAdapter gridViewAdapter;
    private Context context;
    private String movieListType = "top_rated";
    private ArrayList<String> imagePaths;
    private ArrayList<MovieInformation> movieInformationArrayList;
    private final String TAG = MainActivity.class.getSimpleName();
    private Dialog dialog;
    private TypefaceUtils mTypefaceUtils;

    /**
     * Insert your API Key below
     */
    private String API_KEY = "ed340a56880a3273e5b1c941357d7def";
    private String baseImageURL = "http://image.tmdb.org/t/p/w185/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initialize();
    }

    private void initialize() {
        context = this;
        mTypefaceUtils = new TypefaceUtils(this);
        setupToolbar();
        setupTypeface();
        fetchMovies();
        initializeEventListeners();
    }

    private void setupTypeface() {
        mTypefaceUtils.setTextViewTypeface(titleToolbar);
        mTypefaceUtils.setTextViewTypeface(errorTextView);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        titleToolbar.setText(getString(R.string.popular_movies));
    }

    private void initializeEventListeners() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchMovies();
            }
        });

        moviePosterGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, MovieInformationActivity.class);
                intent.putExtra("movieInformationObject", movieInformationArrayList.get(position));
                startActivity(intent);
            }
        });
    }


    private void dismissDialog() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    private void fetchMovies() {
        if (Utilities.hasConnection(getApplication())) {
            swipeRefreshLayout.setRefreshing(true);
            fetchMovieList(movieListType);
        } else {
            Utilities.showToastMessage(getString(R.string.no_internet), context);
            errorTextView.setVisibility(View.VISIBLE);
            errorTextView.setText(getString(R.string.no_data));
            swipeRefreshLayout.setRefreshing(false);
        }
    }


    private void fetchMovieList(String movieListType) {

        new NetworkClient().getService().fetchMovies(movieListType).enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Response<Results> response, Retrofit retrofit) {
                Log.d(TAG, "Response code : " + response.code());
                Log.d(TAG, "Response body : " + response.body());
                if (response.code() == 200) {
                    Log.d(TAG, "We are successful");
                    swipeRefreshLayout.setRefreshing(false);
                    onMovieListFetched(response.body());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                errorTextView.setVisibility(View.VISIBLE);
                Log.d(TAG, "Error : " + t);
                errorTextView.setText(getString(R.string.download_fail));
            }
        });
    }

    private void onMovieListFetched(Results results) {
        imagePaths = new ArrayList<String>();
        movieInformationArrayList = new ArrayList<MovieInformation>();

        for (int i = 0; i < results.getResults().size(); i++) {
            imagePaths.add(baseImageURL + results.getResults().get(i).getPosterPath());
            movieInformationArrayList.add(results.getResults().get(i));

        }
        Log.d(TAG, "Size of image path array : " + imagePaths.size());
        setupGridViewAdapter();
    }

    private void setupGridViewAdapter() {
        if (imagePaths != null && imagePaths.size() > 0) {
            errorTextView.setVisibility(View.GONE);
            gridViewAdapter = new GridViewAdapter(context, imagePaths);
            moviePosterGridView.setAdapter(gridViewAdapter);
        }
    }

    private void createSortOptionsDialog() {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_view);

        sortOptionsRadioGroup = (RadioGroup) dialog.findViewById(R.id.sort_options_radio_group);
        sortByRatingRadioButton = (RadioButton) dialog.findViewById(R.id.rating_radio_button);
        sortByPopularityRadioButton = (RadioButton) dialog.findViewById(R.id.popularity_radio_button);
        chooseSortingOption = (TextView) dialog.findViewById(R.id.choose_sorting_option);

        mTypefaceUtils.setRadioButtonTypeface(sortByRatingRadioButton);
        mTypefaceUtils.setRadioButtonTypeface(sortByPopularityRadioButton);
        mTypefaceUtils.setTextViewTypeface(chooseSortingOption);


        if (movieListType.equalsIgnoreCase("top_rated")) {
            sortByRatingRadioButton.setChecked(true);
        } else if (movieListType.equalsIgnoreCase("popular")) {
            sortByPopularityRadioButton.setChecked(true);
        }

        initializeRadioButtonEventListener();

        dialog.show();
    }

    private void initializeRadioButtonEventListener() {
        sortOptionsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rating_radio_button) {
                    movieListType = "top_rated";
                    fetchMovies();
                    dismissDialog();
                } else if (checkedId == R.id.popularity_radio_button) {
                    movieListType = "popular";
                    fetchMovies();
                    dismissDialog();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sort) {
            createSortOptionsDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        Parcelable gridViewState = moviePosterGridView.onSaveInstanceState();
        saveInstanceState.putParcelable("gridViewState", gridViewState);
        super.onSaveInstanceState(saveInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        Parcelable gridViewState = saveInstanceState.getParcelable("gridViewState");
        moviePosterGridView.onRestoreInstanceState(gridViewState);

    }



}
