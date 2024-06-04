package edu.badpals;

import edu.badpals.Scope.*;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.List;
import java.util.Optional;
@Path("/")
public class ResourcesOlli {

    @Inject
    ServiceOlli service;

    @GET
    @Path("wellcome")
    @Produces (MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hola Olivanders";
    }

    @GET
    @Path("user/{nombre}")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getUser (@PathParam("nombre") String name) {
        Optional<Usuaria> userOptional = Usuaria.findByIdOptional(name);
        return userOptional.isPresent()?
                Response.status(Response.Status.OK).entity(userOptional).build():
                Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("createOrder")
    @Consumes(MediaType.APPLICATION_JSON) //mediante un curl
    @Transactional
    public Response order (Orden NewOrder){
            Usuaria userOrder = NewOrder.getUser();
            Item itemOrder = NewOrder.getItem();
            return service.isOrderCreada(userOrder, itemOrder)?
                    Response.status(201).entity(NewOrder).build():
                    Response.status(404).build();
    }

    @GET
    @Path("pedidos/{usuaria}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Orden> getPedidosUsuaria(@PathParam("usuaria")String nombre_usuaria){
        return service.obtenerPedidoUsuaria(nombre_usuaria);
    }


    @GET
    @Path("item/{itemName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("itemName") String itemName){
        Optional<Item> optionalItem = Item.findByIdOptional(itemName);
        return optionalItem.isPresent()?
                Response.status(Response.Status.OK).entity(optionalItem).build():
                Response.status(Response.Status.NOT_FOUND).build();
    }
}
