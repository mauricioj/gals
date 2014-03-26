package br.ufscar.dc.latosensu.gals.negocio.entidade;

import br.ufscar.dc.latosensu.gals.negocio.entidade.PerfilPermissoes;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-02-26T19:19:39")
@StaticMetamodel(Permissoes.class)
public class Permissoes_ { 

    public static volatile SingularAttribute<Permissoes, Integer> id;
    public static volatile CollectionAttribute<Permissoes, PerfilPermissoes> perfilPermissoesCollection;
    public static volatile SingularAttribute<Permissoes, String> descricao;

}