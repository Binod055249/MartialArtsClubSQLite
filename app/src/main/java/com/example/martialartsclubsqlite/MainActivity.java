package com.example.martialartsclubsqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.martialartsclubsqlite.model.DatabaseHandler;
import com.example.martialartsclubsqlite.model.MartialArts;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

   private DatabaseHandler databaseHandler;
   private double totalMartialArtsPrice;
   private ScrollView scrollView;
   private int martialArtButtonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = new DatabaseHandler(this);
        totalMartialArtsPrice = 0.0;
        scrollView = findViewById(R.id.scrollView);

        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        martialArtButtonWidth = screenSize.x / 2;

        modifyUserInterface();


    }

    private void modifyUserInterface() {


        ArrayList<MartialArts> allMartialArtObjects =
                databaseHandler.returnAllMaritalArtsObject();

        scrollView.removeAllViewsInLayout();


        if (allMartialArtObjects.size() > 0) {



            GridLayout gridLayout = new GridLayout(MainActivity.this);
            gridLayout.setRowCount((allMartialArtObjects.size() + 1) / 2);
            gridLayout.setColumnCount(2);

            MartialArtButton[] martialArtButtons = new MartialArtButton[allMartialArtObjects.size()];
            int index = 0;

            for (MartialArts martialArtObject : allMartialArtObjects) {


                martialArtButtons[index] = new MartialArtButton(
                        MainActivity.this, martialArtObject);
                martialArtButtons[index].setText(martialArtObject.getMartialArtsId() +
                        "\n" + martialArtObject.getMartialArtsName() + "\n"
                        + martialArtObject.getMartialArtsPrice());

                switch (martialArtObject.getMartialArtsColor()) {


                    case "Red":
                        martialArtButtons[index].setBackgroundColor(Color.RED);
                        break;
                    case "Blue":
                        martialArtButtons[index].setBackgroundColor(Color.BLUE);
                        break;
                    case "Black":
                        martialArtButtons[index].setBackgroundColor(Color.BLACK);
                        break;
                    case "Yellow":
                        martialArtButtons[index].setBackgroundColor(Color.YELLOW);
                        break;
                    case "Purple":
                        martialArtButtons[index].setBackgroundColor(Color.CYAN);
                        break;
                    case "Green":
                        martialArtButtons[index].setBackgroundColor(Color.GREEN);
                        break;
                    case "White":
                        martialArtButtons[index].setBackgroundColor(Color.WHITE);
                        break;
                    default:
                        martialArtButtons[index].setBackgroundColor(Color.GRAY);
                        break;


                }


                martialArtButtons[index].setOnClickListener(MainActivity.this);
                gridLayout.addView(martialArtButtons[index],
                        martialArtButtonWidth,
                        ViewGroup.LayoutParams.WRAP_CONTENT);


            }


            scrollView.addView(gridLayout);


        }


    }

    @Override
    public void onClick(View view) {

        MartialArtButton martialArtButton = (MartialArtButton) view;
        totalMartialArtsPrice = totalMartialArtsPrice + martialArtButton.getMartialArtPrice();
        String  martialArtsPriceFormatted = NumberFormat.getCurrencyInstance().format(totalMartialArtsPrice);
        Toast.makeText(this, martialArtsPriceFormatted, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        modifyUserInterface();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.my_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.add_martial_arts:

                Intent addMartialArtIntent=new Intent(this,AddMartialArtsActivity.class);
                startActivity(addMartialArtIntent);
           return true;

            case R.id.update_martial_arts:

                Intent updateIntent = new Intent(MainActivity.this, UpdateMartialArtsActivity.class);
                startActivity(updateIntent);
                return true;


            case R.id.delete_martial_arts:

                Intent deleteIntent = new Intent(MainActivity.this, DeleteMartialArtsActivity.class);
                startActivity(deleteIntent);
                return true;

            case R.id.martial_arts_price_reset:
                totalMartialArtsPrice=0.0;
                Toast.makeText(this, "Total MartialArt Price is reset", Toast.LENGTH_SHORT).show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}