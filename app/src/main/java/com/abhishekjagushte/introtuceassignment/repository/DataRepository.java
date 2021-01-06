package com.abhishekjagushte.introtuceassignment.repository;

import com.abhishekjagushte.introtuceassignment.model.User;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.Query;

public class DataRepository {

    private static DataRepository instance = null;
    private static FirebaseFirestore firestore = null;

    public static DataRepository getInstance(){
        if(instance==null) {
            instance = new DataRepository();
            firestore = FirebaseFirestore.getInstance();
        }

        return instance;
    }

    public Task<Void> addUser(User user){
        DocumentReference doc = firestore.collection("users").document();
        user.setId(doc.getId());
        return doc.set(user);
    }

    public Query getUsers(){
        Query query = firestore.collection("users").orderBy("added", Query.Direction.DESCENDING);

        return query;
    }

    public void deleteUser(User user) {
        String id = user.getId();
        firestore.collection("users").document(id).delete();
    }
}
