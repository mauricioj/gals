/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.apresentacao.conversao;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Perfil;
import java.util.HashMap;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Junior
 */
@FacesConverter("perfilConverter")
public class PerfilConverter implements Converter {

    private static HashMap<String, Perfil> map = new HashMap<String, Perfil>();
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Perfil perfil = map.get(value);
        return perfil;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Perfil perfil = (Perfil)value;
        if (perfil != null) {
            map.put(perfil.getId().toString(), perfil);
            return perfil.getId().toString();
        }
        return null;
    }
}