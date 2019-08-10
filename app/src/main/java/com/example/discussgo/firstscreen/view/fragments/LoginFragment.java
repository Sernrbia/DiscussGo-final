package com.example.discussgo.firstscreen.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.discussgo.R;
import com.example.discussgo.addon.Animation;
import com.example.discussgo.addon.SharedPreferencesWrapper;
import com.example.discussgo.firstscreen.factory.LogInFactory;
import com.example.discussgo.firstscreen.repository.UserRepository;
import com.example.discussgo.firstscreen.viewmodel.LogInVM;
import com.example.discussgo.secondscreen.MainScreenActivity;

import java.util.Objects;

public class LoginFragment extends Fragment {
    private LogInVM viewmodel;
    private Animation animation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login_layout, container, false);
        // Inflate the layout for this fragment
        Animation animation = new Animation((ImageView) view.findViewById(R.id.main_loading));

        final EditText username = view.findViewById(R.id.main_editUsername);
        final EditText password = view.findViewById(R.id.main_editPassword);

        SharedPreferencesWrapper wrapper = SharedPreferencesWrapper.getInstance();
        UserRepository repository = UserRepository.getInstance(wrapper);
        LogInFactory factory = new LogInFactory(repository);

        viewmodel = ViewModelProviders.of(this, factory).get(LogInVM.class);

        String token = wrapper.getAccessToken();
        if (token != null && !token.isEmpty()) {
            Intent intent = new Intent(getActivity(), MainScreenActivity.class);
            startActivity(intent);
            Objects.requireNonNull(getActivity()).finish();
        }

        viewmodel.getSignInSuccessful().observe(LoginFragment.this, s -> {
            animation.stopAnimation();
            if (!s.equals("fail")) {
//                Toast.makeText(view.getContext(), "Login success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainScreenActivity.class);
                startActivity(intent);
                Objects.requireNonNull(getActivity()).finish();
            } else {
                Toast.makeText(view.getContext(), "Login failed!", Toast.LENGTH_LONG).show();
            }
        });

        viewmodel.getCheckFields().observe(LoginFragment.this, s -> {
            animation.stopAnimation();
            if (s.contains("username")) {
                username.setError(s);
            } else {
                password.setError(s);
            }
        });

        Button btnLogin = view.findViewById(R.id.main_btnLogin);
        btnLogin.setOnClickListener(view1 -> {
            animation.startAnimation();
            viewmodel.checkFields(username.getText().toString(), password.getText().toString());
        });

        return view;
    }
}
