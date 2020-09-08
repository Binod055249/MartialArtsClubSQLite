package com.example.martialartsclubsqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.martialartsclubsqlite.model.DatabaseHandler;
import com.example.martialartsclubsqlite.model.MartialArts;

import java.util.ArrayList;

public class DeleteMartialArtsActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener ,
        View.OnClickListener{

    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_martial_arts);

        databaseHandler = new DatabaseHandler(this);

        updateTheUserInterface();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){

            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateTheUserInterface(){

        ArrayList<MartialArts> allMartialArtsObject = databaseHandler.returnAllMaritalArtsObject();
        RelativeLayout relativeLayout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup radioGroup = new RadioGroup(this);

        for(MartialArts martialArts : allMartialArtsObject) {

            RadioButton currentRadioButton = new RadioButton(this);
            currentRadioButton.setId(martialArts.getMartialArtsId());
            currentRadioButton.setText(martialArts.toString());
            currentRadioButton.setTextSize(18f);
            radioGroup.addView(currentRadioButton);

        }

        radioGroup.setOnCheckedChangeListener(DeleteMartialArtsActivity.this);

        Button btnBack = new Button(this);
        btnBack.setText("Go Back");
        btnBack.setOnClickListener(this);

        scrollView.addView(radioGroup);

        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
                                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                                    RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParams.setMargins(0,0,0,70);

        relativeLayout.addView(btnBack, buttonParams);

        ScrollView.LayoutParams scrollViewParams = new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT,
                                                                   ScrollView.LayoutParams.MATCH_PARENT);

        relativeLayout.addView(scrollView, scrollViewParams);

        setContentView(relativeLayout);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        databaseHandler.deletMartialArtObjectFromDatabaseById(checkedId);
        Toast.makeText(this, "The MartialArt object is deleted", Toast.LENGTH_SHORT).show();
        updateTheUserInterface();
    }

    @Override
    public void onClick(View v) {

        finish();
    }
}