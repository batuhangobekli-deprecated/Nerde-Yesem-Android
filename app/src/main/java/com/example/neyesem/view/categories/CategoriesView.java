package com.example.neyesem.view.categories;

import com.example.neyesem.model.categories.CategoriesResponse;
import com.example.neyesem.shared.BaseView;

public interface CategoriesView  extends BaseView {
    void onGetCategories(CategoriesResponse response);
}
