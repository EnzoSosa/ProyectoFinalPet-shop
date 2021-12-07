
package com.magicpet.petshop.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class CarritoConProducto implements Serializable {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @OneToOne
    private Carrito carrito;
    private Integer cantidad;
    private Double precioTotal;

    public CarritoConProducto() {
    }

    public CarritoConProducto(String id, Carrito carrito, Integer cantidad, Double precioTotal) {
        this.id = id;
        this.carrito = carrito;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    @Override
    public String toString() {
        return "CarritoConProducto{" + "id=" + id + ", carrito=" + carrito + ", cantidad=" + cantidad + ", precioTotal=" + precioTotal + '}';
    }
    
    
    
    
}
