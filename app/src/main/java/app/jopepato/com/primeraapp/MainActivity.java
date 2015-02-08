package app.jopepato.com.primeraapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

import app.jopepato.com.primeraapp.util.ContactListAdapter;
import app.jopepato.com.primeraapp.util.Contacto;
import app.jopepato.com.primeraapp.util.TextChangedListener;


public class MainActivity extends ActionBarActivity {

    private EditText txtNombre, txtTelefono, txtEmail, txtDireccion;
    private ArrayAdapter<Contacto> adapter;
    private ImageView imgViewContacto;
    private ListView contactsListView;
    private Button btnAgregar;
    private TabHost tabHost;
    private int request_code = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponetesUI();
        inicializarListaContactos();
        inicializarTabs();

    }

    private void inicializarListaContactos() {
        adapter = new ContactListAdapter(this, new ArrayList<Contacto>());
        contactsListView.setAdapter(adapter);

    }

    private void inicializarTabs() {
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("tab1");
      //  spec.setContent(R.id.tab1);
        spec.setIndicator("Crear");
        tabHost.addTab(spec);

        TabHost.TabSpec spec2 = tabHost.newTabSpec("tab2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("Contactos");
        tabHost.addTab(spec2);
    }

    //Con esta funcion inicializamos los componentes y los unimos a los de xml
    private void inicializarComponetesUI() {
        txtNombre = (EditText) findViewById(R.id.cmpNombre);
        txtTelefono = (EditText) findViewById(R.id.cmpTelefono);
        txtEmail = (EditText) findViewById(R.id.cmpEmail);
        txtDireccion = (EditText) findViewById(R.id.cmpDireccion);
        contactsListView = (ListView) findViewById(R.id.listView);
        imgViewContacto = (ImageView) findViewById(R.id.imgViewContacto);

        txtNombre.addTextChangedListener(new TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence seq, int i1, int i2, int i3) {
                //Ponemos el boton en enable, cuando hay algo escrito en el nombre
                btnAgregar = (Button) findViewById(R.id.btnAgregar);
                btnAgregar.setEnabled(!seq.toString().trim().isEmpty());
            }
        });
    }
}

