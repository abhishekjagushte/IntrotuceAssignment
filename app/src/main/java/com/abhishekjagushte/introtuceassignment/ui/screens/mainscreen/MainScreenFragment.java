package com.abhishekjagushte.introtuceassignment.ui.screens.mainscreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhishekjagushte.introtuceassignment.R;
import com.abhishekjagushte.introtuceassignment.ui.screens.mainscreen.adapters.MainScreenFragmentStateAdapter;

public class MainScreenFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main_screen, container, false);
        FragmentStateAdapter adapter = new MainScreenFragmentStateAdapter(this);
        ViewPager2 viewPager2 = view.findViewById(R.id.viewpager2);
        viewPager2.setAdapter(adapter);

        return view;
    }
}