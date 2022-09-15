package fpt.aptech.eatneatapp.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import fpt.aptech.eatneatapp.entities.Item;


public class CartDAO extends SQLiteOpenHelper {

    public CartDAO(Context context){
        super(context,"MyCart.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table tbCart(foodid int primary key, foodname String,price int,image String,quantity int)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql="drop table if exists tbCart";
        sqLiteDatabase.execSQL(sql);
    }
    //create
    public void addCart(Item item){
        SQLiteDatabase db=getWritableDatabase();
        String sql="insert into tbCart(foodid,foodname,price,image,quantity) values(?,?,?,?,?)";
        db.execSQL(sql,new String[]{
                String.valueOf(item.getFoodid()),
                item.getFoodname(),
                String.valueOf(item.getPrice()),
                item.getImage(),
                String.valueOf(item.getQuantity())
        });
    }
    public void clear(Item item){
        SQLiteDatabase db=getWritableDatabase();
        db.delete("tbCart","foodid=?",new String[]{
                String.valueOf(item.getFoodid()),

        });
        db.close();
    }
    public void clearAll(){
        SQLiteDatabase db=getWritableDatabase();
        String sql="delete from tbCart";
        db.execSQL(sql);
        db.close();
    }
    public  void updateQuantity(Item item){
        SQLiteDatabase db=getWritableDatabase();
        String sql="update tbCart set quantity=? where foodid=?";
        db.execSQL(sql,new String[]{
                String.valueOf(item.getQuantity()),
                String.valueOf(item.getFoodid())
        });
    }
    public List<Item> findAll(){
        List<Item> list=new ArrayList<>();
        String sql="select * from tbCart";
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                Item item=new Item();
                item.setFoodid(cursor.getInt(0));
                item.setFoodname(cursor.getString(1));
                item.setPrice(cursor.getInt(2));
                item.setImage(cursor.getString(3));
                item.setQuantity(cursor.getInt(4));
                list.add(item);
            }while (cursor.moveToNext());
        }
        return list;
    }
    public Item findOne(int foodid){
        for (Item item:findAll()){
            if (item.getFoodid()==foodid){
                return item;
            }
        }
        return null;
    }

}
