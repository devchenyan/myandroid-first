package com.example.echo.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private String[] data = { "Apple", "Banana", "Orange",
//            "Watermelon", "Pear", "Grape", "Pineapple",
//            "Strawberry", "Cherry", "Mango"};

    private List<Fruit> fruitList = new ArrayList<Fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
// R.layout.support_simple_spinner_dropdown_item, data);

        initFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);

        ListView listView = (ListView) findViewById(R.id.list_view);
        if (listView != null) {
            listView.setAdapter(adapter);
        }
    }

    private void initFruits() {
        Fruit apple = new Fruit("apple", 1);
        fruitList.add(apple);
        Fruit banana = new Fruit("banana", 2);
        fruitList.add(banana);
        Fruit orange = new Fruit("orange", 1);
        fruitList.add(orange);
        Fruit mango = new Fruit("mango", 2);
        fruitList.add(mango);

    }
}
