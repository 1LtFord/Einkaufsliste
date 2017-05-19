package letschert_ruh.einkaufsliste.GUI.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
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

import letschert_ruh.einkaufsliste.Database.Queries.ShoppingListQueries;
import letschert_ruh.einkaufsliste.Database.ShoppingList;
import letschert_ruh.einkaufsliste.Handler.Handler_ShoppinglistOverview;
import letschert_ruh.einkaufsliste.R;
import letschert_ruh.einkaufsliste.GUI.Adapter.Adapter_ShoppingList_Overview;
import letschert_ruh.einkaufsliste.GUI.Data.GUI_Data_ShoppingList_Overview;

public class Activity_ListOverview extends Activity {

    private ListView listView1;

    private List<GUI_Data_ShoppingList_Overview> lists_overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_overview);

        this.listView1 = (ListView)findViewById(R.id.lv_ListsOverview);

        View header = (View)getLayoutInflater().inflate(R.layout.element_listview_newlist, null);
        listView1.addHeaderView(header);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    ActionCreateNewShoppinglist(parent.getContext());
                }
                else{
                    openShoppinglist(position, parent.getContext());
                }
            }
        });

        GetShoppingLists();
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

    private void GetShoppingLists(){
        Handler_ShoppinglistOverview handler = new Handler_ShoppinglistOverview();

        lists_overview = handler.GetOverviewData(this);
        GUI_Data_ShoppingList_Overview Overview_Data[] = lists_overview.toArray(new GUI_Data_ShoppingList_Overview[lists_overview.size()]);
        Adapter_ShoppingList_Overview adapter = new Adapter_ShoppingList_Overview(this, R.layout.element_listview_shoppinglist_overview, Overview_Data);

        listView1.setAdapter(adapter);
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

    private void ActionCreateNewShoppinglist(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.new_Shoppinglist);

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint(R.string.hint_newShoppinglist);
        builder.setView(input);

        builder.setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ShoppingList list = new ShoppingList();
                list.Name = input.getText().toString();
                list.SaveOrUpdate(context);
                GetShoppingLists();
                //Toast.makeText(getApplicationContext(),input.getText().toString(), Toast.LENGTH_SHORT).show();
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

    private void openShoppinglist(long position, Context context){
        //Todo
        ShoppingListQueries queries = new ShoppingListQueries();

        List<ShoppingList> lists = queries.GetAll(context);

        //Bessere Möglichkeiten erst mit Mindestvoraussetzung API Stufe 25 (Android 7)
        ShoppingList list = lists.get((int)position - 1);
        Intent intent = new Intent(context, Activity_ListView.class);
        intent.putExtra("Id", list.GetId());
        intent.putExtra("Index", (int)position - 1);
        startActivity(intent);
        //Toast.makeText(getApplicationContext(),String.valueOf(position), Toast.LENGTH_SHORT).show();
    }

}
