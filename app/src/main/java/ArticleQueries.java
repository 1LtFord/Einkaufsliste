import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public final class ArticleQueries {
    public Article GetById(long id){
        SQLiteDatabase db = new SQLiteHandler(null).getReadableDatabase();

        Article article = new Article();

        String[] projection = {
                "ID",
                article.GetColumnNameName(),
                article.GetColumnNameMerchant(),
                article.GetColumnNameManufacturer(),
                article.GetColumnNameCost()
        };

        String selection = "ID" + " = ?";
        String[] selectionArgs = { "[" + id + "]" };

        Cursor cursor = db.query(
                article.GetTableName(),              // The table to query
                projection,                          // The columns to return
                selection,                           // The columns for the WHERE clause
                selectionArgs,                       // The values for the WHERE clause
                null,                                // don't group the rows
                null,                                // don't filter by row groups
                null                                 // The sort order
        );

        Article result = null;
        while(cursor.moveToNext()) {
            result = new Article(id);

            result.Name = cursor.getString(
                    cursor.getColumnIndexOrThrow(article.GetColumnNameName()));

            result.Merchant = cursor.getString(
                    cursor.getColumnIndexOrThrow(article.GetColumnNameMerchant()));

            result.Manufacturer = cursor.getString(
                    cursor.getColumnIndexOrThrow(article.GetColumnNameManufacturer()));

            result.Cost = cursor.getDouble(
                    cursor.getColumnIndexOrThrow(article.GetColumnNameCost()));
        }
        cursor.close();

        if (result == null){
            return null;
        }

        return result;
    };
}
