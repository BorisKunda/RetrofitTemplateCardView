package com.happytrees.fast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.happytrees.fast.Model.MyMovie;
import com.happytrees.fast.Model.MyResponse;
import com.happytrees.fast.REST.APIClient;
import com.happytrees.fast.REST.Endpoint;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView   myRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting RecyclerView
        myRecycler = (RecyclerView) findViewById(R.id.myRecycler);

        myRecycler.setLayoutManager(new LinearLayoutManager(this));//LinearLayoutManager, GridLayoutManager ,StaggeredGridLayoutManagerFor defining how single row of recycler view will look .  LinearLayoutManager shows items in horizontal or vertical scrolling list. Don't confuse with type of layout you use in xml



        final Endpoint apiService = APIClient.getClient().create(Endpoint.class);
        Call<MyResponse>call = apiService.getMyResults("Titanic");
        call.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                final ArrayList<MyMovie> myDataSource = new ArrayList<>();
                myDataSource.clear();//clean old list if there was call from before
                MyResponse myResponse = response.body();
                myDataSource.addAll(myResponse.results);


                //setting txt adapter
                RecyclerView.Adapter myMovieAdapter = new MyAdapter(myDataSource, MainActivity.this);
                myRecycler.setAdapter(myMovieAdapter);
                myMovieAdapter.notifyDataSetChanged();//refresh

            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {

            }
        });


    }
}


//https://api.themoviedb.org/3/search/movie?api_key=7feab044341d650481d056f7b6fe4441&language=en-US&query=alien&page=1&include_adult=false


/*
   Call<TxtResponse> call = apiService.getMyResults(fromEdtTxt, key);
                            progressDoalog.show();//SHOW PROGRESS BAR BEFORE CALL
                            call.enqueue(new Callback<TxtResponse>() {
                                @Override
                                public void onResponse(Call<TxtResponse> call, Response<TxtResponse> response) {
                                    final ArrayList<TxtResult> myDataSource = new ArrayList<>();
                                    myDataSource.clear();//clean old list if there was call from before
                                    TxtResponse res = response.body();


                                    myDataSource.addAll(res.results);

                                    if (myDataSource.isEmpty()) {
                                        Toast.makeText(getActivity(), "No Results", Toast.LENGTH_SHORT).show();//TOAST MESSAGE IF WE HAVE JSON WITH ZERO RESULTS
                                    }

                                    //SEARCH HISTORY

                                    if (!myDataSource.isEmpty()) {//prevent an attempt of storing empty  array into LastSearch DB


                                        //delete old searches
                                        List<LastSearch> lastSearches = LastSearch.listAll(LastSearch.class);//select all favourites
                                        LastSearch.deleteAll(LastSearch.class);


                                        //loop through array  of results received from retrofit and insert them all into database as most recent result
                                        for (int position = 0; position < myDataSource.size(); position++) {
                                            LastSearch lastSearch = new LastSearch(myDataSource.get(position).name, myDataSource.get(position).formatted_address, myDataSource.get(position).geometry.location.lat, myDataSource.get(position).geometry.location.lng);
                                            lastSearch.save();
                                        }

                                    }


                                    //setting txt adapter
                                    RecyclerView.Adapter myTxtAdapter = new TxtAdapter(myDataSource, getActivity());
                                    fragArecycler.setAdapter(myTxtAdapter);
                                    myTxtAdapter.notifyDataSetChanged();//refresh
                                    progressDoalog.dismiss();//dismiss progress bar after call was completed
                                    Log.i("TxtResults", " very good: " + response.body());

                                }


                                @Override
                                public void onFailure(Call<TxtResponse> call, Throwable t) {
                                    progressDoalog.dismiss();//dismiss progress bar after call was completed
                                    Log.i("TxtResults", " bad: " + t);
                                }
                            });

 */