/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.apresentacao.conversao;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Curso;
import java.util.HashMap;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Matheus
 */
@FacesConverter("cursoConverter")
public class CursoConverter implements Converter {

    private static HashMap<String, Curso> map = new HashMap<String, Curso>();
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Curso curso = map.get(value);
        return curso;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Curso curso = (Curso)value;
        if (curso != null) {
            map.put(curso.getId().toString(), curso);
            return curso.getId().toString();
        }
        return null;
    }
}