package letschert_ruh.einkaufsliste.Handler;


import android.animation.TypeConverter;
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


    public List<GUI_Data_ShoppingList_Overview> GetOverviewData(){

        ShoppingListQueries slq = new ShoppingListQueries();
        List<ShoppingList> shoppingList = slq.GetAll();

        return Prepare_ShoppingList_Overview_Data(shoppingList);
    }

    private List<GUI_Data_ShoppingList_Overview> Prepare_ShoppingList_Overview_Data(List<ShoppingList> shoppingList){
        List<GUI_Data_ShoppingList_Overview>    Overview_Data   = new ArrayList<GUI_Data_ShoppingList_Overview>();

        for(int i = 0; i < shoppingList.size(); i++){
            Overview_Data.add(new GUI_Data_ShoppingList_Overview(shoppingList.get(i).Name,
                                                                 Prepare_Shoppinglist_CheckedTotal(shoppingList.get(i).Positions),
                                                                 Prepare_Shoppinglist_Total(shoppingList.get(i).Positions)));
        }

        return Overview_Data;
    }

    private String Prepare_Shoppinglist_CheckedTotal(List<ListPosition> Positions){
        int Checked = 0;
        int Total = 0;

        for(int i = 0; i < Positions.size(); i++){
            Total++;
            if (Positions.get(i).Checked){
                Checked++;
            }
        }
        return (Checked + "/" + Total);
    }

    private String Prepare_Shoppinglist_Total(List<ListPosition> Positions){
        int summ = 0;

        for(int i = 0; i < Positions.size(); i++){
            if(!Positions.get(i).Checked) {
                summ += Positions.get(i).Article.Cost;
            }
        }
        return String.valueOf(summ);
    }

}
