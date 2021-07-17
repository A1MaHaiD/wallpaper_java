package com.example.wallpaperjava.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.wallpaperjava.R;

public class Functions {
    private static void changeMainFragment(FragmentActivity fragmentActivity, Fragment fragment){
         fragmentActivity.getSupportFragmentManager()
                 .beginTransaction()
                 .replace(R.id.main_container,fragment)
                 .commit();
    }
}
