package webservices;
import java.sql.SQLException;

import api.UsuarioDAO;
import core.UsuarioDAOMySQL;
import modelo.Usuario;

public class WebService implements UsuarioDAO {
	
	UsuarioDAO usuarioDAO = new UsuarioDAOMySQL();

	@Override
	public Usuario achaUsuario(Long id) throws ClassNotFoundException, SQLException {
		return usuarioDAO.achaUsuario(id);
	}

	@Override
	public boolean verificaUsuario(String  usuario, String senha) throws ClassNotFoundException, SQLException {
		return usuarioDAO.verificaUsuario(usuario, senha);
	}

	@Override
	public boolean insereUsuario(String nome, String usuario, String senha) {
		return usuarioDAO.insereUsuario(nome, usuario, senha);
	}

}
