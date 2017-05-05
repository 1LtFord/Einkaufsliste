import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class ListPosition {
    public Article Article;
    public int Amount;
    public boolean Checked;


    private long Id = 0;
    private String TABLE_NAME = "ListPosition";
    private String COLUMN_NAME_ARTICLE = "ArticleId";
    private String COLUMN_NAME_AMOUNT = "Amount";
    private String COLUMN_NAME_CHECKED = "Checked";

    public long GetId(){
        return this.Id;
    }

    public boolean Save()
    {
        if (this.Article == null || this.Article.GetId() == 0){
            return false;
        }

        SQLiteDatabase db = new SQLiteHandler(null).getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(this.COLUMN_NAME_ARTICLE, this.Article.GetId());
        values.put(this.COLUMN_NAME_AMOUNT , this.Amount);
        values.put(this.COLUMN_NAME_CHECKED, this.Checked);

        long newRowId = db.insert(this.TABLE_NAME, null, values);

        return true;
    }


}
