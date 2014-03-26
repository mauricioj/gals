package br.ufscar.dc.latosensu.gals.negocio.entidade;

import br.ufscar.dc.latosensu.gals.negocio.entidade.PerfilPermissoes;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Pessoa;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-02-26T19:19:39")
@StaticMetamodel(Perfil.class)
public class Perfil_ { 

    public static volatile SingularAttribute<Perfil, Integer> id;
    public static volatile CollectionAttribute<Perfil, Pessoa> pessoaCollection;
    public static volatile CollectionAttribute<Perfil, PerfilPermissoes> perfilPermissoesCollection;
    public static volatile SingularAttribute<Perfil, String> descricao;

}