package api;

import java.sql.SQLException;

import modelo.Usuario;

public interface UsuarioDAO {
	 public Usuario achaUsuario(Long id) throws ClassNotFoundException, SQLException;
	 public boolean verificaUsuario(String usuario, String senha) throws ClassNotFoundException, SQLException;
	 public boolean insereUsuario(String nome, String usuario, String senha);
}
