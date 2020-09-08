package com.example.martialartsclubsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martialartsclubsqlite.model.DatabaseHandler;
import com.example.martialartsclubsqlite.model.MartialArts;

import java.util.ArrayList;

public class UpdateMartialArtsActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_martial_arts);

        databaseHandler = new DatabaseHandler(this);

        modifyUserInterface();

    }

    private void modifyUserInterface(){

        ArrayList<MartialArts> martialArtsObjects = databaseHandler.returnAllMaritalArtsObject();

        if(martialArtsObjects.size()>0){

            ScrollView scrollView = new ScrollView(this);
            GridLayout gridLayout = new GridLayout(this);
            gridLayout.setRowCount(martialArtsObjects.size());
            gridLayout.setColumnCount(5);

            TextView[] idTextViews = new TextView[martialArtsObjects.size()];
            EditText[][] edtNamesPricesAndColors = new EditText[martialArtsObjects.size()][3];
            Button[] modifyButtons = new Button[martialArtsObjects.size()];

            Point screenSize = new Point();
            getWindowManager().getDefaultDisplay().getSize(screenSize);

            int screenWidth = screenSize.x;
            int index = 0;

            for (MartialArts martialArtsObject :martialArtsObjects){

                idTextViews[index] = new TextView(this);
                idTextViews[index].setGravity(Gravity.CENTER);
                idTextViews[index].setText(martialArtsObject.getMartialArtsId() + "");

                edtNamesPricesAndColors[index][0] = new EditText(this);
                edtNamesPricesAndColors[index][1] = new EditText(this);
                edtNamesPricesAndColors[index][2] = new EditText(this);

                edtNamesPricesAndColors[index][0].setText(martialArtsObject.getMartialArtsName());
                edtNamesPricesAndColors[index][1].setText(martialArtsObject.getMartialArtsPrice() + "");
                edtNamesPricesAndColors[index][1].setInputType(InputType.TYPE_CLASS_NUMBER);
                edtNamesPricesAndColors[index][2].setText(martialArtsObject.getMartialArtsColor());

                edtNamesPricesAndColors[index][0].setId(martialArtsObject.getMartialArtsId() + 10);
                edtNamesPricesAndColors[index][1].setId(martialArtsObject.getMartialArtsId() + 20);
                edtNamesPricesAndColors[index][2].setId(martialArtsObject.getMartialArtsId() + 30);

                modifyButtons[index] = new Button(this);
                modifyButtons[index].setText("MODIFY");
                modifyButtons[index].setId(martialArtsObject.getMartialArtsId());
                modifyButtons[index].setOnClickListener(this);

                gridLayout.addView(idTextViews[index], (int) (screenWidth * 0.05), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamesPricesAndColors[index][0], (int) (screenWidth * 0.28), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamesPricesAndColors[index][1], (int) (screenWidth * 0.20), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamesPricesAndColors[index][2], (int) (screenWidth * 0.20), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(modifyButtons[index], (int) (screenWidth * 0.30), ViewGroup.LayoutParams.WRAP_CONTENT);

                index++;
            }

            scrollView.addView(gridLayout);
            setContentView(scrollView);


        }
    }

    @Override
    public void onClick(View view) {

        int martialArtsObjectId = view.getId();

        EditText edtMartialArtName = findViewById(martialArtsObjectId + 10);
        EditText edtMartialArtPrice = findViewById(martialArtsObjectId + 20);
        EditText edtMartialArtColor = findViewById(martialArtsObjectId + 30);

        String martialArtNameStringValue = edtMartialArtName.getText().toString();
        String martialArtPriceStringValue = edtMartialArtPrice.getText().toString();
        String  martialArtColorStringValue = edtMartialArtColor.getText().toString();

        try {

            double martialArtPriceDoubleValue = Double.parseDouble(martialArtPriceStringValue);

            databaseHandler.modifyMartialArtObject(martialArtsObjectId, martialArtNameStringValue,
                    martialArtPriceDoubleValue, martialArtColorStringValue);

            Toast.makeText(this, "MartialArt Object is updated", Toast.LENGTH_SHORT).show();

        }catch (NumberFormatException e){
            e.printStackTrace();
        }

    }
}