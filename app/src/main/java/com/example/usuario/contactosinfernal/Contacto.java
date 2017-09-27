package com.example.usuario.contactosinfernal;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

import java.util.Comparator;

/**
 * Created by usuario on 21/09/2017.
 */

public class Contacto implements Parcelable {
    private String Nombre;
    private String email;
    private int edad;


    protected Contacto(Parcel in) {
        Nombre = in.readString();
        email = in.readString();
        edad = in.readInt();
    }

    public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {
        @Override
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        @Override
        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Nombre);
        parcel.writeString(email);
        parcel.writeInt(edad);
    }


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Contacto(String nombre, String email, int edad) {
        Nombre = nombre;
        this.email = email;
        this.edad = edad;
    }

    public Contacto(String nombre) {
        Nombre = nombre;

    }


    @Override
    public String toString() {
        return
                Nombre+" edad: "+edad+"\n"+ "email: "+email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contacto contacto = (Contacto) o;

        if (edad != contacto.edad) return false;
        if (Nombre != null ? !Nombre.equals(contacto.Nombre) : contacto.Nombre != null)
            return false;
        return email != null ? email.equals(contacto.email) : contacto.email == null;

    }

    @Override
    public int hashCode() {
        int result = Nombre != null ? Nombre.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + edad;
        return result;
    }
}