package com.rachmatwahid.foodie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DishesAdapter extends RecyclerView.Adapter<DishesAdapter.DishViewHolder> {

    private final ArrayList<String> dishes;
    private LayoutInflater inflater;

    public DishesAdapter(Context context, ArrayList<String> dishes) {
        inflater = LayoutInflater.from(context);
        this.dishes = dishes;
    }

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.dish_item, parent,false);
        return new DishViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {
        String dish = dishes.get(position);
        holder.dishTextView.setText(dish);
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    class DishViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView dishTextView;
        final DishesAdapter adapter;

        public DishViewHolder(@NonNull View itemView, DishesAdapter adapter) {
            super(itemView);
            dishTextView = itemView.findViewById(R.id.dish_textView);
            this.adapter = adapter;
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            String element = dishes.get(position);

            adapter.notifyDataSetChanged();
        }
    }
}
