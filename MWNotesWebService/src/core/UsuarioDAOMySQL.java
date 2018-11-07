package core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import api.UsuarioDAO;
import banco.Conexao;
import modelo.Usuario;

public class UsuarioDAOMySQL implements UsuarioDAO{
	
	Conexao conexao;
	
	public UsuarioDAOMySQL() {
		conexao = new Conexao();
	}

	@Override
	public Usuario achaUsuario(Long id) throws ClassNotFoundException, SQLException {
		Usuario achado = null;
		ResultSet dados = null;
		String sql = "SELECT * FROM usuario WHERE codigo = ?";
		PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
		stmt.setLong(1, id);
	
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

		return achado;
	}

	@Override
	public boolean verificaUsuario(String usuario, String senha) throws ClassNotFoundException, SQLException {
		boolean achado = false;
		ResultSet dados = null;
		String sql = "SELECT * FROM usuario WHERE usuario = ? AND senha = ?";
		PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
		stmt.setString(1, usuario);
		stmt.setString(2, senha);
	
		dados = stmt.executeQuery();

		if (dados.next()) {
			achado = true;
		}

		stmt.execute();
		stmt.close();

		return achado;
	}

	@Override
	public boolean insereUsuario(String nome, String usuario, String senha) {
		boolean resposta = false;
		String sql = "INSERT INTO usuario (nome, usuario, senha) VALUES (?, ?, ?)";
		PreparedStatement stmt;
		try {
			stmt = conexao.getConexao().prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setString(2, usuario);
			stmt.setString(3, senha);

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
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
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
	}

}
