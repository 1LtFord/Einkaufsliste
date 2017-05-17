package letschert_ruh.einkaufsliste.GUI.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import letschert_ruh.einkaufsliste.Database.Article;
import letschert_ruh.einkaufsliste.Database.Queries.ArticleQueries;
import letschert_ruh.einkaufsliste.GUI.Adapter.Adapter_Articlelist_Edit;
import letschert_ruh.einkaufsliste.R;

public class Activity_ArticleOverview extends Activity {

    private ListView ArticleListView;
    private Adapter_Articlelist_Edit adapter;
    private EditText SearchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articlelist_overview);
        setTitle(getString(R.string.action_EditArticles));

        this.ArticleListView = (ListView)findViewById(R.id.lv_Article_List);
        this.SearchText = (EditText)findViewById(R.id.et_Searchbar);

        setSearchbarListener();
        setListViewOnClickListener();

    }

    private void setListViewOnClickListener(){
        ArticleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
        List<Article> Articles = queries.GetByNameOrManufacturer(SplitSearchString(s), this);

        Adapter_Articlelist_Edit adapter = new Adapter_Articlelist_Edit(this,R.id.lv_Article_List,Articles.toArray(new Article[Articles.size()]));
        ArticleListView.setAdapter(adapter);
    }

    private String[] SplitSearchString(String s){
        String[] st = SearchText.getText().toString().split(" ");
        return st;
    }
}
