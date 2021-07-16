package com.example.temanautis.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;

public class SessionManager {
    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String USER_ID = "user_id";
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String TOKEN = "token";

    public SessionManager (Context context){
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(LoginData user,String token){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putInt(USER_ID, user.getId());
        editor.putString(EMAIL, user.getEmail());
        editor.putString(NAME, user.getName());
        editor.putString(TOKEN, token);
        editor.commit();

    }

    public HashMap getUserDetail(){
        HashMap user = new HashMap<>();
        user.put(USER_ID, sharedPreferences.getInt(USER_ID, 0));
        user.put(EMAIL,sharedPreferences.getString(EMAIL, null));
        user.put(NAME, sharedPreferences.getString(NAME, null));
        user.put(TOKEN, sharedPreferences.getString(TOKEN, null));
        return user;
    }

    public void logoutSession(){
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

}
