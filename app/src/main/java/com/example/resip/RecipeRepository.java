package com.example.resip;

import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {

    public List<Recipe> getRecipes() {
        List<Recipe> recipeList = new ArrayList<>();

        recipeList.add(buildRecipe(
                1,
                "Sate Ayam Madura",
                "Sate ayam dengan bumbu kacang gurih khas Madura.",
                "Rp 30.000",
                R.drawable.food1,
                "15 Menit",
                "4 Porsi",
                "380 kcal",
                new String[]{"Potong Ayam", "Tusuk Sate"},
                new String[]{"10 menit", "10 menit"},
                new String[]{
                        "Potong daging dada ayam fillet menjadi bentuk dadu atau kubus kecil ukuran sekitar 1.5 cm. Pastikan ukurannya seragam.",
                        "Tusukkan 4 sampai 5 potong ayam dadu ke bilah tusuk sate bambu yang sudah direndam air sebelumnya."
                }
        ));

        recipeList.add(buildRecipe(
                2,
                "Rendang Padang",
                "Daging sapi empuk bumbu rempah tradisional Padang.",
                "Rp 45.000",
                R.drawable.food2,
                "3 Jam",
                "6 Porsi",
                "520 kcal",
                new String[]{"Potong Daging Sapi", "Masak Santan Rempah"},
                new String[]{"20 menit", "10 menit"},
                new String[]{
                        "Potong daging sapi searah dengan urat serat dagingnya dengan ketebalan sekitar 2 cm agar bentuk daging tetap terjaga.",
                        "Rebus santan kental bersama bumbu halus, serai, dan daun jeruk di dalam wajan besar. Aduk merata secara terus-menerus."
                }
        ));

        return recipeList;
    }

    private Recipe buildRecipe(int id, String title, String desc, String price, int img, String time, String portion, String calories, String[] stepTitles, String[] stepTimes, String[] stepDetails) {
        List<Step> steps = new ArrayList<>();
        for (int i = 0; i < stepTitles.length; i++) {
            steps.add(new Step(stepTitles[i], stepTimes[i], stepDetails[i]));
        }
        return new Recipe(id, title, desc, price, img, time, portion, calories, steps);
    }
}