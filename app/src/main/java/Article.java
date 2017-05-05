import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Article {
    public String Name;
    public String Merchant;
    public String Manufacturer;
    public double Cost;

    private long Id;
    private String TABLE_NAME = "Article";
    private String COLUMN_NAME_NAME = "Name";
    private String COLUMN_NAME_Merchant = "Merchant";
    private String COLUMN_NAME_Manufacturer = "Manufacturer";
    private String COLUMN_NAME_Cost = "Cost";

    public long GetId(){
        return this.Id;
    }

    public boolean Save()
    {
        if (this.Name == null || this.Name.length() <= 2){
            return false;
        }

        SQLiteDatabase db = new SQLiteHandler(null).getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(this.COLUMN_NAME_NAME, this.Name);
        values.put(this.COLUMN_NAME_Merchant, this.Merchant);
        values.put(this.COLUMN_NAME_Manufacturer, this.Manufacturer);
        values.put(this.COLUMN_NAME_Cost, this.Cost);

        long newRowId = db.insert(this.TABLE_NAME, null, values);
        this.Id = newRowId;

        return true;
    }
}
