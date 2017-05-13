package letschert_ruh.einkaufsliste.Database.Queries;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import letschert_ruh.einkaufsliste.Database.Article;
import letschert_ruh.einkaufsliste.Database.SQLiteHandler;

public class ArticleQueries {
    public Article GetById(long id){
        SQLiteDatabase db = new SQLiteHandler(null).getReadableDatabase();

        Article article = new Article();

        String[] projection = {
                "_ID",
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

    public List<Article> GetByNameOrManufacturer(String[] searchStrings){
        if (searchStrings == null ||searchStrings.length == 0){
            return null;
        }

        SQLiteDatabase db = new SQLiteHandler(null).getReadableDatabase();

        Article article = new Article();

        String[] projection = {
                "_ID",
                article.GetColumnNameName(),
                article.GetColumnNameMerchant(),
                article.GetColumnNameManufacturer(),
                article.GetColumnNameCost()
        };

        String[] doubledSearchStrings = new String[searchStrings.length * 2];
        for(int i = 0; i <= searchStrings.length; i++)
        {
            doubledSearchStrings[i] = searchStrings[i];
            doubledSearchStrings[i + 1] = searchStrings[i];
        }

        String selection = article.GetColumnNameName() + "match ? ";
        selection = "or" + article.GetColumnNameManufacturer() + "match ? ";
        for (int i = 1; i <= searchStrings.length; i++)
        {
            selection += "or" + article.GetColumnNameName() + "match ?";
            selection += "or" + article.GetColumnNameManufacturer() + "match ?";
        }

        Cursor cursor = db.rawQuery("Select * from " + article.GetTableName() + "where " + selection, doubledSearchStrings);

        List<Article> result = new ArrayList<Article>();
        Article resultItem = null;
        while(cursor.moveToNext()) {
            resultItem = new Article();

            resultItem = new Article(cursor.getLong(
                    cursor.getColumnIndexOrThrow("_ID")));

            resultItem.Name = cursor.getString(
                    cursor.getColumnIndexOrThrow(article.GetColumnNameName()));

            resultItem.Merchant = cursor.getString(
                    cursor.getColumnIndexOrThrow(article.GetColumnNameMerchant()));

            resultItem.Manufacturer = cursor.getString(
                    cursor.getColumnIndexOrThrow(article.GetColumnNameManufacturer()));

            resultItem.Cost = cursor.getDouble(
                    cursor.getColumnIndexOrThrow(article.GetColumnNameCost()));

            result.add(resultItem);
        }
        cursor.close();

        if (result == null){
            return null;
        }

        return result;
    };

}
