package com.happytrees.fast;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.happytrees.fast.Model.MyMovie;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    public List<MyMovie> myObjectList;
    public Context context;

    public MyAdapter(List<MyMovie> myObjectList, Context context) {
        this.myObjectList = myObjectList;
        this.context = context;
    }



    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.movie_item,null);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
      MyMovie myMovie = myObjectList.get(position);
       holder.bindDataFromArrayToView(myMovie);
    }

    @Override
    public int getItemCount() {
        return myObjectList.size();
    }

    //INNER CLASS
    public class MyViewHolder extends RecyclerView.ViewHolder {
        View viewHolderView;


        public MyViewHolder(View itemView) {
            super(itemView);
            viewHolderView = itemView;
        }
        public void bindDataFromArrayToView(final MyMovie currentMovie) {
            TextView nameTV = (TextView)viewHolderView.findViewById(R.id.nameTV);
            nameTV.setText(currentMovie.title);

        }
    }
}
