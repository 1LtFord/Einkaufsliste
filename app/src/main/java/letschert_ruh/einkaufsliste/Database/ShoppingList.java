package letschert_ruh.einkaufsliste.Database;

import android.content.ContentValues;
import android.content.Context;
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

    public String GetCheckedTotal(){
        List<ListPosition> positions = this.Positions;
        String CheckedTotal = "0/0";
        if(Positions != null) {
            int checked = 0;
            int total = positions.size();

            for (int i = 0; i < positions.size(); i++) {
                if (positions.get(i).Checked) {
                    checked++;
                }
            }
            CheckedTotal = String.valueOf(checked) + "/" + String.valueOf(total);
        }
        return CheckedTotal;
    }

    public String GetCostLeft(){
        List<ListPosition> positions = this.Positions;
        Double CostLeft = 0.0;

        if(positions != null){
            for(int i = 0; i < positions.size(); i++){
                if(!positions.get(i).Checked){
                    CostLeft += (positions.get(i).Article.Cost * positions.get(i).Amount);
                }

            }
        }
        return GetCurrencyString(CostLeft);
    }

    private String GetCurrencyString(Double Cost){
        String value = String.valueOf(Cost);
        if(value.contains(".")){
            String[] split = value.split("\\.");
            if(split[1].length() == 1){
                value = value + "0";
            }
        }
        else {
            value = value + ".00";
        }
        return value;
    }

    public ShoppingList()
    {

    }

    public ShoppingList(long id)
    {
        this.Id = id;
    }

    public boolean SaveOrUpdate(Context context)
    {
        if (this.Name == null || this.Name.length() <= 2)
        {
            return false;
        }

        SQLiteDatabase db = new SQLiteHandler(context).getWritableDatabase();

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