import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListQueries {
    public List<ShoppingList> GetAll(){
        SQLiteDatabase db = new SQLiteHandler(null).getReadableDatabase();

        ShoppingList list = new ShoppingList();

        String[] projection = {
                "ID",
                list.GetColumnNameName()
        };

        Cursor cursor = db.query(
                list.GetTableName(),        // The table to query
                projection,                 // The columns to return
                null,                       // The columns for the WHERE clause
                null,                       // The values for the WHERE clause
                null,                       // don't group the rows
                null,                       // don't filter by row groups
                null                         // The sort order
        );

        List<ShoppingList> results = new ArrayList<ShoppingList>();

        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow("ID"));

            ShoppingList item = new ShoppingList(itemId);

            item.Name =  cursor.getString(
                    cursor.getColumnIndexOrThrow(list.GetColumnNameName()));

            results.add(item);
        }
        cursor.close();

        return results;
    };
}
