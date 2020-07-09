package com.example.task2films

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.cardview_activity.*
import org.json.JSONArray
import java.io.InputStream

class CardViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cardview_activity)

        var json: String? = null;
        var title: String = intent.getStringExtra("filmTitle")


        val inputStream: InputStream = assets.open("films.json");
        json = inputStream.bufferedReader().use { it.readText() }
        var jsonArray = JSONArray(json);
        for (i in 0..jsonArray.length() - 1) {
            var jsonobject = jsonArray.getJSONObject(i);
            //val mainActivity = MainActivity();

            if (jsonobject.getString("Title").contains(title)) {
                titleCard2.setText(jsonobject.getString("Title"))
                yearCard2.setText(jsonobject.getString("Year"))
                runtimeCard2.setText(jsonobject.getString("Runtime"))
                genreCard2.setText(jsonobject.getString("Genre"))
                directorCard2.setText(jsonobject.getString("Director"))
                writerCard2.setText(jsonobject.getString("Writer"))
                actorsCard2.setText(jsonobject.getString("Actors"))
                plotCard2.setText(jsonobject.getString("Plot"))
                languageCard2.setText(jsonobject.getString("Language"))
                countryCard2.setText(jsonobject.getString("Country"))
                awardsCard2.setText(jsonobject.getString("Awards"))


            }

        }
    }
}