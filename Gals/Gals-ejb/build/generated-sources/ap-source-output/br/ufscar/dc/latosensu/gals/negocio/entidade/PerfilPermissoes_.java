package br.ufscar.dc.latosensu.gals.negocio.entidade;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Perfil;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Permissoes;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-02-26T19:19:39")
@StaticMetamodel(PerfilPermissoes.class)
public class PerfilPermissoes_ { 

    public static volatile SingularAttribute<PerfilPermissoes, Boolean> exclui;
    public static volatile SingularAttribute<PerfilPermissoes, Permissoes> permissoes;
    public static volatile SingularAttribute<PerfilPermissoes, Perfil> perfil;
    public static volatile SingularAttribute<PerfilPermissoes, Boolean> permite;
    public static volatile SingularAttribute<PerfilPermissoes, Boolean> edita;
    public static volatile SingularAttribute<PerfilPermissoes, Boolean> inclui;

}