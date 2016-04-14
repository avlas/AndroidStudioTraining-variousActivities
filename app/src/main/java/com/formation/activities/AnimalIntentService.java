package com.formation.activities;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.formation.model.Animal;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class AnimalIntentService extends IntentService {
    public static final String API_URL = "https://localhost:8180/zoo/rest/";
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public AnimalIntentService() {
        super("AnimalIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit = builder.client(httpClient.build()).build();
            ZooService client = retrofit.create(ZooService.class);
            Call<List<Animal>> call = client.getAnimals();
            if (call != null) {
                List<Animal> animals = null;
                try {
                    Response<List<Animal>> response = call.execute();
                    animals = response.body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (Animal animal : animals) {
                    Log.e("ANIMAL:", animal.getDiet() + " " + animal.getFamily() + " " + animal.getName() + " " + animal.getSex() + " " + Integer.toString(animal.getAge()));
                }
            } else {
                Log.e("CALL ", "null");
            }
/*            List<Animal> animals = client.listAnimals();
            for (Animal animal : animals) {
                Log.e("ANIMAL:", animal.getDiet() + " " + animal.getFamily() + " " + animal.getName() + " " + animal.getSex() + " " + Integer.toString(animal.getAge()));
            }*/
        }
    }

    public interface ZooService {
        @GET("animals")
        Call<List<Animal>> getAnimals();
        //List<Animal> getAnimals();
    }
}
