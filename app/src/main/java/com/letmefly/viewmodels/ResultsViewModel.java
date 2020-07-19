package com.letmefly.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ResultsViewModel extends AndroidViewModel {

    MutableLiveData<String[]> strings;

    public ResultsViewModel(@NonNull Application app){
        super(app);
        strings = new MutableLiveData<>();
    }

}