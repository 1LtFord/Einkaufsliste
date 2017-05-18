package letschert_ruh.einkaufsliste.Handler;


import android.animation.TypeConverter;
import android.content.Context;
import android.view.ViewDebug;
import android.view.accessibility.AccessibilityManager;

import java.util.ArrayList;
import java.util.List;

import letschert_ruh.einkaufsliste.Database.ListPosition;
import letschert_ruh.einkaufsliste.Database.Queries.ShoppingListQueries;
import letschert_ruh.einkaufsliste.Database.ShoppingList;
import letschert_ruh.einkaufsliste.GUI.Data.GUI_Data_ShoppingList_Overview;

public class Handler_ShoppinglistOverview {

    //TODO TESTEN!


    public List<GUI_Data_ShoppingList_Overview> GetOverviewData(Context context){

        ShoppingListQueries slq = new ShoppingListQueries();
        List<ShoppingList> shoppingList = slq.GetAll(context);

        return Prepare_ShoppingList_Overview_Data(shoppingList);
    }

    private List<GUI_Data_ShoppingList_Overview> Prepare_ShoppingList_Overview_Data(List<ShoppingList> shoppingList){
        List<GUI_Data_ShoppingList_Overview>    Overview_Data   = new ArrayList<GUI_Data_ShoppingList_Overview>();

        for(int i = 0; i < shoppingList.size(); i++){
            GUI_Data_ShoppingList_Overview ov = new GUI_Data_ShoppingList_Overview();

            ov.Name = shoppingList.get(i).Name;
            ov.CheckedTotal = Prepare_Shoppinglist_CheckedTotal(shoppingList.get(i).Positions);
            ov.Total = Prepare_Shoppinglist_Total(shoppingList.get(i).Positions);

            Overview_Data.add(ov);
        }

        return Overview_Data;
    }

    private String Prepare_Shoppinglist_CheckedTotal(List<ListPosition> Positions){
        int Checked = 0;
        int Total = 0;
        if(Positions != null) {
            for (int i = 0; i < Positions.size(); i++) {
                Total++;
                if (Positions.get(i).Checked) {
                    Checked++;
                }
            }
        }
        return (Checked + "/" + Total);
    }

    private String Prepare_Shoppinglist_Total(List<ListPosition> Positions){
        int summ = 0;
        if(Positions != null) {
            for (int i = 0; i < Positions.size(); i++) {
                if (!Positions.get(i).Checked) {
                    summ += Positions.get(i).Article.Cost;
                }
            }
        }
        return GetCurrencyString(String.valueOf(summ));
    }

    private String GetCurrencyString(String summ){
        String c = summ;
        String value = summ;
        if(value.contains(".")){
            String[] split = value.split("\\.");
            if(split[1].length() == 1){
                c = value + "0";
            }
        }
        else {
            c = value + ".00";
        }
        return c;
    }

}
