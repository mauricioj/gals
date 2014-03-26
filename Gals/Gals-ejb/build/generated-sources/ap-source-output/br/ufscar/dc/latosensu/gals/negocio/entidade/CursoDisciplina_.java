package br.ufscar.dc.latosensu.gals.negocio.entidade;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Curso;
import br.ufscar.dc.latosensu.gals.negocio.entidade.CursoDisciplinaProfessor;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Disciplina;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Pessoa;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-02-26T19:19:39")
@StaticMetamodel(CursoDisciplina.class)
public class CursoDisciplina_ { 

    public static volatile CollectionAttribute<CursoDisciplina, CursoDisciplinaProfessor> cursoDisciplinaProfessorCollection;
    public static volatile SingularAttribute<CursoDisciplina, Disciplina> disciplina;
    public static volatile CollectionAttribute<CursoDisciplina, Pessoa> pessoaCollection;
    public static volatile SingularAttribute<CursoDisciplina, Curso> curso;
    public static volatile SingularAttribute<CursoDisciplina, Integer> cargaHoraria;
    public static volatile SingularAttribute<CursoDisciplina, Integer> cargaHorariaDistancia;

}