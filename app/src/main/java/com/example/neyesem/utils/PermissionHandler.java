package com.example.neyesem.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;
import com.example.neyesem.BuildConfig;


import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;

public class PermissionHandler {
    private Context ctx;
    private String ErrorMessage;
    private PermissionHandlerListener PermissionListener;
    private int RequestIdentifier;
    private com.yanzhenjie.permission.PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            if (PermissionListener != null)
                PermissionListener.OnGrantPermission(RequestIdentifier);
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            Toast.makeText(ctx, "fail", Toast.LENGTH_LONG).show();
            AlertDialog.newBuilder(ctx)
                    .setTitle("Bi'Tabak")
                    .setMessage(ErrorMessage)
                    .setPositiveButton("Tamam", (dialog, which) -> {
                        if (AndPermission.hasAlwaysDeniedPermission(ctx, deniedPermissions)) {
                            ctx.startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID)));
                        } else {
                            for (String i : deniedPermissions) {
                                if (!checkPermission(i))
                                    requestPermission(i, ErrorMessage, RequestIdentifier, PermissionListener);
                            }
                        }
                    })
                    .setCancelable(false).show();

        }
    };

    public PermissionHandler(Context Context) {
        ctx = Context;
    }

    public boolean checkPermission(String Permission) {
        return ctx.checkCallingOrSelfPermission(Permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermission(String Permission, String ErrorMessage, int RequestIdentifier, PermissionHandlerListener PermissionListener) {
        this.PermissionListener = PermissionListener;
        this.RequestIdentifier = RequestIdentifier;
        this.ErrorMessage = ErrorMessage;
        if (!checkPermission(Permission)) {
            AndPermission.with(ctx)
                    .permission(Permission).callback(listener).start();
        }
    }
}