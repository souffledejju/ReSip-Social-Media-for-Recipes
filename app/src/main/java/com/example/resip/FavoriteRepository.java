package com.example.resip;

import java.util.ArrayList;
import java.util.List;

public class FavoriteRepository {

    private static FavoriteRepository instance;
    private List<Recipe> favoriteRecipes;

    private FavoriteRepository() {
        favoriteRecipes = new ArrayList<>();
    }

    public static synchronized FavoriteRepository getInstance() {
        if (instance == null) {
            instance = new FavoriteRepository();
        }
        return instance;
    }

    public List<Recipe> getFavorites() {
        return new ArrayList<>(favoriteRecipes);
    }

    public void addFavorite(Recipe recipe) {
        if (!isFavorite(recipe)) {
            favoriteRecipes.add(recipe);
        }
    }

    public void removeFavorite(Recipe recipe) {
        Recipe toRemove = null;
        for (Recipe fav : favoriteRecipes) {
            if (fav.getId() == recipe.getId()) {
                toRemove = fav;
                break;
            }
        }
        if (toRemove != null) {
            favoriteRecipes.remove(toRemove);
        }
    }

    public boolean isFavorite(Recipe recipe) {
        for (Recipe fav : favoriteRecipes) {
            if (fav.getId() == recipe.getId()) {
                return true;
            }
        }
        return false;
    }
}