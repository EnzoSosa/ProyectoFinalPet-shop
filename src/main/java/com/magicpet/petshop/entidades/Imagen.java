package com.magicpet.petshop.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Imagen implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String mime;
    private String nombre;

    @Lob //la anotacion @Lob permite indicarle a JPA que este campo/atributo va a ser un objeto pesado para tratarlo como tal y manejarlo con Byte, ya que permite almacenar cualquier cosa.
    @Basic(fetch = LAZY) // @Basic permite controlar el momento en que una propiedad es cargada desde la base de datos, evitando no traer valores que no son necesarios mientras se carga el objeto.
    private byte[] contenido;            //@Basic solo se usa cuando un objeto va a ser pesado,en este caso se cargaran imagenes.

    public Imagen() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Imagen{" + "id=" + id + ", mime=" + mime + ", nombre=" + nombre + '}';
    }

}
