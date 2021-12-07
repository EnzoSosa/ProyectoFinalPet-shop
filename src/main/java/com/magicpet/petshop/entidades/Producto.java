package com.magicpet.petshop.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Producto implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)//con column le decimos a JPA que esta columna no es nula,es decir,necesita la validacion si o si.
    private String nombre;

    private String codigo;

    @Column(nullable = false)
    private String descripcion;

    @Lob
    private String descripcionCompleta;

    @ManyToOne
    private Marca marca;

    @ManyToOne
    private Categoria categoria;

    private Integer stock;

    @Column(nullable = false)
    private Double precioUnitario;

    @OneToOne
    private Imagen imagen;

    public Producto() {
    }

    public Producto(String id, String nombre, String codigo, String descripcion, Marca marca, Categoria categoria,
            Integer stock, Double precioUnitario, Imagen imagen) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.marca = marca;
        this.categoria = categoria;
        this.stock = stock;
        this.precioUnitario = precioUnitario;
        this.imagen = imagen;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public String getDescripcionCompleta() {
        return descripcionCompleta;
    }

    public void setDescripcionCompleta(String descripcionCompleta) {
        this.descripcionCompleta = descripcionCompleta;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + ", descripcion=" + descripcion
                + ", marca=" + marca + ", categoria=" + categoria + ", stock=" + stock + ", precioUnitario="
                + precioUnitario + "}";
    }

}
