package com.example.neyesem.utils;

public interface PermissionHandlerListener {
    //OnSuccess = 1;
    //OnFailed = 0;
    void OnGrantPermission(int RequestIdentifier);
}
