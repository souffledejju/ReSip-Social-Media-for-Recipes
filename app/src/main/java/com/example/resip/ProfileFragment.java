package com.example.resip;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.app.AlertDialog;
import android.widget.EditText;
import android.widget.TextView;


public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        view.findViewById(R.id.btnEditProfile).setOnClickListener(v ->
                Toast.makeText(getContext(), "Redirecting to Edit Profile", Toast.LENGTH_SHORT).show());

        view.findViewById(R.id.btnSettings).setOnClickListener(v ->
                Toast.makeText(getContext(), "Settings clicked", Toast.LENGTH_SHORT).show());

        view.findViewById(R.id.btnHelp).setOnClickListener(v ->
                Toast.makeText(getContext(), "Opening Help...", Toast.LENGTH_SHORT).show());

        view.findViewById(R.id.btnCustomerService).setOnClickListener(v ->
                Toast.makeText(getContext(), "Opening Customer Service...", Toast.LENGTH_SHORT).show());

        view.findViewById(R.id.btnLogout).setOnClickListener(v ->
                Toast.makeText(getContext(), "Logging out...", Toast.LENGTH_SHORT).show());

        view.findViewById(R.id.btnEditProfile).setOnClickListener(v -> {
            // 1. Create an EditText for the user to type in
            final EditText input = new EditText(getContext());
            input.setHint("Enter new name");

            // 2. Build the dialog
            new AlertDialog.Builder(getContext())
                    .setTitle("Edit Profile Name")
                    .setView(input)
                    .setPositiveButton("Save", (dialog, which) -> {
                        String newName = input.getText().toString();
                        if (!newName.isEmpty()) {
                            // 3. Update the TextView
                            TextView txtUserName = view.findViewById(R.id.txtUserName);
                            txtUserName.setText(newName);
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });

        return view;
    }
}