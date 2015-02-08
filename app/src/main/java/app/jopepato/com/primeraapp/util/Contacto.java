package app.jopepato.com.primeraapp.util;

import android.net.Uri;

/**
 * Created by jopepato on 08/02/2015.
 */

//Clase contacto que contendra los datos de una persona
public class Contacto {
    private String nombre, telefono, email, direccion;
    private Uri imageUri;

    //Constructor
    public Contacto(String nombre, String telefono, String email, String direccion, Uri imageUri) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.imageUri = imageUri;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

   public Uri getImageUri(){return imageUri;};

    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setImageUri(Uri imageUri){
        this.imageUri = imageUri;
    }

}


