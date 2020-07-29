package com.example.lab4.Screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab4.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Lesson2Activity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson2);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navigationItemView = (BottomNavigationView)findViewById(R.id.bottomNavigator);
        navigationItemView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.music:
                        toolbar.setTitle("Music");
                        return true;
                    case R.id.favorite:
                        toolbar.setTitle("Favorite");
                        return true;
                    case R.id.time:
                        toolbar.setTitle("Time");
                        return true;
                }
                return false;
            }
        });
        createListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenuitem, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void createListView(){

        final ArrayList<String> listButton = new ArrayList<>();
        for(String i : createData()){
            listButton.add(i.toString());
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listButton);
        final ListView mList = (ListView)findViewById(R.id.list_item);
        mList.setAdapter(adapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                registerForContextMenu(mList);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.optionmenuitem, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                Toast.makeText(this, "Find successed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove:
                Toast.makeText(this, "Catch successed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.edit:
                Toast.makeText(this, "Drop successed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.update:
                Toast.makeText(this, "Evelution successed", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private ArrayList<String> createData(){
        ArrayList<String> pokemon = new ArrayList();
        pokemon.add("Bulbasaur");
        pokemon.add("Charizard");
        pokemon.add("Blastoise");
        pokemon.add("Butterfree");
        pokemon.add("Beedrill");
        pokemon.add("Pidgeot");
        pokemon.add("Raichu");
        pokemon.add("Pikachu");
        pokemon.add("Growlithe");
        pokemon.add("Arcanine");
        pokemon.add("Kadabra");
        pokemon.add("Haunter");
        pokemon.add("Electrode");
        pokemon.add("Kangaskhan");
        pokemon.add("Electabuzz");
        pokemon.add("Lapras");
        pokemon.add("Gyarados");
        pokemon.add("Articuno");
        pokemon.add("Mewtwo");
        pokemon.add("Crobat");
        return pokemon;
    }
}
