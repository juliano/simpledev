package br.com.simpledev.struts2.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import br.com.simpledev.struts2.dao.LocalidadeDao;
import br.com.simpledev.struts2.dao.jdbc.JdbcLocalidadeDao;

@ParentPackage("json-default")
@Namespace(value = "/localidade")
public class LocalidadeAction {

    private String estado;

    private List<String> estados;
    private List<String> cidades;

    private LocalidadeDao dao;

    public LocalidadeAction() {
        this.dao = new JdbcLocalidadeDao();
    }

    @Action(value = "estados", results = @Result(name = "ok", location = "/localidades.jsp"))
    public String listaEstados() {
        estados = dao.listaEstados();
        return "ok";
    }

    @Action(value = "cidades", results = @Result(name = "ok", type = "json", params = {
            "excludeProperties", "estados, estado" }))
    public String listaCidades() {
        cidades = dao.listaCidades(estado);
        return "ok";
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<String> getEstados() {
        return estados;
    }

    public List<String> getCidades() {
        return cidades;
    }
}