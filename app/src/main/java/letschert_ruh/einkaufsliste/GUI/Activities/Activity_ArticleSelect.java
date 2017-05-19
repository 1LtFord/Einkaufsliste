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
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import letschert_ruh.einkaufsliste.Database.Article;
import letschert_ruh.einkaufsliste.Database.ListPosition;
import letschert_ruh.einkaufsliste.Database.Queries.ArticleQueries;
import letschert_ruh.einkaufsliste.Database.Queries.ShoppingListQueries;
import letschert_ruh.einkaufsliste.Database.ShoppingList;
import letschert_ruh.einkaufsliste.GUI.Adapter.Adapter_Articlelist_Edit;
import letschert_ruh.einkaufsliste.R;

public class Activity_ArticleSelect extends Activity {

        private ListView ArticleListView;
        private Adapter_Articlelist_Edit adapter;
        private EditText SearchText;
        private List<Article> Articles;


        private long ShoppingListId;
        private int ShoppingListindex;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_article_select);

            setTitle(getString(R.string.title_SelectArticle));

            this.ArticleListView = (ListView)findViewById(R.id.lv_Article_List);
            this.SearchText = (EditText)findViewById(R.id.et_Searchbar);

            Intent intent = getIntent();
            this.ShoppingListId = intent.getLongExtra("Id", Long.MAX_VALUE);
            this.ShoppingListindex = intent.getIntExtra("Index", -1);

            setSearchbarListener();
            setListViewOnClickListener();

            Search("");
        }


    private void Action_AddItemToShoppinglist(int i, int amount, Context context){

        Article selectedArticle = Articles.get(i);
        ListPosition position = new ListPosition();

        position.Amount = amount;
        position.Checked = false;
        position.Article = selectedArticle;
        position.ShoppingList = getShoppingList();
        position.SaveOrUpdate(this);

        Intent intent = new Intent(context,Activity_ListView.class);
        intent.putExtra("Id", ShoppingListId);
        intent.putExtra("Index", ShoppingListindex);
        startActivity(intent);
    }

    private void setListViewOnClickListener(){
        ArticleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupArticleAmount((position), parent.getContext());
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

    private ShoppingList getShoppingList(){
        ShoppingListQueries queries = new ShoppingListQueries();

        List<ShoppingList> lists = queries.GetAll(this);
        ShoppingList list = lists.get(ShoppingListindex);

        if(ShoppingListId != list.GetId()){
            int i = 0;
            while(ShoppingListId != list.GetId() && i < lists.size()){
                list = lists.get(i);
                i++;
            }
            if(ShoppingListId != list.GetId()){
                list = null;
            }
        }
        return list;
    }

    private void popupArticleAmount(final int i, final android.content.Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.title_ArticleAmount);

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setHint(R.string.hint_ArticleAmount);
        builder.setView(input);

        builder.setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Action_AddItemToShoppinglist(i, Integer.parseInt(input.getText().toString()),context);
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

