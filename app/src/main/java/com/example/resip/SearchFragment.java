package com.example.resip;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private EditText etSearchQuery;
    private RecyclerView rvSearchResults;
    private RecipeAdapter adapter;
    private List<Recipe> allRecipes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        etSearchQuery = view.findViewById(R.id.etSearchQuery);
        rvSearchResults = view.findViewById(R.id.rvSearchResults);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecipeRepository repository = new RecipeRepository();
        allRecipes = repository.getRecipes();

        adapter = new RecipeAdapter(new ArrayList<>(allRecipes));
        rvSearchResults.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSearchResults.setAdapter(adapter);

        etSearchQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterRecipes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        if (getArguments() != null && getArguments().containsKey("HOME_QUERY")) {
            String homeQuery = getArguments().getString("HOME_QUERY");
            etSearchQuery.setText(homeQuery);
            etSearchQuery.setSelection(homeQuery.length());
        }
    }

    private void filterRecipes(String query) {
        List<Recipe> filteredList = new ArrayList<>();
        for (Recipe recipe : allRecipes) {
            if (recipe.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    recipe.getDescription().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(recipe);
            }
        }
        adapter.updateList(filteredList);
    }
}