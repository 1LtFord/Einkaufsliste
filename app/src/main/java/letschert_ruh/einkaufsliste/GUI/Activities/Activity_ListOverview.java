package letschert_ruh.einkaufsliste.GUI.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

        //Nicht benötigt da Startbildschirm
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        //setTitle("Test");


        //TODO Nach Test von Handler aktivieren!
        //Fertige Implementierung:
        //Handler_ShoppinglistOverview handler = new Handler_ShoppinglistOverview();
        //GUI_Data_ShoppingList_Overview Overview_Data[] = handler.GetOverviewData().toArray(new GUI_Data_ShoppingList_Overview[handler.GetOverviewData().size()]);

        //TODO Nach Test von Handler deaktivieren!
        //Test:
        List<GUI_Data_ShoppingList_Overview> testdaten = new ArrayList<GUI_Data_ShoppingList_Overview>();
        for(long i = 0; i < 10; i++){
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
                if(position == 0) {
                    ActionCreateNewShoppinglist();
                }
                else{
                    openShoppinglist(position);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.global, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_EditArticles:
                Action_OpenArticleOverview();
                return true;
            case R.id.action_AddArticle:
                Action_AddArticle();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Action_OpenArticleOverview()
    {
        Intent intent = new Intent(this, Activity_ArticleOverview.class);
        startActivity(intent);
    }

    private void Action_AddArticle()
    {
        Intent intent = new Intent(this, Activity_EditArticle.class);
        startActivity(intent);
    }

    private void ActionCreateNewShoppinglist(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.new_Shoppinglist);

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint(R.string.hint_newShoppinglist);
        builder.setView(input);

        builder.setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Todo
                Toast.makeText(getApplicationContext(),input.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void openShoppinglist(long position){
        //Todo
        Toast.makeText(getApplicationContext(),String.valueOf(position), Toast.LENGTH_SHORT).show();
    }

}
