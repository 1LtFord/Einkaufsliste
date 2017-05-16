package letschert_ruh.einkaufsliste.GUI.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import letschert_ruh.einkaufsliste.Database.Article;
import letschert_ruh.einkaufsliste.R;

public class Activity_EditArticle extends Activity {

    private TextView Name;
    private TextView Merchant;
    private TextView Manufacturer;
    private TextView Cost;

    private EditText NewName;
    private EditText NewMerchant;
    private EditText NewManufacturer;
    private EditText NewCost;

    private Button Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);

        this.Name = (TextView)findViewById(R.id.tv_Name);
        this.Merchant = (TextView)findViewById(R.id.tv_Merchant);
        this.Manufacturer = (TextView)findViewById(R.id.tv_Manufacturer);
        this.Cost = (TextView)findViewById(R.id.tv_Price);

        this.NewName = (EditText)findViewById(R.id.tbx_ArticleName);
        this.NewMerchant = (EditText)findViewById(R.id.tbx_Merchant);
        this.NewManufacturer = (EditText)findViewById(R.id.tbx_Manufacturer);
        this.NewCost = (EditText)findViewById(R.id.tbx_Cost);

        this.Save = (Button)findViewById(R.id.btn_Save);

        setInitialText();
        setEditTextListeners();
        setButtonClickListener();
    }

    private void setInitialText(){
        this.Name.setText(this.NewName.getText());
        this.Merchant.setText(this.NewMerchant.getText());
        this.Manufacturer.setText(this.NewMerchant.getText());
        this.Cost.setText(this.NewCost.getText());
    }

    private void setEditTextListeners(){
        this.NewName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Name.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        this.NewMerchant.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Merchant.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        this.NewManufacturer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Manufacturer.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        this.NewCost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Cost.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setButtonClickListener(){
        Save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveArticleToDatabase();
            }
        });
    }

    private void SaveArticleToDatabase(){
        Article newArticle = new Article();
        boolean complete = true;

        if (this.NewName.getText().toString().length() > 0){
            newArticle.Name = this.NewName.getText().toString();
        }
        else{
            complete = false;
        }

        if(complete && this.NewMerchant.getText().length() > 0){
            newArticle.Merchant = this.NewMerchant.getText().toString();
        }

        if(complete && this.NewManufacturer.getText().length() > 0){
            newArticle.Manufacturer = this.NewManufacturer.getText().toString();
        }

        if(complete && this.NewCost.getText().length() > 0){
            newArticle.Cost = Double.parseDouble(this.NewCost.getText().toString());
        }

        if(complete){
            //newArticle.SaveOrUpdate();
            //TODO Intend
        }
        else{
            Toast.makeText(getApplicationContext(),getString(R.string.error_NewArticle_EmptyName), Toast.LENGTH_SHORT).show();
        }
    }
}
