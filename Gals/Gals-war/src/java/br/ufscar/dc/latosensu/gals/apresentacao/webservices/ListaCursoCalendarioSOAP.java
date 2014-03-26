/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.apresentacao.webservices;

import br.ufscar.dc.latosensu.gals.apresentacao.utils.Constants;
import br.ufscar.dc.latosensu.gals.negocio.entidade.CalendarioCursoDisciplinaProfessor;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Curso;
import br.ufscar.dc.latosensu.gals.negocio.fachada.CalendarioCursoDisciplinaProfessorFachada;
import br.ufscar.dc.latosensu.gals.negocio.fachada.PessoaFachada;
import br.ufscar.dc.latosensu.gals.persistencia.PessoaDAO;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ws.rs.core.Response;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

/**
 *
 * @author junior
 */
@WebService(serviceName = "ListaCursoCalendarioSOAP")
public class ListaCursoCalendarioSOAP {

    /**
     * This is a sample web service operation
     */
    @EJB 
    PessoaFachada pesFachada;
        
    @EJB 
    CalendarioCursoDisciplinaProfessorFachada ccdpFachada;
    
    @WebMethod(operationName = "listaCursoCalendario")
    public String listaCursoCalendario(@WebParam(name = "user") String user, @WebParam(name = "pass") String pass) {
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
        
        return response.toString();
    }
}
