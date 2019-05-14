package com.example.neyesem.view.categories;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.neyesem.R;
import com.example.neyesem.model.categories.Categories;
import com.example.neyesem.model.categories.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private List<Category> categoriesList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories, parent, false);
        return new CategoriesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Categories category = categoriesList.get(position).getCategories();
        holder.categoryName.setText(category.getName());
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        ViewCompat.setBackgroundTintList(
                holder.cardView,
                ColorStateList.valueOf(color));
    }
    public void setList(List<Category> categories) {
        this.categoriesList.clear();
        this.categoriesList.addAll(categories);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView categoryName;

        public ViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.card_categories);
            categoryName = view.findViewById(R.id.textview_itemcategories_name);
        }
    }
}
