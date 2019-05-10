package com.example.neyesem.shared;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.neyesem.R;
import com.example.neyesem.view.categories.CategoriesFragment;
import com.example.neyesem.view.nearbyrestaurant.NearbyRestaurantsFragment;
import com.ncapdevi.fragnav.FragNavController;
import com.ncapdevi.fragnav.FragNavTransactionOptions;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements FragNavController.TransactionListener {

    private ImageView logo;
    private TextView title;
    private ImageView back;
    private ImageView mapButton;
    private FragNavController fragNavController;
    private ImageView nearbyRestaurantsImage;
    private ImageView categoriesImage;
    private LinearLayout nearbyRestaurantsButton;
    private LinearLayout categoriesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews(savedInstanceState);

    }
    private void initViews(Bundle savedInstanceState){
        title = findViewById(R.id.brandbar_title);
        logo = findViewById(R.id.brandbar_logo);
        back = findViewById(R.id.brandbar_back);
        mapButton = findViewById(R.id.brandbar_mapbutton);
        nearbyRestaurantsButton = findViewById(R.id.linearlayout_main_nearbyrestaurants);
        categoriesButton = findViewById(R.id.linearlayout_main_categories);
        nearbyRestaurantsImage = findViewById(R.id.imageview_main_nearbyrestaurants);
        categoriesImage = findViewById(R.id.imageview_main_categories);

        nearbyRestaurantsButton.setOnClickListener(v -> {
            clickTab(0);
            nearbyRestaurantsImage.setColorFilter(getResources().getColor(R.color.colorPrimary));
            categoriesImage.setColorFilter(getResources().getColor(R.color.colorGrey));

        });

        categoriesButton.setOnClickListener(v -> {
            clickTab(1);
            nearbyRestaurantsImage.setColorFilter(getResources().getColor(R.color.colorGrey));
            categoriesImage.setColorFilter(getResources().getColor(R.color.colorPrimary));
        });

        List<Fragment> fragments = new ArrayList<>(2);
        fragments.add(NearbyRestaurantsFragment.newInstance());
        fragments.add(CategoriesFragment.newInstance());

        FragNavTransactionOptions.Builder transactionOptions = FragNavTransactionOptions.newBuilder();
        transactionOptions.customAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in, R.anim.fade_out);
        fragNavController = FragNavController.newBuilder(savedInstanceState,getSupportFragmentManager(),R.id.framelayout_main).transactionListener(this).rootFragments(fragments).build();

        onScreenUpdate();
    }
    public void onScreenUpdate() {
        BaseFragment baseFragment = (BaseFragment) fragNavController.getCurrentFrag();
        title.setText(baseFragment.getTitle());
        if(fragNavController.getCurrentStack().size() == 1) {
            logo.setVisibility(View.VISIBLE);
            mapButton.setVisibility(View.VISIBLE);
            back.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
        }
    }
    public void switchTab(int position){
        fragNavController.switchTab(position);
        onScreenUpdate();
    }

    public void clickTab(int position) {
        if(fragNavController.getCurrentStackIndex() == position){
            fragNavController.clearStack();
            onScreenUpdate();
        }
        else{
            switchTab(position);
        }
    }

    @Override
    public void onBackPressed() {
        if(fragNavController.getCurrentStack().size() > 1)
            popFragment();
        else
            startExitProcess();
        onScreenUpdate();
    }
    public void pushFragment(Fragment fragment, boolean a){
        fragNavController.pushFragment(fragment);//Stacke ekle
        onScreenUpdate();
    }
    public void popFragment(){
        fragNavController.popFragment();//Stack çıkar
        onScreenUpdate();
    }
    public void replaceFragment(Fragment fragment){
        fragNavController.replaceFragment(fragment);
        onScreenUpdate();
    }
    public void startExitProcess(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(getResources().getString(R.string.sure_to_exit));
        dialogBuilder.setCancelable(true);
        dialogBuilder.setPositiveButton(getResources().getString(R.string.yes), (dialog, which) -> {
            finishAffinity();
            System.exit(0);
        });
        dialogBuilder.setNegativeButton(getResources().getString(R.string.no), (dialog, which) -> dialog.cancel());
        dialogBuilder.show();
    }
    @Override
    public void onTabTransaction(@Nullable android.support.v4.app.Fragment fragment, int i) {

    }

    @Override
    public void onFragmentTransaction(android.support.v4.app.Fragment fragment, FragNavController.TransactionType transactionType) {

    }
}
