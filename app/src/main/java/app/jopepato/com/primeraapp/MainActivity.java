package app.jopepato.com.primeraapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

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
        spec.setContent(R.id.tab1);
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

        txtNombre.addTextChangedListener(new TextChangedListener(){
            @Override
            public void onTextChanged(CharSequence seq, int i1, int i2, int i3) {
                //Ponemos el boton en enable, cuando hay algo escrito en el nombre
                btnAgregar = (Button) findViewById(R.id.btnAgregar);
                btnAgregar.setEnabled(!seq.toString().trim().isEmpty());
            }
        });
    }

    public void onClick(View view) {
        //Funcion que nos muestra un mensaje por pantalla cuando agregamos a alguien
        agregarContacto(
                txtNombre.getText().toString(),
                txtTelefono.getText().toString(),
                txtDireccion.getText().toString(),
                txtEmail.getText().toString(),
                (Uri) imgViewContacto.getTag() //Obtenemos el atributo TAG de la Uri de la imagen
        );
        String msg = String.format("%s ha sido agregado a la lista", txtNombre.getText());
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        btnAgregar.setEnabled(false);
        limpiarCampos();
    }



    private void agregarContacto(String nombre, String telefono, String Email, String direccion, Uri imageUri) {
        Contacto nuevo = new Contacto(nombre, telefono, Email, direccion, imageUri);
        adapter.add(nuevo);
    }

    protected void limpiarCampos() {
        txtNombre.getText().clear();
        txtEmail.getText().clear();
        txtDireccion.getText().clear();
        txtTelefono.getText().clear();
        //Reestablecemos la imagen
        imgViewContacto.setImageResource(R.drawable.contacto);
        imgViewContacto.getTag();
        txtNombre.requestFocus();
    }

    public void onImgClick(View view) {
        Intent intent = null;
        //Verificamos la version
        if (Build.VERSION.SDK_INT < 19){
            //Android jellybean 4.3 y anteriores
            intent = new Intent();
            //Con get_content podemos acceder a los contenidos del telefono
            intent.setAction(Intent.ACTION_GET_CONTENT);

        }else{
            //Android 4.4 y superiores
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        startActivityForResult(intent, request_code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == request_code){
            imgViewContacto.setImageURI(data.getData());
            //Utilizamos el atributo TAG para almacenar la URI del archivo seleccionado
            imgViewContacto.setTag(data.getData());
        }
    }
}
