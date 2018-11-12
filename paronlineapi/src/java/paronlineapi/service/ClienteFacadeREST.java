/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paronlineapi.service;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import py.com.fpuna.paronlineapi.entity.Cliente;
import py.com.fpuna.paronlineapi.utils.LoginResponse;

/**
 *
 * @author Mauricio
 */
@javax.ejb.Stateless
@Path("clientes")
public class ClienteFacadeREST extends AbstractFacade<Cliente> {

    //@Inject
    @PersistenceContext(unitName = "paronlineapiPU")
    private EntityManager em;

    public ClienteFacadeREST() {
        super(Cliente.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Cliente entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Cliente entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Cliente find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cliente> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cliente> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("/login/{loginName}/{passwd}")
    @Produces({MediaType.APPLICATION_JSON})
    public LoginResponse login(@PathParam("loginName") String from, @PathParam("passwd") String to) {
        LoginResponse retorno = new LoginResponse();
        try {

            Cliente ejCliente = super.findLogin("login_name", from);
            if (ejCliente != null) {
                if (ejCliente.getPasswd().compareToIgnoreCase(to) == 0) {
                    retorno.setError(false);
                    retorno.setMensaje("Login correcto");
                    ejCliente.setPasswd("xxxxxxxxxxx");
                    retorno.setUsuario(ejCliente);
                } else {
                    retorno.setError(true);
                    retorno.setMensaje("Usuario/password incorrecto");
                }
            } else {
                retorno.setError(true);
                retorno.setMensaje("Usuario/password incorrecto");
            }

        } catch (Exception e) {
            System.err.print(e);
            retorno.setError(true);
            retorno.setMensaje("Usuario/password incorrecto");
        }
        //return super.findRange(new int[]{from, to});
        return retorno;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("paronlineapiPU");
        EntityManager em = emf.createEntityManager();
        if (em == null) {
            throw new IllegalStateException("EntityManager no esta seteado");
        }

        return em;
    }

}
