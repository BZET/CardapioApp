package br.com.bzet.cardapio.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.bzet.cardapio.modelo.Item;


/**
 * Created by Bruna on 07/01/16.
 */
public class ItemDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String TABELA = "Item";
    private static final String DATABASE = "Cardapio";

    public ItemDAO(Context context){
        super(context,DATABASE,null,VERSAO);
    }

    //Chamado para criar o banco de dados

    public void onCreate (SQLiteDatabase db){
        String ddl = "CREATE TABLE " + TABELA
                + " (id INTEGER PRIMARY KEY, "
                + " nome TEXT NOT NULL, "
                + " tipo TEXT NOT NULL, "
                + " preco TEXT NOT NULL, "
                + " descricao TEXT, "
                + " imagemUrl TEXT NOT NULL, "
                + " ativo INTEGER NOT NULL, ";
        db.execSQL(ddl);

    }

    //Chamada quando o DB é atualizado
    public void onUpgrade(SQLiteDatabase database, int versaoAntiga, int versaoNova){

       // String sql = "ALTER TABLE " + TABELA + " ADD COLUMN caminhoFoto TEXT";
        //String sql = "DROP TABLE IF EXISTS" + TABELA;
       // database.execSQL(sql);
        //onCreate(database);
    }

    //Chamada para fazer o insert no banco
    public void insere(Item item){

        ContentValues values = new ContentValues();

        values.put("nome", item.getNome());
        values.put("tipo", item.getPreco());
        values.put("preco", item.getPreco());
        values.put("descricao", item.getDescricao());
        values.put("imagemUrl", item.getImagemUrl());
        values.put("ativo", item.getAtivo());


        getWritableDatabase().insert(TABELA,null,values);
    }
    //Faz o select da lista do cardapio
    public List<Item> getLista(String tipo){

        List<Item> listItem = new ArrayList<>();

        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM " + TABELA + " WHERE tipo='"+ tipo +"' ativo =1;",null);

        while(c.moveToNext()){
            Item item = new Item();

            item.setId(c.getInt(c.getColumnIndex("id")));
            item.setNome(c.getString(c.getColumnIndex("nome")));
            item.setNome(c.getString(c.getColumnIndex("tipo")));
            item.setPreco(c.getString(c.getColumnIndex("preco")));
            item.setDescricao(c.getString(c.getColumnIndex("descricao")));
            item.setImagemUrl(c.getInt(c.getColumnIndex("imagemUrl")));
            item.setAtivo(c.getInt(c.getColumnIndex("ativo")));


            listItem.add(item);
        }
        c.close();
        return listItem;
    }
    //Delete itens
//    public void deletar (Item item){
//        String[] args = {item.getId().toString()};
//        getWritableDatabase().delete(TABELA,"id = ?",args);
//    }

    //update  na tabela
//    public void atualiza(Item item){
//        ContentValues values = new ContentValues();
//
//        values.put("nome", item.getNome());
//        values.put("tipo", item.getPreco());
//        values.put("preco", item.getPreco());
//        values.put("descricao", item.getDescricao());
//        values.put("imagemUrl", item.getImagemUrl());
//        values.put("ativo", item.getAtivo());
//
//        getWritableDatabase().update(TABELA,values,"id=?",new String[]{item.getId().toString()});
//    }


//    public boolean isAluno(String telefone){
//        String[] parametros = {telefone};
//
//        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT telefone FROM " + TABELA + " WHERE telefone=?",parametros);
//        int total = rawQuery.getCount();
//        rawQuery.close();
//        return total>0;
//    }


}
