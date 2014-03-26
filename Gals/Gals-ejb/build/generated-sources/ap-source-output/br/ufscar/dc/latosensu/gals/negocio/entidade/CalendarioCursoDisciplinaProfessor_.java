package br.ufscar.dc.latosensu.gals.negocio.entidade;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Calendario;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Curso;
import br.ufscar.dc.latosensu.gals.negocio.entidade.CursoDisciplinaProfessor;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Disciplina;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Pessoa;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-02-26T19:19:39")
@StaticMetamodel(CalendarioCursoDisciplinaProfessor.class)
public class CalendarioCursoDisciplinaProfessor_ { 

    public static volatile SingularAttribute<CalendarioCursoDisciplinaProfessor, Date> dataAula;
    public static volatile SingularAttribute<CalendarioCursoDisciplinaProfessor, Disciplina> disciplina;
    public static volatile SingularAttribute<CalendarioCursoDisciplinaProfessor, Curso> curso;
    public static volatile SingularAttribute<CalendarioCursoDisciplinaProfessor, Calendario> calendario;
    public static volatile SingularAttribute<CalendarioCursoDisciplinaProfessor, CursoDisciplinaProfessor> cursoDisciplinaProfessor;
    public static volatile SingularAttribute<CalendarioCursoDisciplinaProfessor, Pessoa> pessoa;
    public static volatile SingularAttribute<CalendarioCursoDisciplinaProfessor, Boolean> aulaDistancia;
    public static volatile SingularAttribute<CalendarioCursoDisciplinaProfessor, String> informacoesAula;

}