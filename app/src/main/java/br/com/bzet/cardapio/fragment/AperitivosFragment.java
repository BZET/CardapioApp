package br.com.bzet.cardapio.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.List;

import br.com.bzet.cardapio.ItemActivity;
import br.com.bzet.cardapio.MainActivity;
import br.com.bzet.cardapio.R;
import br.com.bzet.cardapio.adapters.AperitivosAdapter;
import br.com.bzet.cardapio.interfaces.RecyclerViewOnClickListenerHack;
import br.com.bzet.cardapio.modelo.Item;

/**
 * Created by bruna on 31/07/2016.
 */
public class AperitivosFragment extends Fragment implements RecyclerViewOnClickListenerHack{

    private RecyclerView recyclerView;
    private List<Item> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.aperitivos_fragment,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewList);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        itemList = ((MainActivity) getActivity()).getSetItemList(4);

        AperitivosAdapter aperitivosAdapter= new AperitivosAdapter(getActivity(),itemList);
        aperitivosAdapter.setRecyclerViewOnClickListenerHack(this);
        recyclerView.setAdapter(aperitivosAdapter);

        return view;
    }

    @Override
    public void onClickListener(View view, int position) {
        Toast.makeText(getActivity(),"LOG Cliquei " + position,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), ItemActivity.class);
        intent.putExtra("item", itemList.get(position));
        getActivity().startActivity(intent);
    }

}
