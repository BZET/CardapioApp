package br.com.bzet.cardapio;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;
import java.util.List;

import br.com.bzet.cardapio.adapters.ViewPagerAdapter;
import br.com.bzet.cardapio.fragment.AperitivosFragment;
import br.com.bzet.cardapio.fragment.ListaFragment;
import br.com.bzet.cardapio.modelo.Item;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private BottomBar mBottomBar;
    private ViewPagerAdapter viewPagerAdapter;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private CoordinatorLayout coordinatorLayout;


    private List<Item> listItem; //lista de itens do cardapio


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar config
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Bottom Menu Configuration
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItems(R.menu.main_menu);

        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                ListaFragment listaFragment = new ListaFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (menuItemId) {
                    case R.id.bottomBarItemOne:
                        tabLayout = (TabLayout) findViewById(R.id.tabs);
                        tabLayout.setupWithViewPager(viewPager);


                        viewPager = (ViewPager) findViewById(R.id.viewpager);
                        setupViewPager(viewPager);
                        break;
                    case R.id.bottomBarItemTwo:

                        fragmentTransaction.replace(R.id.viewpager, listaFragment, "mainFrag");
                        tabLayout.setEnabled(false);
                        tabLayout.removeAllTabs();

                        fragmentTransaction.commit();

                        break;
                    case R.id.bottomBarItemThree:

                        break;
                    case R.id.bottomBarItemFour:

                        break;
                }

            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bottomBarItemOne) {
                    // The user reselected item number one, scroll your content to top.
                }
            }
        });
        //Troca a cor da bottom tab
        //mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorAccent));
        //mBottomBar.mapColorForTab(1, 0xFF5D4037);
        //mBottomBar.mapColorForTab(2, "#7B1FA2");
        //mBottomBar.mapColorForTab(3, "#FF5252");

//         //FRAGMENT
//            AperitivosFragment aperitivosFragment = (AperitivosFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
//
//            if(aperitivosFragment == null) {
//                aperitivosFragment = new AperitivosFragment();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, aperitivosFragment, "mainFrag");
//                fragmentTransaction.commit();
//            }


        //Tabs para a Página de Cardápio

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }


    private void setupViewPager(ViewPager viewPager) {

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFrag(new AperitivosFragment(), "Aperitivos");
        viewPagerAdapter.addFrag(new AperitivosFragment(), "Saladas");
        viewPagerAdapter.addFrag(new AperitivosFragment(), "Carnes");
        viewPagerAdapter.addFrag(new AperitivosFragment(), "Massas");
        viewPagerAdapter.addFrag(new AperitivosFragment(), "Sandwiches");
        viewPagerAdapter.addFrag(new AperitivosFragment(), "Kids");
        viewPagerAdapter.addFrag(new AperitivosFragment(), "Bebida");


        viewPager.setAdapter(viewPagerAdapter);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBottomBar.onSaveInstanceState(outState);
    }

    public List<Item> getSetItemList(int qnt) {

        String itemPreco[] = {"R$ 43,50", "R$ 43,50", "R$ 43,00", "R$ 43,50"};
        String itemNome[] = {"Aussie Cheesse Fries", "Billy Ribs", "Bloomin Onion", "Kooka Burra Wings"};
        int itemImagemUrl[] = {R.drawable.outback_aperitivo_aussiecheesefries
                , R.drawable.outback_aperitivo_billyribs
                , R.drawable.outback_aperitivo_bloominonion
                , R.drawable.outback_aperitivo_kookaburrawings};
        String itemdescricao[] = {"Nossas tradicionais batatas fritas cobertas com uma combinação de queijos e bacon picado. Servidas com o clássico molho Ranch."
                , "Cinco costelas de porco regadas com o delicioso molho Billabong. Servidas com fritas."
                , "O verdadeiro sabor do Outback. A gigante cebola dourada, pronta para ser saboreada com nosso maravilhoso molho Bloom."
                , "10 sobreasas de frango empanadas com uma mistura de temperos especiais e acompanhadas do molho Blue Cheese e aipo. Explore uma das opções: suave, média ou picante."};
        String itemTipo[] = {"Apertivo", "Apertivo", "Apertivo", "Apertivo"};
        Integer itemAtivo[] = {1, 1, 1, 1};

        listItem = new ArrayList<>();

        for (int i = 0; i < qnt; i++) {
            Item itemObj = new Item(itemNome[i], itemPreco[i], itemdescricao[i], itemImagemUrl[i], itemTipo[i], itemAtivo[i]);
            listItem.add(itemObj);

        }
        return listItem;
    }



}
