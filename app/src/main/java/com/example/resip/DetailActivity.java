package com.example.resip;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageButton btnBack = findViewById(R.id.btnBack);
        Button btnFavorite = findViewById(R.id.btnFavorite);
        ImageView imgDetailRecipe = findViewById(R.id.imgDetailRecipe);
        TextView txtDetailTitle = findViewById(R.id.txtDetailTitle);
        TextView txtDetailDescription = findViewById(R.id.txtDetailDescription);
        TextView txtDetailPrice = findViewById(R.id.txtDetailPrice);
        TextView txtDetailTime = findViewById(R.id.txtDetailTime);
        TextView txtDetailPortion = findViewById(R.id.txtDetailPortion);
        TextView txtDetailCalories = findViewById(R.id.txtDetailCalories);
        RecyclerView rvSteps = findViewById(R.id.rvSteps);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        btnBack.setOnClickListener(v -> finish());

        Recipe recipe = (Recipe) getIntent().getSerializableExtra("RECIPE_DATA");

        if (recipe != null) {
            txtDetailTitle.setText(recipe.getTitle());
            txtDetailDescription.setText(recipe.getDescription());
            txtDetailPrice.setText(recipe.getPrice());
            txtDetailTime.setText(recipe.getTime());
            txtDetailPortion.setText(recipe.getPortion());
            txtDetailCalories.setText(recipe.getCalories());
            imgDetailRecipe.setImageResource(recipe.getImageResource());

            rvSteps.setLayoutManager(new LinearLayoutManager(this));
            StepAdapter stepAdapter = new StepAdapter(recipe.getSteps());
            rvSteps.setAdapter(stepAdapter);

            if (FavoriteRepository.getInstance().isFavorite(recipe)) {
                btnFavorite.setText("Favorited");
            } else {
                btnFavorite.setText("Add");
            }

            btnFavorite.setOnClickListener(v -> {
                if (FavoriteRepository.getInstance().isFavorite(recipe)) {
                    FavoriteRepository.getInstance().removeFavorite(recipe);
                    btnFavorite.setText("Add");
                    Toast.makeText(DetailActivity.this, "Removed from Favorites", Toast.LENGTH_SHORT).show();
                } else {
                    FavoriteRepository.getInstance().addFavorite(recipe);
                    btnFavorite.setText("Favorited");
                    Toast.makeText(DetailActivity.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
                }
            });
        }

        bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                finish();
                return true;
            } else if (id == R.id.nav_search) {
                android.content.Intent intent = new android.content.Intent(DetailActivity.this, MainActivity.class);
                intent.putExtra("SELECT_TAB", "SEARCH");
                intent.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP | android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
                return true;
            } else if (id == R.id.nav_favorite) {
                android.content.Intent intent = new android.content.Intent(DetailActivity.this, MainActivity.class);
                intent.putExtra("SELECT_TAB", "FAVORITE");
                intent.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP | android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
                return true;
            } else if (id == R.id.nav_profile) {
                android.content.Intent intent = new android.content.Intent(DetailActivity.this, MainActivity.class);
                intent.putExtra("SELECT_TAB", "PROFILE");
                intent.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP | android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
                return true;
            }
            return false;
        });
    }
}
