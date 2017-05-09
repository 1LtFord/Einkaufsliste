package letschert_ruh.einkaufsliste.GUI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import org.w3c.dom.Text;

import letschert_ruh.einkaufsliste.GUI.Data.GUI_Data_ShoppingList_Overview;
import letschert_ruh.einkaufsliste.R;

public class Adapter_ShoppingList_Overview extends ArrayAdapter<GUI_Data_ShoppingList_Overview> {

    Context context;
    int layoutResourceId;
    GUI_Data_ShoppingList_Overview data[] = null;

    public Adapter_ShoppingList_Overview(Context context, int layoutResourceId, GUI_Data_ShoppingList_Overview[] data){
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Holder_ShoppingList_Overview holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new Holder_ShoppingList_Overview();
            holder.Name = (TextView)row.findViewById(R.id.txtv_Name);
            holder.CheckedTotal = (TextView)row.findViewById(R.id.txtv_CheckedTotal);
            holder.Total = (TextView)row.findViewById(R.id.txtv_Total);

            row.setTag(holder);
        }
        else{
            holder = (Holder_ShoppingList_Overview)row.getTag();
        }

        GUI_Data_ShoppingList_Overview ov = data[position];
        holder.Name.setText(ov.Name);
        holder.CheckedTotal.setText(ov.CheckedTotal);
        holder.Total.setText(ov.Total);

        return row;
    }

    static class Holder_ShoppingList_Overview{
        TextView Name;
        TextView CheckedTotal;
        TextView Total;
    }
}
