package webservices;
import java.sql.SQLException;

import api.NotaDAO;
import api.UsuarioDAO;
import core.NotaDAOMySQL;
import core.UsuarioDAOMySQL;

public class WebService implements UsuarioDAO, NotaDAO {
	
	UsuarioDAO usuarioDAO = new UsuarioDAOMySQL();
	NotaDAO notaDAO = new NotaDAOMySQL();

	@Override
	public String achaUsuarioPorId(String usuarioJson) throws ClassNotFoundException, SQLException {
		return usuarioDAO.achaUsuarioPorId(usuarioJson);
	}

	@Override
	public boolean verificaUsuario(String usuarioJson) throws ClassNotFoundException, SQLException {
		return usuarioDAO.verificaUsuario(usuarioJson);
	}

	@Override
	public boolean insereUsuario(String usuarioJson) {
		return usuarioDAO.insereUsuario(usuarioJson);
	}

	@Override
	public String achaUsuarioPorNome(String usuarioJson) throws ClassNotFoundException, SQLException {
		return usuarioDAO.achaUsuarioPorNome(usuarioJson);
	}

	@Override
	public String achaUsuarioPorUsuario(String usuarioJson) throws ClassNotFoundException, SQLException {
		return usuarioDAO.achaUsuarioPorUsuario(usuarioJson);
	}

	@Override
	public boolean insereNota(String notaJson) {
		return notaDAO.insereNota(notaJson);
	}

	@Override
	public String achaNotaPorUsuario(String notaJson) throws ClassNotFoundException, SQLException {
		return notaDAO.achaNotaPorUsuario(notaJson);
	}

	@Override
	public String achaNotaPorTitulo(String notaJson) throws ClassNotFoundException, SQLException {
		return notaDAO.achaNotaPorTitulo(notaJson);
	}

	@Override
	public String achaTodos() {
		return null;
	}

	@Override
	public boolean atualizaNota(String notaJson) {
		return notaDAO.atualizaNota(notaJson);
	}

	@Override
	public boolean verificaNota(String notaJson) throws ClassNotFoundException, SQLException {
		return notaDAO.verificaNota(notaJson);
	}

	@Override
	public boolean deletaNota(String notaJson) {
		return notaDAO.deletaNota(notaJson);
	}

	@Override
	public String achaNotaPorCodigo(String notaJson) throws ClassNotFoundException, SQLException {
		return notaDAO.achaNotaPorCodigo(notaJson);
	}
}
