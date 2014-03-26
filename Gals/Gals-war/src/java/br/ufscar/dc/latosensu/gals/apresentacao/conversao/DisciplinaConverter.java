/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.apresentacao.conversao;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Disciplina;
import java.util.HashMap;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Junior
 */
@FacesConverter("disciplinaConverter")
public class DisciplinaConverter implements Converter {

    private static HashMap<String, Disciplina> map = new HashMap<String, Disciplina>();
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Disciplina disciplina = map.get(value);
        return disciplina;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Disciplina disciplina = (Disciplina)value;
        if (disciplina != null) {
            map.put(disciplina.getId().toString(), disciplina);
            return disciplina.getId().toString();
        }
        return null;
    }
}