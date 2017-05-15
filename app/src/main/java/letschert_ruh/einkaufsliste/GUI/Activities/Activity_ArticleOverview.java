package letschert_ruh.einkaufsliste.GUI.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import letschert_ruh.einkaufsliste.Database.Article;
import letschert_ruh.einkaufsliste.Database.Queries.ArticleQueries;
import letschert_ruh.einkaufsliste.GUI.Adapter.Adapter_Articlelist_Edit;
import letschert_ruh.einkaufsliste.R;

public class Activity_ArticleOverview extends Activity {

    private ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_articlelist_overview);
        setTitle(getString(R.string.action_EditArticles));

        listView1 = (ListView)findViewById(R.id.lv_Article_List);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
