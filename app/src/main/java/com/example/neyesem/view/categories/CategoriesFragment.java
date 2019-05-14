package com.example.neyesem.view.categories;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.neyesem.R;
import com.example.neyesem.model.categories.CategoriesResponse;
import com.example.neyesem.shared.BaseFragment;

import retrofit2.Response;

public class CategoriesFragment extends BaseFragment implements CategoriesView{
    private View parentView;
    private RecyclerView categoriesRecyclerView;
    private RelativeLayout container;
    private CategoriesAdapter adapter = new CategoriesAdapter();
    private CategoriesPresenter presenter = new CategoriesPresenter(this);


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (parentView == null) {
            parentView = inflater.inflate(R.layout.fragment_categories, container, false);
            initViews();
            getCategories();
        }
        return parentView;
    }
    private void initViews(){
        container = parentView.findViewById(R.id.relativelayout_categories_container);
        categoriesRecyclerView = parentView.findViewById(R.id.recyclerview_categories);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        categoriesRecyclerView.setAdapter(adapter);
    }
    private void getCategories(){
        presenter.getCategories((AppCompatActivity) getActivity(),container);
    }

    public static CategoriesFragment newInstance(){
        return new CategoriesFragment();
    }

    @Override
    public void onGetCategories(CategoriesResponse response) {
        adapter.setList(response.getCategories());
    }

    @Override
    public void onConfirmDialog() {

    }

    @Override
    public void onRetryLayout() {

    }

    @Override
    public void onUserError(Response serverResponse) {

    }
}
