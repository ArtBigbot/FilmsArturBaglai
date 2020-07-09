package com.example.task2films

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.films_activity.*
import org.json.JSONArray
import java.io.InputStream

 class FilmActivity  :AppCompatActivity() {
     private lateinit var recyclerView: RecyclerView
     private lateinit var viewAdapter: RecyclerView.Adapter<*>
     private lateinit var viewManager: RecyclerView.LayoutManager
     private lateinit var titleFilmSelected: String
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.films_activity)




         var json: String? = null;

         val filmsArray = ArrayList<String>();
         var category: String = intent.getStringExtra("filmCategory")
         val inputStream: InputStream = assets.open("films.json");
         json = inputStream.bufferedReader().use { it.readText() }
         var jsonArray = JSONArray(json);


         for (i in 0..jsonArray.length() - 1) {
             var jsonobject = jsonArray.getJSONObject(i);


             if (jsonobject.getString("Genre").contains(category)) {
                 filmsArray.add(jsonobject.getString("Title"))
             }

         }
         var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, filmsArray)
         listViewFilms.adapter = adapter ;
         listViewFilms.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
             titleFilmSelected=filmsArray[position] ;
             val intent  = Intent(this, CardViewActivity::class.java )
             startActivity(intent)
             intent.putExtra("filmTitle" , titleFilmSelected)
             startActivity(intent);

         }

         }


     }

