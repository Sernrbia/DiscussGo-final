package com.example.discussgo.firstscreen.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.discussgo.firstscreen.repository.UserRepository;
import com.example.discussgo.firstscreen.viewmodel.RegisterVM;

public class RegisterFactory implements ViewModelProvider.Factory {

    private final UserRepository repository;

    public RegisterFactory(UserRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public RegisterVM create(@NonNull Class modelClass) {
        return new RegisterVM(repository);
    }
}