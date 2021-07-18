package com.example.wallpaperjava.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.wallpaperjava.R;

public class Functions {
    public static void changeMainFragment(FragmentActivity fragmentActivity, Fragment fragment){
         fragmentActivity.getSupportFragmentManager()
                 .beginTransaction()
                 .replace(R.id.main_container,fragment)
                 .commit();
    }
    public static void changeMainFragmentWithBack(FragmentActivity fragmentActivity, Fragment fragment){
        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container,fragment)
                .addToBackStack(null)
                .commit();
    }
}
