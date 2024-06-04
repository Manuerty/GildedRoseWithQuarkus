package edu.badpals.Scope;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="ORDENES")
public class Orden extends PanacheEntityBase {
    public Orden(){}

    public Orden(Usuaria Usuario, Item Object){
        this.User = Usuario;
        this.Item = Object;
    }
    public Orden(Usuaria Usuario, int id, Item Object){
        this.User = Usuario;
        this.Id = id;
        this.Item = Object;
    }


    @Id
    @Column(name="Id_Orden")
    public int Id;

    @OneToOne
    @JoinColumn(name="Usuaria",referencedColumnName = "nombre_usuaria")
    public Usuaria User;

    @OneToOne
    @JoinColumn(name="item", referencedColumnName = "nombre_item")
    public Item Item;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public Usuaria getUser() {
        return User;
    }

    public void setUser(Usuaria user) {
        this.User = user;
    }

    public Item getItem() {
        return Item;
    }

    public void setItem(Item item) {
        this.Item = item;
    }


    public static List<Orden> findByUserName(String nombre_user){
        List<Orden> orders = Orden.listAll();
        List<Orden> ordersByName = orders
                .stream()
                .filter(o -> o.getUser().getNombre().equalsIgnoreCase(nombre_user))
                .collect(Collectors.toList());
        return ordersByName.isEmpty()? List.of(): ordersByName;
    }
    /*
    public static List<Order> findByUserName(String nombre_usuaria) {
        List<Order> pedidos = Order.findAll().list();
        List<Order> pedidosFiltrados = new ArrayList<>();
        Usuaria usuaria = Usuaria.find("nombre",nombre_usuaria).firstResult();
        if ( usuaria != null){
            for (Order pedido : pedidos){
                if (pedido.getUser().getNombre().equals(nombre_usuaria)){
                    pedidosFiltrados.add(pedido);
                }
            }
        }
        return pedidosFiltrados;
    }*/

}
