package edu.badpals.Scope;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="ITEMS")
public class Item extends PanacheEntityBase{
    public Item() {
    }


    public Item(String nombre, int calidad, String tipo) {
        this.nombre = nombre;
        this.calidad = calidad;
        this.tipo = tipo;
    }

    @Id
    @Column(name="nombre_item")
    private String nombre;
    @Column(name="calidad")
    private int calidad;
    @Column(name="tipo")
    private String tipo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCalidad() {
        return calidad;
    }

    public void setCalidad(int calidad) {
        this.calidad = calidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Item{" +
                "nombre='" + nombre + '\'' +
                ", calidad=" + calidad +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
