package letschert_ruh.einkaufsliste.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ListPosition {
    public Article Article;
    public int Amount;
    public boolean Checked;
    public ShoppingList ShoppingList;

    private long Id = 0;
    private String TABLE_NAME = "ListPosition";
    private String COLUMN_NAME_ARTICLE = "ArticleId";
    private String COLUMN_NAME_AMOUNT = "Amount";
    private String COLUMN_NAME_CHECKED = "Checked";
    private String COLUMN_NAME_SHOPPINGLIST = "ShoppingList";

    public long GetId(){
        return this.Id;
    }

    public String GetTableName()
    {
        return this.TABLE_NAME;
    }

    public String GetColumnNameArticle()
    {
        return this.COLUMN_NAME_ARTICLE;
    }

    public String GetColumnNameAmount()
    {
        return this.COLUMN_NAME_AMOUNT;
    }

    public String GetColumnNameChecked()
    {
        return this.COLUMN_NAME_CHECKED;
    }

    public String GetColumnNameShoppingList()
    {
        return this.COLUMN_NAME_SHOPPINGLIST;
    }

    public ListPosition()
    {

    }

    public ListPosition(long id)
    {
        this.Id = id;
    }

    public boolean SaveOrUpdate(Context context)
    {
        if (this.Article == null || this.Article.GetId() == 0){
            return false;
        }

        if (this.ShoppingList == null || this.ShoppingList.GetId() <= 0)
        {
            return false;
        }

        SQLiteDatabase db = new SQLiteHandler(context).getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(this.COLUMN_NAME_ARTICLE, this.Article.GetId());
        values.put(this.COLUMN_NAME_AMOUNT , this.Amount);
        values.put(this.COLUMN_NAME_CHECKED, this.Checked);
        values.put(this.COLUMN_NAME_SHOPPINGLIST, this.ShoppingList.GetId());

        if(this.Id == 0){
            long newRowId = db.insert(this.TABLE_NAME, null, values);
            this.Id = newRowId;
        }else{
            String selection = "_ID" + " = ?";
            String[] selectionArgs = { String.valueOf(this.Id) };


            db.update(
                this.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        }
        return true;
    }


}