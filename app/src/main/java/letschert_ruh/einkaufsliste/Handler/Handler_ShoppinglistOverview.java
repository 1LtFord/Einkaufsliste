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

            ov.Id = shoppingList.get(i).GetId();
            ov.Name = shoppingList.get(i).Name;
            ov.CheckedTotal = shoppingList.get(i).GetCheckedTotal();
            ov.Total = shoppingList.get(i).GetCostLeft();

            Overview_Data.add(ov);
        }

        return Overview_Data;
    }
}
