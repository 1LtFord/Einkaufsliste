import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.lang.Object;

public class ListPositionQueries {
    public List<ListPosition> GetByShoppingList(ShoppingList shoppingList){
        SQLiteDatabase db = new SQLiteHandler(null).getReadableDatabase();

        ListPosition position = new ListPosition();

        String[] projection = {
                "ID",
                position.GetColumnNameArticle(),
                position.GetColumnNameAmount(),
                position.GetColumnNameChecked(),
        };

        String selection = position.GetColumnNameShoppingList() + " = ?";
        String[] selectionArgs = { "[" + shoppingList.GetId() + "]" };

        Cursor cursor = db.query(
                position.GetTableName(),             // The table to query
                projection,                          // The columns to return
                selection,                           // The columns for the WHERE clause
                selectionArgs,                       // The values for the WHERE clause
                null,                                // don't group the rows
                null,                                // don't filter by row groups
                null                                 // The sort order
        );

        List<ListPosition> results = new ArrayList<ListPosition>();

        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow("ID"));

            ListPosition item = new ListPosition(itemId);
            ArticleQueries articleQueries = new ArticleQueries();

            item.Article = articleQueries.GetById(cursor.getLong(
                    cursor.getColumnIndexOrThrow(position.GetColumnNameArticle())));

            item.Amount = cursor.getInt(
                    cursor.getColumnIndexOrThrow(position.GetColumnNameAmount()));

            item.Amount = cursor.getInt(
                    cursor.getColumnIndexOrThrow(position.GetColumnNameAmount()));

            item.Checked = cursor.getInt(
                    cursor.getColumnIndexOrThrow(position.GetColumnNameChecked())) != 1 ? false : true;

            item.ShoppingList = shoppingList;

            results.add(item);
        }
        cursor.close();

        return results;
    };
}
