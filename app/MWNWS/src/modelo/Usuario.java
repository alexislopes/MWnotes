package modelo;

import com.google.gson.Gson;

public class Usuario {
	private Long id;
	private String nome;
	private String usuario;
    private String senha;

    public Usuario(){}

    public Usuario(String nome, String usuario, String senha){
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String toJson(){
        Gson json = new Gson();
        return json.toJson(this);
    }

    public Usuario fromJson(String usuarioJson){

        Gson json = new Gson();
        Usuario novo = new Usuario();

        novo = json.fromJson(usuarioJson, Usuario.class);

        return  novo;
    }
	
}
