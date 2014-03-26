package br.ufscar.dc.latosensu.gals.negocio.entidade;

import br.ufscar.dc.latosensu.gals.negocio.entidade.CalendarioCursoDisciplinaProfessor;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Curso;
import br.ufscar.dc.latosensu.gals.negocio.entidade.CursoDisciplina;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Disciplina;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Pessoa;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-02-26T19:19:39")
@StaticMetamodel(CursoDisciplinaProfessor.class)
public class CursoDisciplinaProfessor_ { 

    public static volatile SingularAttribute<CursoDisciplinaProfessor, Disciplina> disciplina;
    public static volatile SingularAttribute<CursoDisciplinaProfessor, Curso> curso;
    public static volatile SingularAttribute<CursoDisciplinaProfessor, Pessoa> pessoa;
    public static volatile SingularAttribute<CursoDisciplinaProfessor, CursoDisciplina> cursoDisciplina;
    public static volatile CollectionAttribute<CursoDisciplinaProfessor, CalendarioCursoDisciplinaProfessor> calendarioCursoDisciplinaProfessorCollection;

}