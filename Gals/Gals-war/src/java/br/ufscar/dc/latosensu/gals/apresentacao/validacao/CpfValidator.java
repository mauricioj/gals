/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.apresentacao.validacao;

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
@FacesValidator("validateCpf")
public class CpfValidator implements Validator {

    private static final String O_CPF_FORNECIDO_E_INVALIDO = "O CPF fornecido é inválido!";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!validarCPF(value.toString())) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, O_CPF_FORNECIDO_E_INVALIDO, O_CPF_FORNECIDO_E_INVALIDO));
        }
    }
    
    /**
     * Valida CPF do usuário. Não aceita CPF's padrões como 11111111111 ou
     * 22222222222
     *
     * @param cpf String valor com 11 dígitos
     * @return 
     */
    public boolean validarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || isCPFPadrao(cpf)) {
            return false;
        }
        
        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) { // CPF não possui somente números
            return false;
        }
        
        if (!calcDigVerif(cpf.substring(0, 9)).equals(cpf.substring(9, 11))) {
            return false;
        }
        
        return true;
    }

    /**
     *
     * @param cpf String valor a ser testado
     * @return boolean indicando se o usuário entrou com um CPF padrão
     */
    private boolean isCPFPadrao(String cpf) {
        if (cpf.equals("00000000000")
                || cpf.equals("11111111111")
                || cpf.equals("22222222222")
                || cpf.equals("33333333333")
                || cpf.equals("44444444444")
                || cpf.equals("55555555555")
                || cpf.equals("66666666666")
                || cpf.equals("77777777777")
                || cpf.equals("88888888888")
                || cpf.equals("99999999999")) {            
            return true;
        }
        
        return false;
    }
    
    private String calcDigVerif(String num) {
        Integer primDig, segDig;
        int soma = 0, peso = 10;
        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }
        
        if (soma % 11 == 0 | soma % 11 == 1) {
            primDig = new Integer(0);
        } else {
            primDig = new Integer(11 - (soma % 11));
        }
        
        soma = 0;
        peso = 11;
        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }
        
        soma += primDig.intValue() * 2;
        if (soma % 11 == 0 | soma % 11 == 1) {
            segDig = new Integer(0);
        } else {
            segDig = new Integer(11 - (soma % 11));
        }
        
        return primDig.toString() + segDig.toString();
    }
}