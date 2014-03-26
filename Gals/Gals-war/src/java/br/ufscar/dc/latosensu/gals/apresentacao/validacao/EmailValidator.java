/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.apresentacao.validacao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Matheus Rossi
 */
@FacesValidator("validateEmail")
public class EmailValidator implements Validator {

    private static final String O_EMAIL_FORNECIDO_E_INVALIDO = "O E-mail fornecido é inválido!";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!verificarEmail(value.toString())) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, O_EMAIL_FORNECIDO_E_INVALIDO, O_EMAIL_FORNECIDO_E_INVALIDO));
        }
    }

    private boolean verificarEmail(String strEmail) {
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }
}
