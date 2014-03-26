/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.apresentacao.conversao;

import br.ufscar.dc.latosensu.gals.negocio.entidade.PerfilPermissoes;
import java.util.HashMap;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Junior
 */
@FacesConverter("perfilPermissoesConverter")
public class PerfilPermissoesConverter implements Converter {

    private static HashMap<String, PerfilPermissoes> map = new HashMap<String, PerfilPermissoes>();
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        PerfilPermissoes perfilPermissoes = map.get(value);
        return perfilPermissoes;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        /*PerfilPermissoes perfilPermissoes = (PerfilPermissoes)value;
        if (perfilPermissoes != null) {
            map.put(perfilPermissoes.getPerfilPermissoesPK() getId().toString(), perfilPermissoes);
            return perfilPermissoes.getId().toString();
        }*/
        return null;
    }
}