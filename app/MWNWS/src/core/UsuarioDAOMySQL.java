package core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import api.UsuarioDAO;
import banco.Conexao;
import modelo.Usuario;

public class UsuarioDAOMySQL implements UsuarioDAO{
	
	Conexao conexao;
	Usuario usuario;
	
	public UsuarioDAOMySQL() {
		conexao = new Conexao();
		usuario = new Usuario();
	}
		
	@Override
	public String achaUsuarioPorId(String usuarioJson) throws ClassNotFoundException, SQLException {
		
		usuario = usuario.fromJson(usuarioJson);
		
		Usuario achado = null;
		ResultSet dados = null;
		String sql = "SELECT * FROM usuario WHERE codigo = ?";
		PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
		stmt.setLong(1, usuario.getId());
	
		dados = stmt.executeQuery();

		if (dados.next()) {
			achado = new Usuario();
			achado.setId(dados.getLong(1));
			achado.setNome(dados.getString(2));
			achado.setUsuario(dados.getString(3));
			achado.setSenha(dados.getString(4));
		}

		stmt.execute();
		stmt.close();
		
		String achadoToJson = achado.toJson();

		return achadoToJson;
	}

	@Override
	public String achaUsuarioPorNome(String usuarioJson) throws ClassNotFoundException, SQLException {
		usuario = usuario.fromJson(usuarioJson);
		
		Usuario achado = null;
		ResultSet dados = null;
		String sql = "SELECT * FROM usuario WHERE nome = ?";
		PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
		stmt.setString(1, usuario.getNome());
	
		dados = stmt.executeQuery();

		if (dados.next()) {
			achado = new Usuario();
			achado.setId(dados.getLong(1));
			achado.setNome(dados.getString(2));
			achado.setUsuario(dados.getString(3));
			achado.setSenha(dados.getString(4));
		}

		stmt.execute();
		stmt.close();
		
		String achadoToJson = achado.toJson();

		return achadoToJson;
	}

	@Override
	public boolean verificaUsuario(String usuarioJson) throws ClassNotFoundException, SQLException {
		
		usuario = usuario.fromJson(usuarioJson);
		
		boolean achado = false;
		ResultSet dados = null;
		String sql = "SELECT * FROM usuario WHERE usuario = ? AND senha = ?";
		PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
		stmt.setString(1, usuario.getUsuario());
		stmt.setString(2, usuario.getSenha());
	
		dados = stmt.executeQuery();

		if (dados.next()) {
			achado = true;
		}

		stmt.execute();
		stmt.close();

		return achado;
	}

	@Override
	public boolean insereUsuario(String usuarioJson) {
		
		usuario = usuario.fromJson(usuarioJson);
		
		boolean resposta = false;
		String sql = "INSERT INTO usuario (nome, usuario, senha) VALUES (?, ?, ?)";
		PreparedStatement stmt;
		try {
			stmt = conexao.getConexao().prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getUsuario());
			stmt.setString(3, usuario.getSenha());

			stmt.execute();
			stmt.close();
			
			resposta = true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return resposta;
	}

	@Override
	public String achaUsuarioPorUsuario(String usuarioJson) throws ClassNotFoundException, SQLException {
		usuario = usuario.fromJson(usuarioJson);
		
		Usuario achado = null;
		ResultSet dados = null;
		String sql = "SELECT * FROM usuario WHERE usuario = ?";
		PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
		stmt.setString(1, usuario.getUsuario());
	
		dados = stmt.executeQuery();

		if (dados.next()) {
			achado = new Usuario();
			achado.setId(dados.getLong(1));
			achado.setNome(dados.getString(2));
			achado.setUsuario(dados.getString(3));
			achado.setSenha(dados.getString(4));
		}

		stmt.execute();
		stmt.close();
		
		String achadoToJson = achado.toJson();

		return achadoToJson;
	}
	
	/*public static void main(String[] args) throws ClassNotFoundException, SQLException {
	UsuarioDAOMySQL udao = new UsuarioDAOMySQL();
	Usuario usuario = new Usuario("Booya", "booy", "1234");
	
	//udao.insereUsuario(usuario);
	//System.out.println(udao.achaUsuario(1L).toString());
	boolean esta = udao.verificaUsuario(usuario.getUsuario(), usuario.getSenha());
	if(esta) {
		System.out.println("esta!");
	} else {
		System.out.println("não está");
	}
}*/

}
