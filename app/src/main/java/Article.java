import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Article {
    public void Save()
    {
        DbHandler.SaveArticle();
    }
}
