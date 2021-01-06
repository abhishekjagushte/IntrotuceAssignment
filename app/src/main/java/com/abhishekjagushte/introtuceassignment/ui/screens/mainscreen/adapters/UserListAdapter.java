package com.abhishekjagushte.introtuceassignment.ui.screens.mainscreen.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhishekjagushte.introtuceassignment.R;
import com.abhishekjagushte.introtuceassignment.model.User;
import com.abhishekjagushte.introtuceassignment.repository.DataRepository;

import java.util.ArrayList;
import java.util.Calendar;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListItemViewHolder> {

    ArrayList<User> users = new ArrayList<User>();
    Calendar calendar = Calendar.getInstance();

    public void updateList(ArrayList<User> users){
        this.users = users;
    }

    @NonNull
    @Override
    public UserListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        return new UserListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListItemViewHolder holder, int position) {
        User user = users.get(position);
        holder.userNameTextView.setText(user.getFirstName() + " " + user.getLastName());
        holder.userInfoTextView.setText(user.getGender()+" | "+getAge(user)+ " | "+ user.getHometown());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataRepository.getInstance().deleteUser(user);
            }
        });
    }

    private int getAge(User user){
        int birthYear = Integer.parseInt(user.getDateOfBirth().split("/")[2]);
        return calendar.get(Calendar.YEAR) - birthYear -1;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class UserListItemViewHolder extends RecyclerView.ViewHolder{

        TextView userNameTextView;
        TextView userInfoTextView;
        ImageView displayPicture;
        ImageButton deleteButton;

        public UserListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.name_text_view);
            userInfoTextView = itemView.findViewById(R.id.info_text_view);
            displayPicture = itemView.findViewById(R.id.displayPicture);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

}
