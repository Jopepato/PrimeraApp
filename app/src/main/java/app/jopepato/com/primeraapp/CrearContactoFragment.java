package app.jopepato.com.primeraapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import app.jopepato.com.primeraapp.util.ContactReceiver;
import app.jopepato.com.primeraapp.util.Contacto;
import app.jopepato.com.primeraapp.util.TextChangedListener;

/**
 * Created by jopepato on 08/02/2015.
 */
public class CrearContactoFragment extends Fragment implements View.OnClickListener{

    private EditText txtNombre, txtTelefono, txtEmail, txtDireccion;
    private ImageView imgViewContacto;
    private Button btnAgregar;
    private int request_code = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_crear_contacto, container, false);
        inicializarComponentes(rootView);
        return rootView;
    }

    private void inicializarComponentes(final View view) {
        txtNombre = (EditText) view.findViewById(R.id.cmpNombre);
        txtTelefono = (EditText) view.findViewById(R.id.cmpTelefono);
        txtEmail = (EditText) view.findViewById(R.id.cmpEmail);
        txtDireccion = (EditText) view.findViewById(R.id.cmpDireccion);
        imgViewContacto = (ImageView) view.findViewById(R.id.imgViewContacto);
        txtNombre.addTextChangedListener(new TextChangedListener(){
            @Override
            public void onTextChanged(CharSequence seq, int i1, int i2, int i3) {
                //Ponemos el boton en enable, cuando hay algo escrito en el nombre
                btnAgregar = (Button) view.findViewById(R.id.btnAgregar);
                btnAgregar.setEnabled(!seq.toString().trim().isEmpty());
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == btnAgregar){
            //Funcion que nos muestra un mensaje por pantalla cuando agregamos a alguien
            agregarContacto(
                    txtNombre.getText().toString(),
                    txtTelefono.getText().toString(),
                    txtDireccion.getText().toString(),
                    txtEmail.getText().toString(),
                    String.valueOf(imgViewContacto.getTag()) //Obtenemos el atributo TAG de la Uri de la imagen
            );
            String msg = String.format("%s ha sido agregado a la lista", txtNombre.getText());
            Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT).show();
            btnAgregar.setEnabled(false);
            limpiarCampos();
        }else if(view == imgViewContacto){
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
    }
    private void agregarContacto(String nombre, String telefono, String Email, String direccion, String imageUri) {
        Contacto nuevo = new Contacto(nombre, telefono, Email, direccion, imageUri);
        Intent intent  = new Intent("listaContactos");
        intent.putExtra("operacion", ContactReceiver.CONTACTO_AGREGADO);
        intent.putExtra("datos", nuevo);
        getActivity().sendBroadcast(intent);
    }

    protected void limpiarCampos() {
        txtNombre.getText().clear();
        txtEmail.getText().clear();
        txtDireccion.getText().clear();
        txtTelefono.getText().clear();
        //Reestablecemos la imagen
        imgViewContacto.setImageResource(R.drawable.contacto);
        txtNombre.requestFocus();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode== request_code){
            imgViewContacto.setImageURI(data.getData());
            //Utilizamos el atributo TAG para almacenar la URI del archivo seleccionado
            imgViewContacto.setTag(data.getData());
        }
    }
}
