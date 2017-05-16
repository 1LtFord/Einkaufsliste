package letschert_ruh.einkaufsliste.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Article {
    public String Name;
    public String Merchant;
    public String Manufacturer;
    public double Cost;

    private Context context;
    private long Id = 0;
    private String TABLE_NAME = "Article";
    private String COLUMN_NAME_NAME = "Name";
    private String COLUMN_NAME_MERCHANT = "Merchant";
    private String COLUMN_NAME_MANUFACTURER = "Manufacturer";
    private String COLUMN_NAME_COST = "Cost";

    public long GetId(){
        return this.Id;
    }

    public String GetTableName(){
        return this.TABLE_NAME;
    }

    public String GetColumnNameName(){
        return this.COLUMN_NAME_NAME;
    }

    public String GetColumnNameMerchant(){
        return this.COLUMN_NAME_MANUFACTURER;
    }

    public String GetColumnNameManufacturer(){
        return this.COLUMN_NAME_MERCHANT;
    }

    public String GetColumnNameCost(){
        return this.COLUMN_NAME_COST;
    }

    public Article()
    {

    }

    public Article(long id)
    {
        this.Id = id;
    }

    public Article(Context context){this.context = context;}

    public Article(long id, Context context){
        this.Id = id;
        this.context = context;
    }

    public boolean SaveOrUpdate()
    {
        if (this.Name == null || this.Name.length() <= 2){
            return false;
        }

        SQLiteDatabase db = new SQLiteHandler(context).getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(this.COLUMN_NAME_NAME, this.Name);
        values.put(this.COLUMN_NAME_MERCHANT, this.Merchant);
        values.put(this.COLUMN_NAME_MANUFACTURER, this.Manufacturer);
        values.put(this.COLUMN_NAME_COST, this.Cost);

        if(this.Id == 0){
            long newRowId = db.insert(this.TABLE_NAME, null, values);
            this.Id = newRowId;
        }else{
            String selection = "_ID" + " = ?";
            String[] selectionArgs = { "[" + this.Id + "]" };

            db.update(
               this.TABLE_NAME,
               values,
               selection,
               selectionArgs);
        }

        return true;
    }
}