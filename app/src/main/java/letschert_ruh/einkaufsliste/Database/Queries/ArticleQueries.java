package letschert_ruh.einkaufsliste.Database.Queries;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import letschert_ruh.einkaufsliste.Database.Article;
import letschert_ruh.einkaufsliste.Database.SQLiteHandler;

public class ArticleQueries {
    public Article GetById(long id, Context context){
        SQLiteDatabase db = new SQLiteHandler(context).getReadableDatabase();

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

    public List<Article> GetByNameOrManufacturer(String[] searchStrings, Context context){
        if (searchStrings == null ||searchStrings.length == 0){
            return null;
        }

        SQLiteDatabase db = new SQLiteHandler(context).getReadableDatabase();

        Article article = new Article();

        String[] projection = {
                "_ID",
                article.GetColumnNameName(),
                article.GetColumnNameMerchant(),
                article.GetColumnNameManufacturer(),
                article.GetColumnNameCost()
        };

        String selection = article.GetColumnNameName() + " like " + "'%" +  searchStrings[0] + "%'";
        selection += " or " + article.GetColumnNameManufacturer() + " like " + "'%" + searchStrings[0] + "%'";
        for (int i = 1; i < searchStrings.length; i++)
        {
            selection += " or " + article.GetColumnNameName() + " like " + "'%" + searchStrings[i] + "%'";
            selection += " or " + article.GetColumnNameManufacturer() + " like " + "'%"  + searchStrings[i] + "%'";
        }

        String query = "Select * from " + article.GetTableName() + " where " + selection + " ;";
        Cursor cursor = db.rawQuery(query, null);
        //Cursor cursor = db.query(article.GetTableName(),projection,selection,doubledSearchStrings,null,null,null);

        List<Article> result = new ArrayList<Article>();
        Article resultItem = null;
        if (cursor.moveToFirst()) {
            do {

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
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        if (result == null){
            return null;
        }

        return result;
    };

}
