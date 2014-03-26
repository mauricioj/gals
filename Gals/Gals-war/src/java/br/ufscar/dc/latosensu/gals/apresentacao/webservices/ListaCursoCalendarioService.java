/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.apresentacao.webservices;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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
import br.ufscar.dc.latosensu.gals.negocio.entidade.Calendario;
import br.ufscar.dc.latosensu.gals.negocio.entidade.CalendarioCursoDisciplinaProfessor;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Curso;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Pessoa;
import br.ufscar.dc.latosensu.gals.negocio.fachada.CalendarioCursoDisciplinaProfessorFachada;
import br.ufscar.dc.latosensu.gals.negocio.fachada.CalendarioFachada;
import br.ufscar.dc.latosensu.gals.negocio.fachada.PessoaFachada;
import org.primefaces.json.JSONArray;
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
@Path("listaCursoCalendario")
@Consumes({"application/x-www-form-urlencoded; charset=utf-8"})
@Produces({"application/json ;charset=utf-8"})
public class ListaCursoCalendarioService {

    @Context
    private UriInfo context;
    
    @EJB 
    PessoaFachada pesFachada;
        
    @EJB 
    CalendarioCursoDisciplinaProfessorFachada ccdpFachada;
    /**
     * Creates a new instance of ListaCursoCalendarioResource
     */
    public ListaCursoCalendarioService() {
    }

    /**
     * Retrieves representation of an instance of webservices.ListaCursoCalendarioResource
     * @return an instance of java.lang.String
     */
    @GET
    public Response getCalendarioDisciplinas(@QueryParam("user")String user, @QueryParam("pass")String pass) {
        JSONObject response = new JSONObject();
        try {
            if ((user == null) || (pass == null)) {
                response.put("status", Constants.HTTP_BAD_REQUEST);    
            } else {                
                if (!pesFachada.validarUsuario(user, pass)) {
                    response.put("status", Constants.HTTP_UNAUTHORIZED);
                } else {
                    response.put("status", Constants.HTTP_OK);
                    
                    /*Collection<Calendario> lista = calFachada.listar();
                    JSONArray array = new JSONArray();
                    
                    for (Iterator<Calendario> it = lista.iterator(); it.hasNext();) {
                        Calendario cal = it.next();
                        
                        JSONObject calJSON = new JSONObject();
                        calJSON.put("nome", "xifre do capeta");
                        calJSON.put("id", cal.getId());
                        
                        array.put(calJSON);
                    }*/
                    
                    Collection<CalendarioCursoDisciplinaProfessor> lista = ccdpFachada.listar();
                    JSONArray array = new JSONArray();
                    
                    Collection<Curso> listCurso = new ArrayList<Curso>();
                    
                    for (Iterator<CalendarioCursoDisciplinaProfessor> it = lista.iterator(); it.hasNext();) {
                        CalendarioCursoDisciplinaProfessor aula = it.next();
                        
                        if (!listCurso.contains(aula.getCurso())) {
                            listCurso.add(aula.getCurso());
                        }
                    }
                    
                    for (Iterator<Curso> it = listCurso.iterator(); it.hasNext();) {
                        Curso c = it.next();
                        
                        JSONObject calJSON = new JSONObject();
                        calJSON.put("nome", c.getDescricao());
                        calJSON.put("id", c.getId());
                        
                        array.put(calJSON);
                    }
                    
                    response.put("calendarios", array);
                }
                
            }
        } catch (Exception ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                response.put("status", Constants.HTTP_SERVER_INTERNAL_ERROR);
                
                StringWriter sw = new StringWriter();
                ex.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
                response.put("message", exceptionAsString);
            } catch (Exception ex1) {
                Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        return Response.ok(response.toString()).build();
    }

    /**
     * PUT method for updating or creating an instance of ListaCursoCalendarioResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}