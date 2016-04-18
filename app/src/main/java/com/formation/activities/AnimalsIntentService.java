package com.formation.activities;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.formation.data.AnimalContract;
import com.formation.model.Animal;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public class AnimalsIntentService extends IntentService {

    private static final String TAG = AnimalsIntentService.class.getSimpleName();

    //10.0.3.2 = pour appeller le WebService depuis localhost:8180
    public static final String API_URL = "http://10.0.3.2:8180/";

    public AnimalsIntentService() {
        super("AnimalsIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ZooService client = retrofit.create(ZooService.class);
            Call<List<Animal>> call = client.getAnimals();
            if (call != null) {
                List<Animal> animals = null;

/*              // Version1: Async call
                Callback<List<Animal>> callback = new Callback<List<Animal>>() {
                    @Override
                    public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                        animals = response.body();
                    }

                    @Override
                    public void onFailure(Call<List<Animal>> call, Throwable t) {
                        Log.w(TAG,"Error when calling webservice (with call.execute())!", t)
                    }
                };
                call.enqueue(callback);*/

                // Version 2:Sync call
                try {
                    Response<List<Animal>> response = call.execute();
                    if(response.isSuccessful()){
                        //Toast.makeText(getBaseContext(), Boolean.toString(response.isSuccessful()), Toast.LENGTH_SHORT).show();

                        animals = response.body();

                        for (Animal animal : animals) {
                            Log.e("ANIMAL:", animal.getDiet() + " " + animal.getFamily() + " " + animal.getName() + " " + animal.getSex() + " " + Integer.toString(animal.getAge()));
                            //Toast.makeText(getBaseContext(), "Retrieved animal ", Toast.LENGTH_SHORT).show();

                            // DB insertion via ContentProvider
                            Uri uri = Uri.parse(AnimalContract.Animals.SCHEME + AnimalContract.Animals.AUTHORITY + AnimalContract.Animals.SEPARATOR + AnimalContract.Animals.TABLE_NAME);

                            ContentValues contentValues = new ContentValues();
                            contentValues.put(AnimalContract.Animals.COLUMN_NAME_ANIMAL_ID, Long.toString(animal.getId()));
                            contentValues.put(AnimalContract.Animals.COLUMN_NAME_DIET, animal.getDiet());
                            contentValues.put(AnimalContract.Animals.COLUMN_NAME_FAMILY, animal.getFamily());
                            contentValues.put(AnimalContract.Animals.COLUMN_NAME_NAME, animal.getName());
                            contentValues.put(AnimalContract.Animals.COLUMN_NAME_SEX, animal.getSex());
                            contentValues.put(AnimalContract.Animals.COLUMN_NAME_AGE, Integer.toString(animal.getAge()));

                            uri = getContentResolver().insert(uri, contentValues);
                            //Toast.makeText(getBaseContext(), "New record inserted : " + uri.toString(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Log.w(TAG,"No response !!");
                    }
                } catch (IOException e) {
                    Log.w(TAG,"Error when calling webservice (with call.execute())!", e);
                }
            } else {
                Log.e("CALL ", "null");
            }
        }
    }

    public interface ZooService {
        @Headers("Accept: application/json")
        @GET("zoo/rest/animals")
        Call<List<Animal>> getAnimals();
    }
}
