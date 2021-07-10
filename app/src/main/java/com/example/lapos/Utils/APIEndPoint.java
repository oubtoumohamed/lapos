package com.example.lapos.Utils;

import com.example.lapos.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIEndPoint {

    /*@GET("api/etudient/login")
    Call<User> login(
            @Header("accept") String type,
            @Query("cne") String cne,
            @Query("cin") String cin
    );*/

    @FormUrlEncoded
    @POST("user/login")
    Call<User> login(
            @Header("accept") String type,
            @Field("email") String email,
            @Field("password") String password
    );

    /*@GET("etudient/course")
    Call<Seance> etudient_course(
            @Header("accept") String type,
            @Header("Authorization") String authorization,
            @Query("id") int id
    );

    @GET("etudient/courses")
    Call<Seance> etudient_course_now(
            @Header("accept") String type,
            @Header("Authorization") String authorization,
            //@Query("filter") String filter,
            @QueryMap(encoded=true) Map<String,String> filter
    );

    @GET("etudient/courses")
    Call<ArrayList<Seance>> etudient_courses(
            @Header("accept") String type,
            @Header("Authorization") String authorization,
            //@Query("filter") String filter,
            @QueryMap(encoded=true) Map<String,String> filter
    );

    @GET("etudient/modules_semesters")
    Call<ArrayList<Semestre>> etudient_modules_semesters(
            @Header("accept") String type,
            @Header("Authorization") String authorization
    );

    @GET("etudient/course/messages")
    Call<ArrayList<Message>> etudient_messages(
            @Header("accept") String type,
            @Header("Authorization") String authorization,
            @Query("cours_id") int cours_id,
            @Query("id") int id
    );

    @GET("etudient/course/messages/send")
    Call<Boolean> etudient_send_message(
            @Header("accept") String type,
            @Header("Authorization") String authorization,
            @Query("cours_id") int course_id,
            @Query("msg") String msg
    );

    @GET("etudient/details")
    Call<Etudiant> etudient_details(
            @Header("accept") String type,
            @Query("cne") String cne,
            @Query("cin") String cin
    );


    *
    * Prof
    *
    * *


    @GET("prof/login")
    Call<ResponseBody> prof_login(
            @Header("accept") String type,
            @Query("email") String matricule,
            @Query("password") String cin
    );*/

    /*@FormUrlEncoded
    @POST("Fees.php")
    Call<ResponseBody> Fees(@Header("accept") String type, @Field("id") String StdID);*/


}