package modelo;

import java.util.List;

public class Nota {
	private int codigo;
    private String tag;
    private String nota;
    private int usuario;
    private List<Usuario> colaboradores;

    public Nota(){}

    public Nota(int codigo, String tag, String nota, int usuario){
        this.codigo = codigo;
        this.tag = tag;
        this.nota = nota;
        this.usuario = usuario;
    }

    public Nota(int codigo, String tag, String nota){
        this.codigo = codigo;
        this.tag = tag;
        this.nota = nota;
    }

    public Nota(String tag, String nota) {
        this.tag = tag;
        this.nota = nota;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public List<Usuario> getColaboradores() {
    	return colaboradores;
    }
    
    public void setColaboradores(List<Usuario> colaboradores) {
    	this.colaboradores = colaboradores;
    }
}
