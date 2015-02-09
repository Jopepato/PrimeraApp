package app.jopepato.com.primeraapp;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

import app.jopepato.com.primeraapp.util.ContactListAdapter;
import app.jopepato.com.primeraapp.util.Contacto;
import app.jopepato.com.primeraapp.util.TabsPagerAdapter;
import app.jopepato.com.primeraapp.util.TextChangedListener;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener, ViewPager.OnPageChangeListener{

    //Control de fichas (tabs)
    private ViewPager viewPager;
    private TabsPagerAdapter adapter;
    private ActionBar actionBar;
    //Titulos de las fichas
    private String[] titulos = {"Crear Contacto", "Lista Contactos"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarTabs();
    }

    private void inicializarTabs() {
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        adapter = new TabsPagerAdapter(getFragmentManager());


        viewPager.setAdapter(adapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //Agregando las fichas (tabs)


            for(String nombre: titulos){
                ActionBar.Tab tab = actionBar.newTab().setText(nombre);
                tab.setTabListener(this);
                actionBar.addTab(tab);
            }
        viewPager.setOnPageChangeListener(this);
    }

    //<editor-fold desc="METODOS TAB CHANGE LISTENER">
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
    //</editor-fold>
}

