package br.ufscar.dc.latosensu.gals.negocio.entidade;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Curso;
import br.ufscar.dc.latosensu.gals.negocio.entidade.CursoDisciplina;
import br.ufscar.dc.latosensu.gals.negocio.entidade.CursoDisciplinaProfessor;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Perfil;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-02-26T19:19:39")
@StaticMetamodel(Pessoa.class)
public class Pessoa_ { 

    public static volatile CollectionAttribute<Pessoa, Curso> cursoCollection;
    public static volatile CollectionAttribute<Pessoa, CursoDisciplina> cursoDisciplinaCollection;
    public static volatile SingularAttribute<Pessoa, Boolean> tipoPessoaAluno;
    public static volatile SingularAttribute<Pessoa, String> password;
    public static volatile SingularAttribute<Pessoa, String> matricula;
    public static volatile SingularAttribute<Pessoa, Integer> id;
    public static volatile CollectionAttribute<Pessoa, CursoDisciplinaProfessor> cursoDisciplinaProfessorCollection;
    public static volatile CollectionAttribute<Pessoa, Perfil> perfilCollection;
    public static volatile SingularAttribute<Pessoa, Boolean> tipoPessoaProfessor;
    public static volatile SingularAttribute<Pessoa, String> eMail;
    public static volatile SingularAttribute<Pessoa, String> userName;
    public static volatile SingularAttribute<Pessoa, Boolean> tipoPessoaCoordenador;
    public static volatile SingularAttribute<Pessoa, String> cpf;
    public static volatile SingularAttribute<Pessoa, String> nome;

}