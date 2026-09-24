package com.example.resip;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipeList;

    public RecipeAdapter(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe currentRecipe = recipeList.get(position);

        holder.txtTitle.setText(currentRecipe.getTitle());
        holder.txtDescription.setText(currentRecipe.getDescription());
        holder.txtPrice.setText(currentRecipe.getPrice());
        holder.imgRecipe.setImageResource(currentRecipe.getImageResource());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("RECIPE_DATA", currentRecipe);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDescription, txtPrice;
        ImageView imgRecipe;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtRecipeTitle);
            txtDescription = itemView.findViewById(R.id.txtRecipeDescription);
            txtPrice = itemView.findViewById(R.id.txtRecipePrice);
            imgRecipe = itemView.findViewById(R.id.imgRecipe);
        }
    }

    public void updateList(List<Recipe> newList) {
        this.recipeList = newList;
        notifyDataSetChanged();
    }

}