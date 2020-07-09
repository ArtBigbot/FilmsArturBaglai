package com.example.task2films

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream


class MainActivity : AppCompatActivity() {

   //var adapter: ArrayAdapter<String> ()
   // var listview =
    var genresSplited = arrayListOf<String>()
   public var  arrayGenre = arrayListOf<String>()
    var titlesArray = arrayListOf<String>()
    var  titleSelected : String? = null;
    public var categorySelected: String? = null;
  //  var filmsArrayList: ArrayList<Film>
    var filmsArrayList = arrayListOf<Film>()

    var foundFilmsNames =arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        read_jsonFIle()

        var adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayGenre)
        listView.adapter = adapter ;

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            categorySelected=arrayGenre[position] ;
            val intent  = Intent(this, FilmActivity::class.java )
            startActivity(intent)
            intent.putExtra("filmCategory" , categorySelected)
            startActivity(intent);

        }
        val searchButton = findViewById<ImageButton>(R.id.imageButtonSerach)
        imageButtonSerach.setOnClickListener {
            foundFilmsNames.clear()
            remove(adapter)
            search(adapter)

        }


    }
    fun read_jsonFIle(){

        var json : String? = null;
        try{


        val inputStream:InputStream = assets.open("films.json");
        json = inputStream.bufferedReader().use { it.readText() }

        var  jsonArray =JSONArray(json);
        for (i in 0..jsonArray.length()-1)
        {
            var jsonobject = jsonArray.getJSONObject(i);
            arrayGenre.add(jsonobject.getString("Genre"));
            var delimiter =","
            val strSplited =
            titlesArray.add(jsonobject.getString("Title"))

            var film = Film(jsonobject.getString("Title"),
                jsonobject.getString("Year"),
                jsonobject.getString("Runtime"),
                jsonobject.getString("Genre"),

                jsonobject.getString("Director"),
                jsonobject.getString("Writer"),
                jsonobject.getString("Actors"),
                jsonobject.getString("Plot"),
                jsonobject.getString("Language"),
                jsonobject.getString("Country"),
                jsonobject.getString("Awards"))

            filmsArrayList.add(film)

        }

/*
            for (i in 0..arrayGenre.size){
                val str = arrayGenre[i].split(",")
                genresSplited.add(str.toString())
            }
            */
            arrayGenre.sort()
            arrayGenre.distinct()


        }catch (e:IOException)
        {

        }

    }
    fun search(adapter: ArrayAdapter<String>){
        filmsArrayList.forEach {

            if(it.title!!.contains(TextFieldSearch.getText().toString())||
                it.year!!.contains(TextFieldSearch.getText().toString())||
                it.genre!!.contains(TextFieldSearch.getText().toString())||
                it.director!!.contains(TextFieldSearch.getText().toString())||
                it.actors!!.contains(TextFieldSearch.getText().toString())

            )
            {
                foundFilmsNames.add(it.title.toString())
                adapter.notifyDataSetChanged()
            }

        }

        var adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,foundFilmsNames)
        listView.adapter = adapter ;
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            titleSelected=foundFilmsNames[position] ;
            val intent  = Intent(this, CardViewActivity::class.java)
            intent.putExtra("filmTitle" , titleSelected)
            startActivity(intent);
            Toast.makeText(applicationContext, "Item selected: "+ foundFilmsNames[position],Toast.LENGTH_LONG).show()

        }

        if(foundFilmsNames.size ==0){
            adapter.notifyDataSetChanged()
            Toast.makeText(this,"Nothing found ",Toast.LENGTH_SHORT).show()
        }

    }
    fun remove(adapter: ArrayAdapter<String>){
        /*
        for (i in 0 until arrayGenre.size){
            adapter.remove(arrayGenre.get(i))
        }*/
        arrayGenre.clear()
        adapter.notifyDataSetChanged()
}



}
