/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.apresentacao.webservices;

import br.ufscar.dc.latosensu.gals.apresentacao.utils.Constants;
import br.ufscar.dc.latosensu.gals.negocio.entidade.CalendarioCursoDisciplinaProfessor;
import br.ufscar.dc.latosensu.gals.negocio.fachada.CalendarioCursoDisciplinaProfessorFachada;
import br.ufscar.dc.latosensu.gals.negocio.fachada.PessoaFachada;
import br.ufscar.dc.latosensu.gals.persistencia.PessoaDAO;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
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
@WebService(serviceName = "ListaCalendarioSOAP")
public class ListaCalendarioSOAP {

    /**
     * This is a sample web service operation
     */
    @EJB 
    PessoaFachada pesFachada;
    
    @EJB 
    CalendarioCursoDisciplinaProfessorFachada ccdpFachada;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    
    @WebMethod(operationName = "listaCalendario")
    public String listaCalendario(@WebParam(name = "user") String user, @WebParam(name = "pass") String pass, @WebParam(name = "idcal") int idCal) {
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
        
        return response.toString();
    }
}
