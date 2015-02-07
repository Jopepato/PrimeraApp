package app.jopepato.com.primeraapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.jopepato.com.primeraapp.util.TextChangedListener;


public class MainActivity extends ActionBarActivity {

    private EditText txtNombre, txtTelefono, txtEmail, txtDireccion;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponetesUI();
    }

    private void inicializarComponetesUI() {
        txtNombre = (EditText) findViewById(R.id.cmpNombre);
        txtTelefono = (EditText) findViewById(R.id.cmpTelefono);
        txtEmail = (EditText) findViewById(R.id.cmpEmail);
        txtDireccion = (EditText) findViewById(R.id.cmpDireccion);

        txtNombre.addTextChangedListener(new TextChangedListener(){
            @Override
            public void onTextChanged(CharSequence seq, int i1, int i2, int i3) {
                btnAgregar = (Button) findViewById(R.id.btnAgregar);
                btnAgregar.setEnabled(!seq.toString().trim().isEmpty());
            }
        });
    }

    public void onClick(View view) {

        String msg = String.format("%s ha sido agregado a la lista", txtNombre.getText());
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        btnAgregar.setEnabled(false);
        limpiarCampos();
    }

    protected void limpiarCampos() {
        txtNombre.getText().clear();
        txtEmail.getText().clear();
        txtDireccion.getText().clear();
        txtTelefono.getText().clear();
        txtNombre.requestFocus();
    }
}
