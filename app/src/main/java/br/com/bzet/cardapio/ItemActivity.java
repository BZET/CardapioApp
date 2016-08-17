package br.com.bzet.cardapio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.bzet.cardapio.modelo.Item;

public class ItemActivity extends AppCompatActivity {

    private Toolbar select_toolbar;
    private Item item;
    //Componentes de Layout
    private ImageView select_imagemItem;
    private TextView select_nomeItem;
    private TextView select_precoItem;
    private TextView select_descricaoItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        if(savedInstanceState != null){
            item = savedInstanceState.getParcelable("item");
        }
        else {
            if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getParcelable("item") != null) {
                item = getIntent().getExtras().getParcelable("item");
            } else {
                Toast.makeText(this, "Fail!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        //Configuração dos componentes
        //Toolbar
        select_toolbar = (Toolbar) findViewById(R.id.select_toolbar);
        select_toolbar.setTitleTextColor(255);
        setSupportActionBar(select_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        //Imagem
        select_imagemItem = (ImageView) findViewById(R.id.select_imageItem);
        select_imagemItem.setImageResource(item.getImagemUrl());
        //Nome
        select_nomeItem = (TextView) findViewById(R.id.select_nomeItem);
        select_nomeItem.setText(item.getNome());
        //Preco
        select_precoItem = (TextView) findViewById(R.id.select_precoItem);
        select_precoItem.setText(item.getPreco());
        //Descrição
        select_descricaoItem = (TextView) findViewById(R.id.select_descricaoItem);
        select_descricaoItem.setText(item.getDescricao());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return true;
    }
}
