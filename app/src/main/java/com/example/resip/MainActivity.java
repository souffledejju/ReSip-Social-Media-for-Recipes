package com.example.resip;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvRecipes = findViewById(R.id.rvRecipes);
        ImageView btnMiniProfile = findViewById(R.id.btnMiniProfile);
        EditText searchBar = findViewById(R.id.searchBar);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        RecipeRepository repository = new RecipeRepository();
        List<Recipe> recipeList = repository.getRecipes();

        RecipeAdapter adapter = new RecipeAdapter(recipeList);
        rvRecipes.setLayoutManager(new LinearLayoutManager(this));
        rvRecipes.setAdapter(adapter);

        btnMiniProfile.setOnClickListener(view -> {
            PopupMenu popup = new PopupMenu(MainActivity.this, view);
            popup.getMenuInflater().inflate(R.menu.profile_popup_menu, popup.getMenu());

            popup.setOnMenuItemClickListener(menuItem -> {
                int id = menuItem.getItemId();
                if (id == R.id.menu_settings) {
                    Toast.makeText(MainActivity.this, "Settings Selected", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.menu_logout) {
                    Toast.makeText(MainActivity.this, "Logging out...", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            });
            popup.show();
        });

        bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                rvRecipes.setVisibility(android.view.View.VISIBLE);
                findViewById(R.id.fragment_container).setVisibility(android.view.View.GONE);
                return true;
            } else if (id == R.id.nav_search) {
                rvRecipes.setVisibility(android.view.View.GONE);
                findViewById(R.id.fragment_container).setVisibility(android.view.View.VISIBLE);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new SearchFragment())
                        .commit();
                return true;
            } else if (id == R.id.nav_favorite) {
                rvRecipes.setVisibility(android.view.View.GONE);
                findViewById(R.id.fragment_container).setVisibility(android.view.View.VISIBLE);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new FavoriteFragment())
                        .commit();
                return true;
            } else if (id == R.id.nav_profile) {
                rvRecipes.setVisibility(android.view.View.GONE);
                findViewById(R.id.fragment_container).setVisibility(android.view.View.VISIBLE);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ProfileFragment())
                        .commit();
                return true;
            }
            return false;
        });

        searchBar.setOnClickListener(v -> {
        });

        searchBar.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH) {
                String query = searchBar.getText().toString();

                if (!query.isEmpty()) {
                    searchBar.setText("");

                    SearchFragment searchFragment = new SearchFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("HOME_QUERY", query);
                    searchFragment.setArguments(bundle);

                    rvRecipes.setVisibility(android.view.View.GONE);
                    findViewById(R.id.fragment_container).setVisibility(android.view.View.VISIBLE);
                    bottomNavigation.setSelectedItemId(R.id.nav_search);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, searchFragment)
                            .commit();
                }
                return true;
            }
            return false;
        });

        searchBar.setOnClickListener(v -> {
            bottomNavigation.setSelectedItemId(R.id.nav_search);
        });

    }

    @Override
    protected void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (intent != null && intent.hasExtra("SELECT_TAB")) {
            String tab = intent.getStringExtra("SELECT_TAB");
            BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
            if ("FAVORITE".equals(tab)) {
                bottomNavigation.setSelectedItemId(R.id.nav_favorite);
            } else if ("SEARCH".equals(tab)) {
                bottomNavigation.setSelectedItemId(R.id.nav_search);
            } else if ("PROFILE".equals(tab)) {
                bottomNavigation.setSelectedItemId(R.id.nav_profile);
            }
        }
    }
}
