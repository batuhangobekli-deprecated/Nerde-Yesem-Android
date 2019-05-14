package com.example.neyesem.view.categories;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.neyesem.R;
import com.example.neyesem.shared.BaseFragment;

public class CategoriesFragment extends BaseFragment {
    private View parentView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (parentView == null) {
            
        }
        return parentView;
    }


    public static CategoriesFragment newInstance(){
        return new CategoriesFragment();
    }
}
