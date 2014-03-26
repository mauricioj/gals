/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.apresentacao.managedbean;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Perfil;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Pessoa;
import br.ufscar.dc.latosensu.gals.negocio.fachada.PerfilFachada;
import br.ufscar.dc.latosensu.gals.negocio.fachada.PessoaFachada;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author phnicezio
 */
@ManagedBean
@SessionScoped
public class SessionManagedBean implements Serializable {

    @EJB
    private PerfilFachada perfilFachada;
    @EJB
    private PessoaFachada pessoaFachada;
    
    private Pessoa pessoa;
    private boolean logado;    
    private String userName;
    private String password;
    private Perfil perfilUsuario;
    private String idPerfilUsuario;
    private String nomeOrUsername;
    private MenuModel modeloMenu; //modelo do menu que varia de acordo com o nível de acesso 

    /**
     * Creates a new instance of SessionManagedBean
     */
    public SessionManagedBean() {
        limpar();
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public Perfil getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(Perfil perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    public String getIdPerfilUsuario() {
        return idPerfilUsuario;
    }

    public void setIdPerfilUsuario(String idPerfilUsuario) {
        this.idPerfilUsuario = idPerfilUsuario;
    }

    public MenuModel getModeloMenu() {
        return modeloMenu;
    }

    public String getNomeOrUsername() {
        return nomeOrUsername;
    }

    public void setNomeOrUsername(String nomeOrUsername) {
        this.nomeOrUsername = nomeOrUsername;
    }

    public void limpar() {
        pessoa = new Pessoa();
        perfilUsuario = new Perfil();
        logado = false;
        idPerfilUsuario = "";
    }

    public void validarLogin() throws IOException {
        logado = pessoaFachada.validarUsuario(userName, password); //valida o usuario de acordo com o CPF(usuario) e senha
        if (logado) { //indica que o usuario foi validado realizado com sucesso
            setPessoa(pessoaFachada.consultarPessoaPorCpf(userName));
            nomeOrUsername = getPrimeiroNome();
            
            if (!this.pessoa.getPerfilCollection().isEmpty()) {
                if (this.getPessoa().getPerfilCollection().size() == 1) {
                    for (Perfil perfil : this.getPessoa().getPerfilCollection()) {
                        perfilUsuario = perfil;
                    }
                    FacesContext.getCurrentInstance().getExternalContext().redirect("principal.jsf");
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Perfil de usuário: ", "você deve escolher um perfil para acesso ao sistema."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no usuário: ", "você não tem perfil adicionado ao seu usuário. Por favor, entre em contato com a secretaria."));
                limpar();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no login: ", "usuário ou senha incorretos!"));
            limpar();
        }
    }

    public void ativarPerfil() throws IOException {
        if (idPerfilUsuario != null && (!idPerfilUsuario.equals(""))) {
            perfilUsuario = perfilFachada.getPerfil(Integer.parseInt(idPerfilUsuario));
            FacesContext.getCurrentInstance().getExternalContext().redirect("principal.jsf");
        }
    }

    public void fazerLogout() throws Exception {
        limpar();
        FacesContext.getCurrentInstance().getExternalContext().redirect(
                FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
    }
    
    /**
     * Método que retorna somente o primeiro nome.
     * @return 
     */
    public String getPrimeiroNome() {
        String nome = getPessoa().getNome();
        if (nome.contains(" ")) { //se houver mais de um nome
            nome = nome.substring(0, nome.indexOf(" ")); //recupera apenas o primeiro nome
        }
        return nome;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the pessoa
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
