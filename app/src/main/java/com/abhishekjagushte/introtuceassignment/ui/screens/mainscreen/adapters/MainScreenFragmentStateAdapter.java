package com.abhishekjagushte.introtuceassignment.ui.screens.mainscreen.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.abhishekjagushte.introtuceassignment.ui.screens.mainscreen.fragments.UserInputFragment;
import com.abhishekjagushte.introtuceassignment.ui.screens.mainscreen.fragments.UserListFragment;

public class MainScreenFragmentStateAdapter extends FragmentStateAdapter {
    public MainScreenFragmentStateAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new UserListFragment();
            case 1: return new UserInputFragment();
        }

        //if not above
        throw new IllegalStateException("Main Fragment position not 0 or 1");
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
