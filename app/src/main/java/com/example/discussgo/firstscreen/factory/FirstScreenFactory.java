package com.example.discussgo.firstscreen.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.discussgo.firstscreen.repository.UserRepository;
import com.example.discussgo.firstscreen.viewmodel.LogInVM;

public class FirstScreenFactory implements ViewModelProvider.Factory {

    private final UserRepository repository;

    public FirstScreenFactory(UserRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public LogInVM create(@NonNull Class modelClass) {
        return new LogInVM(repository);
    }
}