package com.example.rezerviranjeparking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="NovaBaza";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "cities";
    public static final String TABLE_NAME1 = "parking";
    private static final String TABLE_Reservation = "Reservation";
    private static final String RESERVATION_ID = "id";
    private static final String USER_NAME = "user";
    private static final String CITY_NAME = "name";
    private static final String DATE = "date";
    private static final String TIME = "time";
    private static final String PARKING_NAME = "parking_name";
    Context mContext;

    public DBHelper(@Nullable Context context) {
        super(context, "NovaBaza", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(username TEXT primary key,password TEXT  ) ");

        String query;
        query = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY, CityName TEXT)";
        db.execSQL(query);

        db.execSQL("create Table reservations(id INTEGER primary key,user TEXT,city TEXT,date TEXT,time TEXT,parking TEXT,lat FLOAT,lon FLOAT)");

       /* String CREATE_TABLE2 = "CREATE TABLE " + TABLE_Reservation + "("
                + RESERVATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_NAME + " TEXT,"
                + CITY_NAME + " TEXT,"
                + DATE + " TEXT,"
                + TIME + " TEXT,"
                + PARKING_NAME + " TEXT"+")";
        db.execSQL(CREATE_TABLE2);*/

        db.execSQL("create table " + TABLE_NAME1 +"(id INTEGER PRIMARY KEY AUTOINCREMENT, parking_name VARCHAR, city_name VARCHAR, free INTEGER,lat FLOAT,lon FLOAT)");
        ContentValues content = new ContentValues();
        db.execSQL("insert into " + TABLE_NAME1 +"(parking_name, city_name, free, lat, lon)" + "VALUES" + "('Паркинг 1', 'Велес', '10','41.7096937798', '21.760735385')");
        db.execSQL("insert into " + TABLE_NAME1 +"(parking_name, city_name, free, lat, lon)" + "VALUES" + "('Паркинг 2', 'Скопје', '10','41.9991267', '21.4168831')");
        db.execSQL("insert into " + TABLE_NAME1 +"(parking_name, city_name, free, lat, lon)" + "VALUES" + "('Паркинг 3', 'Охрид', '10','41.1175018', '20.7989473')");
        db.execSQL("insert into " + TABLE_NAME1 +"(parking_name, city_name, free, lat, lon)" + "VALUES" + "('Паркинг 4', 'Велес', '17','41.71085', '21.764183')");
        db.execSQL("insert into " + TABLE_NAME1 +"(parking_name, city_name, free, lat, lon)" + "VALUES" + "('Паркинг 5', 'Кавадарци', '9','41.4338368', '22.014034')");
        db.execSQL("insert into " + TABLE_NAME1 +"(parking_name, city_name, free, lat, lon)" + "VALUES" + "('Паркинг 6', 'Велес', '25','41.71576', '21.78348')");
        db.execSQL("insert into " + TABLE_NAME1 +"(parking_name, city_name, free, lat, lon)" + "VALUES" + "('Паркинг 7', 'Скопје', '15','42.0012037', '21.3899403')");


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
            Float lat=cursor.getFloat(4);
            Float lon=cursor.getFloat(5);
            Parking parking =
                    new Parking(parkingId, parkingName, city, free, lat,lon);
            returnList.add(parking);
        }
        cursor.close();
        db.close();

        return returnList;
    }
     /*  public Boolean NamaliMesta(String parking_name)
    { SQLiteDatabase db = this.getReadableDatabase();
        try {
        onCreate(db);
    } catch (Exception e) {
        e.printStackTrace();

    } Cursor cursor=null;
        String sql = String.format("SELECT id FROM %s WHERE parking_name = parking_name", TABLE_NAME1);
        int free = 0,taken=0;

        cursor = db.rawQuery(sql,  new String[]{parking_name});
        if(cursor.moveToFirst()) {
            free = cursor.getInt(3);
            taken = cursor.getInt(4);
            free--;
            taken++;
        }
        cursor.close();
        ContentValues cv = new ContentValues();
        cv.put("free", free);
        cv.put("taken",taken);
        long proba=db.update(TABLE_NAME1,cv,free+"=?",new String[]{parking_name});
        db.update(TABLE_NAME1,cv,taken+"=?",new String[]{parking_name});
        if(proba==-1) return false;
        else  return true;
    }*/
    public boolean isEmpty(String TableName){

        SQLiteDatabase database = this.getReadableDatabase();
        long NoOfRows = DatabaseUtils.queryNumEntries(database,TableName);

        if (NoOfRows == 0){
            return true;
        } else {
            return false;
        }
    }
    public Boolean insertReservation(String user, String city, String date, String time, String parking){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor proverka= db.rawQuery("Select * from reservations where user=? and city=? and date=? and time=? and parking=?", new String[]{user,city,date,time,parking});
        Cursor count= db.rawQuery("Select * from reservations where user=?", new String[]{user});
        if(proverka.getCount()>0)
        {return false;}
        else{
        if(count.getCount()>2) {
            return false;
        }
        else{
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", user);
        contentValues.put("city", city);
        contentValues.put("date", date);
       contentValues.put("time", time);
       contentValues.put("parking", parking);
       if(parking.equals("Паркинг 1")){
           contentValues.put("lat",41.7096937798);
       contentValues.put("lon",21.760735385);}
       else if(parking.equals("Паркинг 2")){
           contentValues.put("lat",41.9991267);
           contentValues.put("lon",21.4168831);}
       else if(parking.equals("Паркинг 3")){
           contentValues.put("lat",41.1175018);
           contentValues.put("lon",20.7989473);}
       else if(parking.equals("Паркинг 4")){
           contentValues.put("lat",41.71085);
           contentValues.put("lon",21.764183);}
       else if(parking.equals("Паркинг 5")){
           contentValues.put("lat",41.4338368);
           contentValues.put("lon",22.014034);}
       else if(parking.equals("Паркинг 6")){
           contentValues.put("lat",41.71576);
           contentValues.put("lon",21.78348);}
       else {
           contentValues.put("lat",42.0012037);
           contentValues.put("lon",21.3899403);}
        long newRowId = db.insert("reservations", null, contentValues);
        if(newRowId==-1) return false;
        else  return true;}
        }
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
    public List<Rezervacii> getAllReservations(String username) {

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            onCreate(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Rezervacii> returnList = new ArrayList<>();

        String query = "SELECT * FROM reservations WHERE user =?";

        Cursor cursor = db.rawQuery(query,  new String[]{username});

        while(cursor.moveToNext()) {
            int rezervacijaId = cursor.getInt(0);
            String parkingName = cursor.getString(5);
            String cityName=cursor.getString(2);
            String date=cursor.getString(3);
            String time =cursor.getString(4);
            Float lat=cursor.getFloat(6);
            Float lon=cursor.getFloat(7);

            Rezervacii rezervacii =
                    new Rezervacii(rezervacijaId, username, parkingName, cityName, date,time,lat,lon);
            returnList.add(rezervacii);
        }
        cursor.close();
        db.close();

        return returnList;
    }
    public int getTotalSpaces(String parking_name) {
        int total;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c1 = database.rawQuery("SELECT * FROM parking WHERE parking_name =?", new String[]{parking_name});
        if(c1.moveToFirst()) {
            total = c1.getInt(3);
            c1.close();
            return total;
        } else {
            return 0;
        }
    }
    public int getNumberOfReservations(String date, String time, String parking) {
        int count = 0;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c1 = database.rawQuery("SELECT * FROM reservations WHERE date=? and time=? and parking=?" , new String []{date,time,parking});
        if(c1.moveToFirst()) {
            count = c1.getCount();
            c1.close();
            return count;
        } else {
            return 0;
        }
    }
}
