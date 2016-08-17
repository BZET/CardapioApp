package br.com.bzet.cardapio.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.bzet.cardapio.MainActivity;
import br.com.bzet.cardapio.R;
import br.com.bzet.cardapio.interfaces.RecyclerViewOnClickListenerHack;
import br.com.bzet.cardapio.modelo.Item;

/**
 * Created by bruna on 14/08/2016.
 */
public class AperitivosAdapter extends RecyclerView.Adapter<AperitivosAdapter.MyViewHolder>{

    private List<Item> itemList;
    private LayoutInflater layoutInflater;
    private RecyclerViewOnClickListenerHack recyclerViewOnClickListenerHack;

    public AperitivosAdapter(Context c, List<Item> itemList) {
        this.itemList = itemList;
        layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("LOG","onCreateViewHolder");
        View view = layoutInflater.inflate(R.layout.item_card,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }
//Vincula os dados da View
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.i("LOG","onBindViewHolder");
        holder.imagemItem.setImageResource(itemList.get(position).getImagemUrl());
        holder.nomeItem.setText(itemList.get(position).getNome());
        holder.precoItem.setText(itemList.get(position).getPreco());
        //holder.descricaoItem.setText(itemList.get(position).getDescricao());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r){
        recyclerViewOnClickListenerHack = r;

    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imagemItem;
        public TextView nomeItem;
        public TextView precoItem;
       // public TextView descricaoItem;
        public ImageView itemAdd;

        public MyViewHolder(View itemView){
            super(itemView);

            imagemItem = (ImageView) itemView.findViewById(R.id.imagemItem);
            nomeItem = (TextView) itemView.findViewById(R.id.nomeItem);
            precoItem = (TextView) itemView.findViewById(R.id.precoItem);
           // descricaoItem = (TextView) itemView.findViewById(R.id.descricaoItem);
            itemAdd = (ImageView) itemView.findViewById(R.id.itemAdd);
            itemAdd.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Log.i("Log Click","no add");
                }
            });

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            if(recyclerViewOnClickListenerHack != null){
                recyclerViewOnClickListenerHack.onClickListener(v,getPosition());
            }
        }


    }
}
