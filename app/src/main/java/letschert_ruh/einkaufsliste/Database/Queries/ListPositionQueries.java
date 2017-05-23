package letschert_ruh.einkaufsliste.Database.Queries;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import letschert_ruh.einkaufsliste.Database.ListPosition;
import letschert_ruh.einkaufsliste.Database.SQLiteHandler;
import letschert_ruh.einkaufsliste.Database.ShoppingList;

public class ListPositionQueries {
    public List<ListPosition> GetByShoppingList(ShoppingList shoppingList, Context context){
        SQLiteDatabase db = new SQLiteHandler(context).getReadableDatabase();

        ListPosition position = new ListPosition();

        String selection = position.GetColumnNameShoppingList() + " = " + shoppingList.GetId();
        String query = "Select * from " + position.GetTableName() + " where " + selection + " ;";

        Cursor cursor = db.rawQuery(query, null);

        //cursor = db.rawQuery("SELECT * FROM " + position.GetTableName() + ";", null);

        List<ListPosition> results = new ArrayList<ListPosition>();

        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow("_ID"));

            ListPosition item = new ListPosition(itemId);
            ArticleQueries articleQueries = new ArticleQueries();

            item.Article = articleQueries.GetById(cursor.getLong(
                    cursor.getColumnIndexOrThrow(position.GetColumnNameArticle())), context);

            item.Amount = cursor.getInt(
                    cursor.getColumnIndexOrThrow(position.GetColumnNameAmount()));

            //item.Amount = cursor.getInt(
            //        cursor.getColumnIndexOrThrow(position.GetColumnNameAmount()));

            int i = cursor.getInt(
                    cursor.getColumnIndexOrThrow(position.GetColumnNameChecked()));

            item.Checked = cursor.getInt(
                    cursor.getColumnIndexOrThrow(position.GetColumnNameChecked())) > 0;

            item.ShoppingList = shoppingList;

            results.add(item);
        }
        cursor.close();

        return results;
    };
}

