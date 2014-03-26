package br.ufscar.dc.latosensu.gals.negocio.entidade;

import br.ufscar.dc.latosensu.gals.negocio.entidade.CalendarioCursoDisciplinaProfessor;
import br.ufscar.dc.latosensu.gals.negocio.entidade.CursoDisciplina;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Pessoa;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-02-26T19:19:39")
@StaticMetamodel(Curso.class)
public class Curso_ { 

    public static volatile SingularAttribute<Curso, Integer> id;
    public static volatile SingularAttribute<Curso, Date> dataFinal;
    public static volatile SingularAttribute<Curso, Pessoa> pessoa;
    public static volatile SingularAttribute<Curso, Integer> cargaHorariaTotal;
    public static volatile SingularAttribute<Curso, Integer> cargaHorariaDistancia;
    public static volatile SingularAttribute<Curso, Date> dataInicial;
    public static volatile CollectionAttribute<Curso, CursoDisciplina> cursoDisciplinaCollection;
    public static volatile ListAttribute<Curso, CalendarioCursoDisciplinaProfessor> calendarioCursoDisciplinaProfessorList;
    public static volatile SingularAttribute<Curso, String> descricao;

}