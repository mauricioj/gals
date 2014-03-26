package br.ufscar.dc.latosensu.gals.negocio.entidade;

import br.ufscar.dc.latosensu.gals.negocio.entidade.CalendarioCursoDisciplinaProfessor;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-02-26T19:19:39")
@StaticMetamodel(Calendario.class)
public class Calendario_ { 

    public static volatile SingularAttribute<Calendario, Integer> id;
    public static volatile SingularAttribute<Calendario, String> informacoesCalendario;
    public static volatile CollectionAttribute<Calendario, CalendarioCursoDisciplinaProfessor> calendarioCursoDisciplinaProfessorCollection;

}