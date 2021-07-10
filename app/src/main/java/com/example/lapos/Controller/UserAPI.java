package com.example.lapos.Controller;


import androidx.core.util.Consumer;
import com.example.lapos.User;
import com.example.lapos.Utils.NetworkUtils;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAPI extends NetworkUtils {

    public User Object_from_Json(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, User.class);
    }
    public String Object_to_Json(User o){
        Gson gson = new Gson();
        return gson.toJson(o);
    }

    public void login(String email, String pasword, final Consumer<User> success, final Consumer<String> error){
        Call<User> call = apiEndPoint.login(
                "application/json",
                email,
                pasword
        );
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                try {
                    if (response.code() == 200) {
                        //e.etudiant = response.body();
                        //e.openMain();
                        success.accept( response.body() );
                    } else {
                        error.accept(  "Failed to login, please check your ID or password" );
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    error.accept( "Failed resp. code(" + response.code() + ") "+ ex.getMessage());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                error.accept(  "Failed : " + t.getMessage() );
            }
        });
    }


}
