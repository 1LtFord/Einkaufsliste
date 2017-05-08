import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHandler extends SQLiteOpenHelper {
    // Version & Name
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Einkaufsliste.db";
    public static SQLiteDatabase DB;

    private static Article article = new Article();
    private static ListPosition listPosition = new ListPosition();
    private static ShoppingList shoppingList = new ShoppingList();

    private static final String SQL_CREATE_TABLE_ARTICLE =
            "CREATE TABLE " + article.GetTableName() + " (" +
                    "ID" + " INTEGER PRIMARY KEY," +
                    article.GetColumnNameName() + " TEXT," +
                    article.GetColumnNameMerchant() + " TEXT," +
                    article.GetColumnNameManufacturer() + " TEXT," +
                    article.GetColumnNameCost() + " TEXT)";

    private static final String SQL_CREATE_TABLE_LISTPOSITION =
            "CREATE TABLE " + listPosition.GetTableName() + " (" +
                    "ID" + " INTEGER PRIMARY KEY," +
                    listPosition.GetColumnNameArticle() + " INTEGER," +
                    listPosition.GetColumnNameAmount() + " INTEGER," +
                    listPosition.GetColumnNameArticle() + " INTEGER," +
                    listPosition.GetColumnNameShoppingList() + " INTEGER," +
                    listPosition.GetColumnNameChecked() + " INTEGER)";

    private static final String SQL_CREATE_TABLE_SHOPPINGLIST =
            "CREATE TABLE " + shoppingList.GetTableName() + " (" +
                    "ID" + " INTEGER PRIMARY KEY," +
                    shoppingList.GetColumnNameName() + " TEXT)";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_ARTICLE);
        db.execSQL(SQL_CREATE_TABLE_LISTPOSITION);
        db.execSQL(SQL_CREATE_TABLE_SHOPPINGLIST);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        //db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

   /*
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);

    }
    */
}