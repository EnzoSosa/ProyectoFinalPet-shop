
package com.magicpet.petshop.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Carrito implements Serializable {
 
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @ManyToOne
    private Usuario usuario;
    private Integer status;

    public Carrito() {
    }

    public Carrito(String id, Date fecha, Usuario usuario, Integer status) {
        this.id = id;
        this.fecha = fecha;
        this.usuario = usuario;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Carrito{" + "id=" + id + ", fecha=" + fecha + ", usuario=" + usuario + ", status=" + status + '}';
    }
    
    
    
    
    
    
}
