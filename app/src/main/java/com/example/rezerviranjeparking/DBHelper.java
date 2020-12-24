package com.example.rezerviranjeparking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="B11";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "cities";
    public static final String TABLE_NAME1 = "parking";
    private static final String TABLE_Reservation = "Reservation";
    private static final String RESERVATION_ID = "id";
    private static final String USER_NAME = "username";
    private static final String CITY_NAME = "name";
    private static final String DATE = "date";
    private static final String TIME = "time";
    private static final String PARKING_NAME = "parking_name";
    Context mContext;

    public DBHelper(@Nullable Context context) {
        super(context, "B11", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(username TEXT primary key,password TEXT  ) ");

        String query;
        query = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY, CityName TEXT)";
        db.execSQL(query);

        String CREATE_TABLE2 = "CREATE TABLE " + TABLE_Reservation + "("
                + RESERVATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_NAME + " TEXT,"
                + CITY_NAME + " TEXT,"
                + DATE + " TEXT,"
                + TIME + " TEXT,"
                + PARKING_NAME + " TEXT"+")";
        db.execSQL(CREATE_TABLE2);

        db.execSQL("create table " + TABLE_NAME1 +"(id INTEGER PRIMARY KEY AUTOINCREMENT, parking_name VARCHAR, city_name VARCHAR, free VARCHAR, taken INTEGER)");
        ContentValues content = new ContentValues();
        db.execSQL("insert into " + TABLE_NAME1 +"(parking_name, city_name, free, taken)" + "VALUES" + "('Паркинг 1', 'Велес', '5','5')");
        db.execSQL("insert into " + TABLE_NAME1 +"(parking_name, city_name, free, taken)" + "VALUES" + "('Паркинг 2', 'Скопје', '3','7')");
        db.execSQL("insert into " + TABLE_NAME1 +"(parking_name, city_name, free, taken)" + "VALUES" + "('Паркинг 3', 'Охрид', '9','1')");
        db.execSQL("insert into " + TABLE_NAME1 +"(parking_name, city_name, free, taken)" + "VALUES" + "('Паркинг 4', 'Велес', '1','9')");
        db.execSQL("insert into " + TABLE_NAME1 +"(parking_name, city_name, free, taken)" + "VALUES" + "('Паркинг 5', 'Кавадарци', '7','3')");
        db.execSQL("insert into " + TABLE_NAME1 +"(parking_name, city_name, free, taken)" + "VALUES" + "('Паркинг 6', 'Велес', '9','21')");
        db.execSQL("insert into " + TABLE_NAME1 +"(parking_name, city_name, free, taken)" + "VALUES" + "('Паркинг 7', 'Скопје', '12','3')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users ");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_Reservation);
        onCreate(db);

    }
    public Boolean insertData(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result=db.insert("users",null,contentValues);
        if(result==-1) return false;
        else  return true;
    }
    public Boolean checkusername(String username)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from users where username=?",new String[]{username});
        if(cursor.getCount()>0) return true;
        else return false;
    }
    public Boolean checkusernamepassword(String username,String password)
    { SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from users where username=? and password=?",new String[]{username,password});
        if(cursor.getCount()>0) return true;
        else return false;

    }
    public void addCities(String CityName) {
        SQLiteDatabase sqLiteDatabase = this .getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CityName", CityName);
        sqLiteDatabase.insert(TABLE_NAME, null , values);
        sqLiteDatabase.close();
    }

    public void addParking(String ParkingName,String i,String ii) {
        SQLiteDatabase sqLiteDatabase = this .getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ParkingName", ParkingName);
        values.put("Zafateno",i);
        values.put("Slobodno",ii);
        sqLiteDatabase.insert(TABLE_NAME1, null , values);
        sqLiteDatabase.close();
    }
    public ArrayList<City> getCities() {
        ArrayList<City> arrayList = new ArrayList<>();
        String select_query= "SELECT *FROM " + TABLE_NAME;
        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);
        if (cursor.moveToFirst()) {
            do {
                City cityModel = new City();
                cityModel.setID(cursor.getString(0));
                cityModel.setCityName(cursor.getString(1));
                arrayList.add(cityModel);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }
    public List<Parking> getAllParkingPlaces(String city) {

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            onCreate(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Parking> returnList = new ArrayList<>();

        String query = "SELECT * FROM "+ TABLE_NAME1 + " WHERE city_name=?";

        Cursor cursor = db.rawQuery(query,  new String[]{city});

        while(cursor.moveToNext()) {
            int parkingId = cursor.getInt(0);
            String parkingName = cursor.getString(1);
            String free=cursor.getString(3);
            String taken=cursor.getString(4);

            Parking parking =
                    new Parking(parkingId, parkingName, city, free, taken);
            returnList.add(parking);
        }
        cursor.close();
        db.close();

        return returnList;
    }
    public boolean isEmpty(String TableName){

        SQLiteDatabase database = this.getReadableDatabase();
        long NoOfRows = DatabaseUtils.queryNumEntries(database,TableName);

        if (NoOfRows == 0){
            return true;
        } else {
            return false;
        }
    }
    void insertReservationDetails(String username, String city_name, String date, String time, String parking_name){

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues cValues = new ContentValues();
        cValues.put(USER_NAME, username);
        cValues.put(CITY_NAME, city_name);
        cValues.put(DATE, date);
        cValues.put(TIME, time);
        cValues.put(PARKING_NAME, parking_name);

        long newRowId = db.insert(TABLE_Reservation, null, cValues);
        db.close();
    }
    public int getParkingID(String city)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=null;
        String sql = String.format("SELECT id FROM %s WHERE city_name = city", TABLE_NAME1);

        cursor = db.rawQuery(sql,  new String[]{ city});
        int ParkingId = -1;
        if(cursor.moveToFirst()) {
            ParkingId = cursor.getInt(0);
        }
        cursor.close();
        db.close();

        return ParkingId;
    }
}
