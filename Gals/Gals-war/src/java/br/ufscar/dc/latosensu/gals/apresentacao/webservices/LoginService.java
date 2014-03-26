/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.apresentacao.webservices;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Pessoa;
import br.ufscar.dc.latosensu.gals.negocio.fachada.PessoaFachada;
import org.primefaces.json.JSONObject;
import br.ufscar.dc.latosensu.gals.persistencia.PessoaDAO;
import br.ufscar.dc.latosensu.gals.apresentacao.utils.Constants;

/**
 * REST Web Service
 *
 * @author Mauricio Jose De Grandi Junior
 */
@Stateless
@LocalBean
@Path("login")
@Consumes({"application/x-www-form-urlencoded; charset=utf-8"})
@Produces({"application/json ;charset=utf-8"})
public class LoginService {

    @Context
    private UriInfo context;
    
    @EJB 
    PessoaFachada pesFachada;

    /**
     * Creates a new instance of LoginService
     */
    public LoginService() {
    }

    /**
     * Retrieves representation of an instance of webservices.LoginService
     * @return an instance of java.lang.String
     */
    @GET
    public Response getLogin(@QueryParam("user")String user, @QueryParam("pass")String pass) {
        JSONObject response = new JSONObject();
        try {
            if ((user == null) || (pass == null)) {
                response.put("status", Constants.HTTP_BAD_REQUEST);    
            } else {
                
                if (!pesFachada.validarUsuario(user, pass)) {
                    response.put("status", Constants.HTTP_UNAUTHORIZED);
                } else {
                    Pessoa p = pesFachada.consultarPessoaPorCpf(user);
                    
                    response.put("status", Constants.HTTP_OK);
                    response.put("user", p.getUserName());
                    response.put("pass", p.getPassword());
                }
                
            }
        } catch (Exception ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                response.put("status", Constants.HTTP_SERVER_INTERNAL_ERROR);
            } catch (Exception ex1) {
                Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        return Response.ok(response.toString()).build();
    }

    /**
     * PUT method for updating or creating an instance of LoginService
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}