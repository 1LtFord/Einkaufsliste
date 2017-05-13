package letschert_ruh.einkaufsliste.Database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class ShoppingList {
    public List<ListPosition> Positions;
    public String Name;

    private long Id;
    private String TABLE_NAME = "ShoppingList";
    private String COLUMN_NAME_NAME = "Name";

    public long GetId()
    {
        return this.Id;
    }

    public String GetTableName()
    {
        return this.TABLE_NAME;
    }

    public String GetColumnNameName()
    {
        return this.COLUMN_NAME_NAME;
    }

    public ShoppingList()
    {

    }

    public ShoppingList(long id)
    {
        this.Id = id;
    }

    public boolean SaveOrUpdate()
    {
        if (this.Name == null || this.Name.length() <= 2)
        {
            return false;
        }

        SQLiteDatabase db = new SQLiteHandler(null).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(this.COLUMN_NAME_NAME, this.Name);

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