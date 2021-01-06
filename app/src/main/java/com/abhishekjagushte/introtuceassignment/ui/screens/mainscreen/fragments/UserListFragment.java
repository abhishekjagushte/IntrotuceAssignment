package com.abhishekjagushte.introtuceassignment.ui.screens.mainscreen.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhishekjagushte.introtuceassignment.R;
import com.abhishekjagushte.introtuceassignment.model.User;
import com.abhishekjagushte.introtuceassignment.repository.DataRepository;
import com.abhishekjagushte.introtuceassignment.ui.screens.mainscreen.adapters.UserListAdapter;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class UserListFragment extends Fragment {


    private String TAG = "UserListFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        UserListAdapter adapter = new UserListAdapter();
        recyclerView.setAdapter(adapter);

        DataRepository.getInstance().getUsers().addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                ArrayList<User> list = new ArrayList<>();
                try {
                    for (int i = 0; i < value.getDocuments().size(); i++) {
                        list.add(value.getDocuments().get(i).toObject(User.class));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                adapter.updateList(list);
                adapter.notifyDataSetChanged();
                Log.d(TAG, "onEvent: "+list.size());
            }
        });

        return view;
    }
}