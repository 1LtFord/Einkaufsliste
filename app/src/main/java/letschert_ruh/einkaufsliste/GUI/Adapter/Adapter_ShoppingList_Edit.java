package letschert_ruh.einkaufsliste.GUI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import letschert_ruh.einkaufsliste.Database.ListPosition;
import letschert_ruh.einkaufsliste.Database.ShoppingList;
import letschert_ruh.einkaufsliste.GUI.Activities.Activity_ListView;
import letschert_ruh.einkaufsliste.R;

public class Adapter_ShoppingList_Edit extends ArrayAdapter<ListPosition> {

    Context context;
    int layoutRessourceId;
    ListPosition data[] = null;

    public Adapter_ShoppingList_Edit(Context context, int layoutRessourceId, ListPosition[] data){
        super(context, layoutRessourceId, data);
        this.context = context;
        this.layoutRessourceId = layoutRessourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Holder_ShoppingList_Edit holder = null;

        if (row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutRessourceId, parent, false);

            holder = new Holder_ShoppingList_Edit();
            holder.Name = (TextView)row.findViewById(R.id.tv_Name);
            holder.Count = (TextView)row.findViewById(R.id.tv_Count);
            holder.Cost = (TextView)row.findViewById(R.id.tv_Cost);
            holder.ItemChecked = (CheckBox)row.findViewById(R.id.chkbx_ItemChecked);
            holder.Manufacturer = (TextView)row.findViewById(R.id.tv_Manufacturer);
            holder.Merchant = (TextView)row.findViewById(R.id.tv_Merchant);

            row.setTag(holder);
        }
        else{
            holder = (Holder_ShoppingList_Edit)row.getTag();
        }

        final ListPosition p = data[position];
        holder.Name.setText(p.Article.Name);
        holder.Count.setText(String.valueOf(p.Amount) + "x");
        holder.Cost.setText(p.Article.GetCurrencyString());
        holder.ItemChecked.setChecked(p.Checked);
        holder.Manufacturer.setText(p.Article.Manufacturer);
        holder.Merchant.setText(p.Article.Merchant);
        holder.ItemChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox ckbx = (CheckBox)v;
                p.Checked = ckbx.isChecked();
                p.SaveOrUpdate(context);
                Activity_ListView l = (Activity_ListView)context;
                l.RefreschListPositons();
            }
        });

        return row;
    }

    static class Holder_ShoppingList_Edit{
        TextView Name;
        TextView Count;
        TextView Cost;
        CheckBox ItemChecked;
        TextView Manufacturer;
        TextView Merchant;
    }


}
