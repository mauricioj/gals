package br.ufscar.sigam.config;

public class Config {
	public static String URL_BASE = "http://192.168.1.98:8080/Gals-war/webresources/";
	private static String SERVICE_LOGIN = "login";
	private static String SERVICE_LISTA_CURSO_CALENDARIO = "listaCursoCalendario";
	private static String SERVICE_LISTA_CALENDARIO = "listaCalendario";
	private static String SERVICE_LISTA_HISTORICO = "listaHistorico";
	
	public static String getServiceLogin(String user, String pass) {
		return SERVICE_LOGIN + "?user=" + user + "&pass=" + pass;
	}
	public static String getServiceListaCursoCalendario(String user, String pass) {
		return SERVICE_LISTA_CURSO_CALENDARIO + "?user=" + user + "&pass=" + pass;
	}
	public static String getServiceListaCalendario(String user, String pass, int id) {
		return SERVICE_LISTA_CALENDARIO + "?user=" + user + "&pass=" + pass + "&idcal=" + id;
	}
	public static String getServiceListaHistorico(String user, String pass, int id) {
		return SERVICE_LISTA_HISTORICO + "?user=" + user + "&pass=" + pass + "&idcal=" + id;
	}
}
