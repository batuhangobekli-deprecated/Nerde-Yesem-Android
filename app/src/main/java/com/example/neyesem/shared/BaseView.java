package com.example.neyesem.shared;

import retrofit2.Response;

public interface BaseView {
    void onConfirmDialog();
    void onRetryLayout();
    void onUserError(Response serverResponse);
}
