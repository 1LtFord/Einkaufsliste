package letschert_ruh.einkaufsliste.GUI.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.List;

import letschert_ruh.einkaufsliste.Database.ListPosition;
import letschert_ruh.einkaufsliste.Database.Queries.ListPositionQueries;
import letschert_ruh.einkaufsliste.Database.Queries.ShoppingListQueries;
import letschert_ruh.einkaufsliste.Database.ShoppingList;
import letschert_ruh.einkaufsliste.GUI.Adapter.Adapter_ShoppingList_Edit;
import letschert_ruh.einkaufsliste.R;

public class Activity_ListView extends Activity {

    private ShoppingList list;
    private List<ListPosition> positions;
    private ListView ListView1;
    private long ListId;
    private int ListIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        Intent intent = getIntent();
        this.ListId = intent.getLongExtra("Id", Long.MAX_VALUE);
        this.ListIndex = intent.getIntExtra("Index", -1);

        this.ListView1 = (ListView)findViewById(R.id.lv_Shopping_List);

        if(this.ListId == Long.MAX_VALUE || this.ListIndex == -1){
            //// TODO Fehlerbehandlung wenn kein Extra übergeben wurde
        }

        this.list = getShoppingList(this.ListId, this.ListIndex);
        this.setTitle(this.list.Name);
        if(this.list != null){
            ListPositionQueries query = new ListPositionQueries();
            this.positions = query.GetByShoppingList(this.list, this);
        }
        FillView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.shoppinglist_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_AddArticle:
                Action_AddArticle();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Action_AddArticle(){
        Intent intent = new Intent(this, Activity_ArticleSelect.class);
        intent.putExtra("Mode", "Select");
        intent.putExtra("Id", this.ListId);
        intent.putExtra("Index", this.ListIndex);
        startActivity(intent);
    }

    private ShoppingList getShoppingList(long id, int index){
        ShoppingListQueries queries = new ShoppingListQueries();

        List<ShoppingList> lists = queries.GetAll(this);
        ShoppingList list = lists.get(index);

        if(id != list.GetId()){
            int i = 0;
            while(id != list.GetId() && i < lists.size()){
                list = lists.get(i);
                i++;
            }
            if(id != list.GetId()){
                list = null;
            }
        }
        return list;
    }

    private void FillView() {
        if (this.list != null) {

            TextView tv = (TextView) findViewById(R.id.tv_CheckedTotal);
            tv.setText(this.list.GetCheckedTotal());

            tv = (TextView) findViewById(R.id.tv_CostLeft);
            tv.setText(this.list.GetCostLeft());

            if (this.positions != null) {
                FillListView();
            }
        }
    }

    private void FillListView(){
        Adapter_ShoppingList_Edit adapter = new Adapter_ShoppingList_Edit(this,
                R.layout.element_listview_shoppinglist_article,
                this.positions.toArray(new ListPosition[this.positions.size()]));
        this.ListView1.setAdapter(adapter);
        SetListViewListener();
    }

    private void SetListViewListener(){
        this.ListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO
            }
        });
    }




}
