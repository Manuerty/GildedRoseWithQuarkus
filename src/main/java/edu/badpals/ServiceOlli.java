package edu.badpals;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import edu.badpals.Scope.*;

@ApplicationScoped
public class ServiceOlli {
    public ServiceOlli(){

    }

    @Transactional
    public boolean isOrderCreada(Usuaria user, Item item){
        Usuaria User1 = Usuaria.find("nombre",user.getNombre()).firstResult();
        Item producto = Item.find("nombre",item.getNombre()).firstResult();
        if(User1 != null && producto != null){
            Orden Order = new Orden(User1,producto);
            Order.persist();
            return true;
        }else{
            return false;
        }

    }

    public List<Orden> obtenerPedidoUsuaria(String nombre_usuaria) {
        return Orden.findByUserName(nombre_usuaria);
    }

    public List<Orden> cargaOrder(String User_nombre) {
            return Orden.findByUserName(User_nombre);
    }
}
