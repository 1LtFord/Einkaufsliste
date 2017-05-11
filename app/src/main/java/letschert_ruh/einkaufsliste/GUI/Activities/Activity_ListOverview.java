package letschert_ruh.einkaufsliste.GUI.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import letschert_ruh.einkaufsliste.R;
import letschert_ruh.einkaufsliste.GUI.Adapter.Adapter_ShoppingList_Overview;
import letschert_ruh.einkaufsliste.GUI.Data.GUI_Data_ShoppingList_Overview;

public class Activity_ListOverview extends Activity {

    private ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_overview);

        //TODO Nach Test von Handler aktivieren!
        //Fertige Implementierung:
        //Handler_ShoppinglistOverview handler = new Handler_ShoppinglistOverview();
        //GUI_Data_ShoppingList_Overview Overview_Data[] = handler.GetOverviewData().toArray(new GUI_Data_ShoppingList_Overview[handler.GetOverviewData().size()]);

        //TODO Nach Test von Handler deaktivieren!
        //Test:
        List<GUI_Data_ShoppingList_Overview> testdaten = new ArrayList<GUI_Data_ShoppingList_Overview>();
        for(int i = 0; i < 10; i++){
            String name = ("Einkaufsliste " + i);
            String CheckedTotal = ((i+10) + "/" + (i + 20));
            String Total = ((i * 100) + ",90");
            testdaten.add(new GUI_Data_ShoppingList_Overview(name,CheckedTotal,Total));
        }
        GUI_Data_ShoppingList_Overview Overview_Data[] = testdaten.toArray(new GUI_Data_ShoppingList_Overview[testdaten.size()]);
        //----------------------------------------






        Adapter_ShoppingList_Overview adapter = new Adapter_ShoppingList_Overview(this, R.layout.element_listview_shoppinglist_overview, Overview_Data);

        listView1 = (ListView)findViewById(R.id.lv_ListsOverview);

        View header = (View)getLayoutInflater().inflate(R.layout.element_listview_newlist, null);
        listView1.addHeaderView(header);

        listView1.setAdapter(adapter);


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO
            }
        });
    }


}
