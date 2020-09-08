package com.example.martialartsclubsqlite;

import android.content.Context;

import androidx.appcompat.widget.AppCompatButton;

import com.example.martialartsclubsqlite.model.MartialArts;

public class MartialArtButton extends AppCompatButton {

    private MartialArts martialArts;

    public MartialArtButton(Context context, MartialArts martialArts){
        super(context);

        this.martialArts = martialArts;
    }

   public String getMartialArtColor(){
        return  martialArts.getMartialArtsColor();
   }

   public Double getMartialArtPrice(){
        return martialArts.getMartialArtsPrice();
   }
}
