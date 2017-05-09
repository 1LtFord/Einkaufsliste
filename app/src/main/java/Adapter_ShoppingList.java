import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.einkaufsliste.R;

public class Adapter_ShoppingList extends ArrayAdapter<View_ShoppingList> {

    Context context;
    int layoutResourceId;
    View_ShoppingList data[] = null;

    public Adapter_ShoppingList(Context context, int layoutResourceId, View_ShoppingList[] data){
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ShoppingList_Holder holder = null;

        if (row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ShoppingList_Holder();
            holder.Name = (TextView)row.findViewById(R.id.tw_ListName);
            holder.ElementsChecked = (TextView)row.findViewById(R.id.tw_ListElements_Checked);
            holder.Price = (TextView)row.findViewById(R.id.tw_ListPrice);

            row.setTag(holder);
        }
        else{
            holder = (ShoppingList_Holder)row.getTag();
        }

        View_ShoppingList sl = data[position];
        holder.Name.setText(sl.Name);
        holder.ElementsChecked.setText(sl.ElementsChecked);
        holder.Price.setText(sl.Price);

        return row;
    }

    static class ShoppingList_Holder {
        TextView Name;
        TextView ElementsChecked;
        TextView Price;
    }
}
