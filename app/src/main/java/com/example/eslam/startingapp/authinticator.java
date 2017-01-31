package com.example.eslam.startingapp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by islam on 25/01/17.
 */

public class authinticator {
    FirebaseUser user;
    public authinticator(){
        user= FirebaseAuth.getInstance().getCurrentUser();
    }
    public String getUserId(){
        return user.getUid();
    }
    public void logout(){
        FirebaseAuth.getInstance().signOut();
    }
}
