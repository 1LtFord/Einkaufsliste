package letschert_ruh.einkaufsliste.GUI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import letschert_ruh.einkaufsliste.Database.Article;
import letschert_ruh.einkaufsliste.R;

public class Adapter_Articlelist_Edit extends ArrayAdapter<Article>{
    Context context;
    int layoutRessourceId;
    Article data[] = null;

    public Adapter_Articlelist_Edit(Context context, int layoutRessourceId, Article[] data){
        super(context, layoutRessourceId, data);
        this.context = context;
        this.layoutRessourceId = layoutRessourceId;
        this.data = data;
    }

     @Override
     public View getView(int position, View convertView, ViewGroup parent){
         View row = convertView;
         Holder_Article holder = null;

         if(row == null){
             LayoutInflater inflater = ((Activity)context).getLayoutInflater();
             row = inflater.inflate(layoutRessourceId, parent, false);

             holder = new Holder_Article();
             holder.Name = (TextView)row.findViewById(R.id.tv_Name);
             holder.Merchant = (TextView)row.findViewById(R.id.tv_Merchant);
             holder.Manufacturer = (TextView)row.findViewById(R.id.tv_Manufacturer);
             holder.Cost = (TextView)row.findViewById(R.id.tv_Price);

             row.setTag(holder);
         }
         else {
            holder = (Holder_Article)row.getTag();
         }

         Article ha = data[position];

         holder.Name.setText(ha.Name);
         if(ha.Merchant != null){ holder.Merchant.setText(ha.Merchant);}
         else {holder.Merchant.setText("");}
         if(ha.Manufacturer != null){ holder.Manufacturer.setText(ha.Manufacturer);}
         else {holder.Manufacturer.setText("");}
         holder.Cost.setText(ha.GetCurrencyString());

         return row;
     }

    static class Holder_Article {
        TextView Name;
        TextView Merchant;
        TextView Manufacturer;
        TextView Cost;
    }



}
