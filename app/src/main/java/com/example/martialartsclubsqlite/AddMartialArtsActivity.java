package com.example.martialartsclubsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.martialartsclubsqlite.model.DatabaseHandler;
import com.example.martialartsclubsqlite.model.MartialArts;

public class AddMartialArtsActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtName, edtPrice, edtColor;
    Button btnAddMartialArt, btnGoBack;

    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_martial_arts);

        Toast.makeText(this, "This is martial art activity", Toast.LENGTH_SHORT).show();

        edtName = findViewById(R.id.edtName);
        edtColor = findViewById(R.id.edtColor);
        edtPrice = findViewById(R.id.edtPrice);

        btnAddMartialArt = findViewById(R.id.btnAddMartialArt);
        btnGoBack = findViewById(R.id.btnGoBack);

        databaseHandler = new DatabaseHandler(this);

        btnAddMartialArt.setOnClickListener(this);
        btnGoBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnAddMartialArt:
                addMartialArtObjectToTheDatabase();
                break;

            case R.id.btnGoBack:
             this.finish();
                break;
        }
    }

    private void addMartialArtObjectToTheDatabase(){

        String nameValue = edtName.getText().toString();
        String priceValue = edtPrice.getText().toString();
        String colorValue = edtColor.getText().toString();

        try{

            double priceDoubleValue = Double.parseDouble(priceValue);
            MartialArts martialArtsObject = new MartialArts(0,nameValue,priceDoubleValue,colorValue);
            databaseHandler.addMartialArts(martialArtsObject);
            Toast.makeText(this, martialArtsObject + "\nis added to the Database", Toast.LENGTH_LONG).show();

        }catch (Exception e){

            e.printStackTrace();
        }
    }
}