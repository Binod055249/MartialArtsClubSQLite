package com.example.martialartsclubsqlite.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MartialArtsDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String MARTIAL_ARTS_TABLE = "MartialArts";
    private static final String ID_KEY = "id";
    private static final String NAME_KEY = "name";
    private static final String PRICE_KEY = "price";
    private static final String COLOR_KEY = "color";

    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String createDatabaseSQL="create table " + MARTIAL_ARTS_TABLE +
                "( " + ID_KEY + " integer primary key autoincrement" +
                ", " + NAME_KEY + " text" + ", " + PRICE_KEY + " real" +
                ", " + COLOR_KEY + " text" +" )";

        db.execSQL(createDatabaseSQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists "+MARTIAL_ARTS_TABLE);
        onCreate(db);

    }

    public void addMartialArts(MartialArts martialArtsObject){

        SQLiteDatabase database = getWritableDatabase();
        String addMartialArtSQLCommand = "insert into " + MARTIAL_ARTS_TABLE +
                " values(null, '" + martialArtsObject.getMartialArtsName() +
                "', '" + martialArtsObject.getMartialArtsPrice() + "', '" +
                martialArtsObject.getMartialArtsColor() + "')";
        database.execSQL(addMartialArtSQLCommand);
        database.close();

    }

    public void deletMartialArtObjectFromDatabaseById(int id){

        SQLiteDatabase database = getWritableDatabase();
        String deleteMartialArtsCommand = "delete from " + MARTIAL_ARTS_TABLE +
                               " where " + ID_KEY + " = " + id;
        database.execSQL(deleteMartialArtsCommand);
        database.close();

    }

    public void modifyMartialArtObject(int martialArtId, String martialArtName,
                                       double martialArtPrice, String martialArtColor){

        SQLiteDatabase database = getWritableDatabase();
        String modifyMartialArtSQLCommand = "update " + MARTIAL_ARTS_TABLE +
                           " set " + NAME_KEY + " = '" + martialArtName + "', " +
                          PRICE_KEY + " = '" + martialArtPrice + "', " +
                              COLOR_KEY + " = '" + martialArtColor + "' " +
                           "where " + ID_KEY + " = " + martialArtId;
        database.execSQL(modifyMartialArtSQLCommand);
       database.close();


    }

    public ArrayList<MartialArts> returnAllMaritalArtsObject(){

        SQLiteDatabase database = getWritableDatabase();
        String sqlQueryCommand = "select * from " + MARTIAL_ARTS_TABLE;
        Cursor cursor = database.rawQuery(sqlQueryCommand, null);

        ArrayList<MartialArts> martialArts = new ArrayList<>();

        while(cursor.moveToNext()){

            MartialArts currentMartialArtsObject = new MartialArts(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getDouble(2),
                    cursor.getString(3));

            martialArts.add(currentMartialArtsObject);

        }

        database.close();
        return martialArts;

    }

    public MartialArts returnMartialArtObjectById(int id){

        SQLiteDatabase database = getWritableDatabase();
        String sqlQueryCommand = "select * from " + MARTIAL_ARTS_TABLE + " where " + ID_KEY +
                " = " + id;
        Cursor cursor = database.rawQuery(sqlQueryCommand, null);

        MartialArts martialArtsObject = null;

        if(cursor.moveToFirst()){

            martialArtsObject = new MartialArts(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getDouble(2),
                    cursor.getString(3));

        }

        database.close();
        return martialArtsObject;
    }

}
