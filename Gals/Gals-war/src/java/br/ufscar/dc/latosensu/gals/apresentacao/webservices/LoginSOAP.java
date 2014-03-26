/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.apresentacao.webservices;

import br.ufscar.dc.latosensu.gals.apresentacao.utils.Constants;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Pessoa;
import br.ufscar.dc.latosensu.gals.negocio.fachada.PessoaFachada;
import br.ufscar.dc.latosensu.gals.persistencia.PessoaDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ws.rs.core.Response;
import org.primefaces.json.JSONObject;

/**
 *
 * @author junior
 */
@WebService(serviceName = "LoginSOAP")
public class LoginSOAP {

    /**
     * This is a sample web service operation
     */
    
    @EJB 
    PessoaFachada pesFachada;
    
    @WebMethod(operationName = "login")
    public String login(@WebParam(name = "user") String user, @WebParam(name = "pass") String pass) {
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
        
        return response.toString();
    }
}
