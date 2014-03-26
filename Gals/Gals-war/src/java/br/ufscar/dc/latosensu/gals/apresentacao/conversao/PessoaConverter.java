/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.apresentacao.conversao;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Pessoa;
import java.util.HashMap;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Junior
 */
@FacesConverter("pessoaConverter")
public class PessoaConverter implements Converter {

    private static HashMap<String, Pessoa> map = new HashMap<String, Pessoa>();
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Pessoa pessoa = map.get(value);
        return pessoa;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Pessoa pessoa = (Pessoa)value;
        if (pessoa != null) {
            map.put(pessoa.getId().toString(), pessoa);
            return pessoa.getId().toString();
        }
        return null;
    }
}