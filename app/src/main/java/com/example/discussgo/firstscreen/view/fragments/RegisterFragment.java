package com.example.discussgo.firstscreen.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.discussgo.R;
import com.example.discussgo.addon.Animation;
import com.example.discussgo.addon.SharedPreferencesWrapper;
import com.example.discussgo.firstscreen.factory.RegisterFactory;
import com.example.discussgo.firstscreen.repository.UserRepository;
import com.example.discussgo.firstscreen.viewmodel.RegisterVM;

public class RegisterFragment extends Fragment {
    RegisterVM viewmodel;
    Animation animation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_layout, container, false);

        final EditText firstName = view.findViewById(R.id.register_editFirstName);
        final EditText lastName = view.findViewById(R.id.register_editLastName);
        final EditText username = view.findViewById(R.id.register_editUsername);
        final EditText password = view.findViewById(R.id.register_editPassword);
        final EditText confirmPassword = view.findViewById(R.id.register_editConfirmPassword);

        SharedPreferencesWrapper wrapper = SharedPreferencesWrapper.getInstance();
        UserRepository repository = UserRepository.getInstance(wrapper);
        RegisterFactory factory = new RegisterFactory(repository);

        viewmodel = ViewModelProviders.of(this, factory).get(RegisterVM.class);

        viewmodel.getCheckFields().observe(RegisterFragment.this, s -> {
            //animation.stopAnimation();
            if (s.contains("username")) {
                username.setError(s);
            } else if(s.contains("password")) {
                password.setError(s);
            } else if(s.contains("first name")) {
                firstName.setError(s);
            } else if(s.contains("last name")) {
                lastName.setError(s);
            } else if(s.contains("confirm password")) {
                confirmPassword.setError(s);
            } else {
                password.setText("");
                confirmPassword.setText("");
                password.setError(s);
                confirmPassword.setError(s);
            }
        });

        Button btnRegister = view.findViewById(R.id.register_btnRegister);
        btnRegister.setOnClickListener(view1 -> {
            viewmodel.checkFields(firstName.getText().toString(), lastName.getText().toString(), username.getText().toString(), password.getText().toString(), confirmPassword.getText().toString());
        });
        return view;
    }
}
