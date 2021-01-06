package com.abhishekjagushte.introtuceassignment.ui.screens.mainscreen.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.abhishekjagushte.introtuceassignment.R;
import com.abhishekjagushte.introtuceassignment.model.User;
import com.abhishekjagushte.introtuceassignment.repository.DataRepository;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

public class UserInputFragment extends Fragment {

    private String TAG = "UserInputFragment";
    private String errorMessage;
    private String[] errors = {"First name cannot be empty", "Last name cannot be empty", "Date of birth should be in format dd/mm/yyyy", "Please select a gender",
            "Country Name cannot be empty","State name cannot be empty", "Hometown name cannot be empty", "Phone numebr shoud be 10 digit only"};
    private int errorCode = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_input, container, false);
        EditText firstNameInput = view.findViewById(R.id.first_name_input);
        EditText lastNameInput = view.findViewById(R.id.last_name_input);
        EditText dateInput = view.findViewById(R.id.date_input);
        EditText countryInput = view.findViewById(R.id.country_input);
        RadioGroup genderInput = view.findViewById(R.id.gender_radio_group);
        EditText stateInput = view.findViewById(R.id.state_input);
        EditText hometownInput = view.findViewById(R.id.hometown_input);
        EditText phoneNumberInput = view.findViewById(R.id.phone_number_inpurt);

        Button addUserButton = view.findViewById(R.id.add_button);

        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameInput.getText().toString();
                String lastName = lastNameInput.getText().toString();
                String date = dateInput.getText().toString();
                String country = countryInput.getText().toString();
                String state = stateInput.getText().toString();
                String phoneNumber = phoneNumberInput.getText().toString();
                String hometown = hometownInput.getText().toString();
                int rbid = genderInput.getCheckedRadioButtonId();
                String gender = "";

                if(rbid == R.id.radio_male)
                    gender = "Male";
                else if (rbid == R.id.radio_female)
                    gender = "Female";

                if(validateInput(firstName, lastName, date, country, state, phoneNumber, gender, hometown)) {
                    DataRepository.getInstance().addUser(new User(firstName, lastName, country, gender, date, hometown, phoneNumber, state)).addOnSuccessListener(Void -> Log.d(TAG, "onSuccess: succeeded"));
                    Log.d(TAG, "onClick: here");
                }
            }
        });

        return view;
    }

    private boolean validateInput(String firstName, String lastName, String date, String country, String state, String phoneNumber, String gender, String hometown) {
        errorCode=-1;
        if(firstName.isEmpty()) {
            errorCode = 0;
        }
        else if(lastName.isEmpty()){
            errorCode = 1;
        }
        else if(validateDate(date)){
            errorCode = 2;
        }
        else if(gender.isEmpty()){
            errorCode = 3;
        }
        else if(country.isEmpty()){
            errorCode = 4;
        }
        else if(state.isEmpty()){
            errorCode = 5;
        }
        else if(hometown.isEmpty()){
            errorCode = 6;
        }
        else if(phoneNumber.length() != 10){
            errorCode = 7;
            System.out.println(phoneNumber.length());
        }

        if(errorCode!=-1) {
            alert();
            return false;
        }
        return true;
    }

    private boolean validateDate(String date){
        if(date.isEmpty())
            return true;

        if(!date.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))
            return true;

        return false;
    }

    private void alert() {

        AlertDialog ad = new AlertDialog.Builder(this.getContext())
                .setTitle("Alert")
                .setMessage(errors[errorCode])
                .setPositiveButton("Okay", (dialog, which) -> {

                }).create();

        ad.show();
    }


}