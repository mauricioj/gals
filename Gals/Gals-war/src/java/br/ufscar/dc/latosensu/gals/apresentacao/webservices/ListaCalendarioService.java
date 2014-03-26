/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.apresentacao.webservices;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Calendario;
import br.ufscar.dc.latosensu.gals.negocio.entidade.CalendarioCursoDisciplinaProfessor;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Pessoa;
import br.ufscar.dc.latosensu.gals.negocio.fachada.CalendarioCursoDisciplinaProfessorFachada;
import br.ufscar.dc.latosensu.gals.negocio.fachada.PessoaFachada;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;
import br.ufscar.dc.latosensu.gals.apresentacao.utils.Constants;
import br.ufscar.dc.latosensu.gals.persistencia.PessoaDAO;

/**
 * REST Web Service
 *
 * @author Mauricio Jose De Grandi Junior
 */
@Stateless
@LocalBean
@Path("listaCalendario")
@Consumes({"application/x-www-form-urlencoded; charset=utf-8"})
@Produces({"application/json ;charset=utf-8"})
public class ListaCalendarioService {

    @Context
    private UriInfo context;

    @EJB 
    PessoaFachada pesFachada;
    
    @EJB 
    CalendarioCursoDisciplinaProfessorFachada ccdpFachada;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    /**
     * Creates a new instance of ListaCalendarioService
     */
    public ListaCalendarioService() {
    }

    /**
     * Retrieves representation of an instance of webservices.ListaCalendarioService
     * @return an instance of java.lang.String
     */
    @GET
    public Response getCalendario(@QueryParam("user")String user, @QueryParam("pass")String pass, @QueryParam("idcal") int idCal) {
        JSONObject response = new JSONObject();
        try {
            if ((user == null) || (pass == null) || (idCal == 0)) {
                response.put("status", Constants.HTTP_BAD_REQUEST);    
            } else {                
                if (!pesFachada.validarUsuario(user, pass)) {
                    response.put("status", Constants.HTTP_UNAUTHORIZED);
                } else {
                    response.put("status", Constants.HTTP_OK);
                    
                    Collection<CalendarioCursoDisciplinaProfessor> lista = ccdpFachada.listar(idCal);
                    JSONArray array = new JSONArray();
                    
                    for (Iterator<CalendarioCursoDisciplinaProfessor> it = lista.iterator(); it.hasNext();) {
                        CalendarioCursoDisciplinaProfessor aula = it.next();
                        
                        JSONObject calJSON = new JSONObject();
                        calJSON.put("dataAula", sdf.format(aula.getDataAula()));
                        calJSON.put("disciplina", aula.getDisciplina().getDescricao());
                        calJSON.put("professor", aula.getPessoa().getNome());
                        calJSON.put("aulaDistancia", aula.getAulaDistancia());
                        calJSON.put("informacoes", aula.getInformacoesAula());
                        
                        array.put(calJSON);
                    }
                    
                    response.put("aulas",array);
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
     * PUT method for updating or creating an instance of ListaCalendarioService
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}