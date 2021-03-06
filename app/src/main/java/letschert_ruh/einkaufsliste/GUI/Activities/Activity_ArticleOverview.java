package letschert_ruh.einkaufsliste.GUI.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import letschert_ruh.einkaufsliste.Database.Article;
import letschert_ruh.einkaufsliste.Database.ListPosition;
import letschert_ruh.einkaufsliste.Database.Queries.ArticleQueries;
import letschert_ruh.einkaufsliste.Database.Queries.ShoppingListQueries;
import letschert_ruh.einkaufsliste.Database.ShoppingList;
import letschert_ruh.einkaufsliste.GUI.Adapter.Adapter_Articlelist_Edit;
import letschert_ruh.einkaufsliste.R;

public class Activity_ArticleOverview extends Activity {

    private ListView ArticleListView;
    private Adapter_Articlelist_Edit adapter;
    private EditText SearchText;
    private List<Article> Articles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articlelist_overview);

        setTitle(getString(R.string.action_EditArticles));

        this.ArticleListView = (ListView)findViewById(R.id.lv_Article_List);
        this.SearchText = (EditText)findViewById(R.id.et_Searchbar);

        setSearchbarListener();
        setListViewOnClickListener();

        Search("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.articleoverview, menu);
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

    private void Action_AddArticle() {
        Intent intent = new Intent(this, Activity_EditArticle.class);
        startActivity(intent);
    }

    private void setListViewOnClickListener(){
        ArticleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //TODO
            }
        });
    }

    private void setSearchbarListener(){
        this.SearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s != ""){Search(s.toString());}
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void Search(String s){
        ArticleQueries queries = new ArticleQueries();
        this.Articles = queries.GetByNameOrManufacturer(SplitSearchString(s), this);

        Adapter_Articlelist_Edit adapter = new Adapter_Articlelist_Edit(this,R.layout.element_articlelist_article,Articles.toArray(new Article[Articles.size()]));
        ArticleListView.setAdapter(adapter);
    }

    private String[] SplitSearchString(String s){
        String[] st = SearchText.getText().toString().split(" ");
        return st;
    }
}
