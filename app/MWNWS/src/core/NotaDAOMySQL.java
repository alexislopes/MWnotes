package core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import api.NotaDAO;
import banco.Conexao;
import modelo.Nota;
import modelo.Usuario;

public class NotaDAOMySQL implements NotaDAO {

	private Conexao conexao;
	private Nota nota;
	
	public NotaDAOMySQL() {
		conexao = new Conexao();
		nota = new Nota();
	}

	@Override
	public boolean insereNota(String notaJson) {
		nota = nota.fromJson(notaJson);
		
		boolean resposta = false;
		String sql = "INSERT INTO nota (titulo, conteudo, endereco) VALUES (?, ?, ?)";
		PreparedStatement stmt;
		try {
			stmt = conexao.getConexao().prepareStatement(sql);
			stmt.setString(1, nota.getTitulo());
			stmt.setString(2, nota.getConteudo());
			//stmt.setLong(3, nota.getDono());
			stmt.setString(3, nota.getEndereco());

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
	public String achaNotaPorUsuario(String notaJson) throws ClassNotFoundException, SQLException {
		nota = nota.fromJson(notaJson);
		
		Nota achado = null;
		ResultSet dados = null;
		String sql = "SELECT * FROM nota WHERE dono = ?";
		PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
		stmt.setLong(1, nota.getDono());
	
		dados = stmt.executeQuery();

		if (dados.next()) {
			achado = new Nota();
			achado.setCodigo(dados.getInt(1));
			achado.setTitulo(dados.getString(2));
			achado.setConteudo(dados.getString(3));
			achado.setDono(dados.getInt(4));
		}

		stmt.execute();
		stmt.close();
		
		String achadoToJson = achado.toJson();

		return achadoToJson;
	}

	@Override
	public String achaNotaPorTitulo(String notaJson) throws ClassNotFoundException, SQLException {
		nota = nota.fromJson(notaJson);
		
		Nota achado = null;
		ResultSet dados = null;
		String sql = "SELECT * FROM nota WHERE titulo = ?";
		PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
		stmt.setString(1, nota.getTitulo());
	
		dados = stmt.executeQuery();

		if (dados.next()) {
			achado = new Nota();
			achado.setCodigo(dados.getInt(1));
			achado.setTitulo(dados.getString(2));
			achado.setConteudo(dados.getString(3));
			achado.setDono(dados.getInt(4));
		}

		stmt.execute();
		stmt.close();
		
		String achadoToJson = achado.toJson();

		return achadoToJson;
	}

	@Override
	public String achaTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean atualizaNota(String notaJson) {
		nota = nota.fromJson(notaJson);
		
		boolean resposta = false;
		String sql = "UPDATE nota SET titulo = ?, conteudo = ? WHERE codigo = ?";
		PreparedStatement stmt;
		try {
			stmt = conexao.getConexao().prepareStatement(sql);
			stmt.setString(1, nota.getTitulo());
			stmt.setString(2, nota.getConteudo());
			stmt.setLong(3, nota.getCodigo());

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
	public boolean verificaNota(String notaJson) throws ClassNotFoundException, SQLException {
		nota = nota.fromJson(notaJson);
		
		boolean achado = false;
		ResultSet dados = null;
		String sql = "SELECT * FROM usuario WHERE codigo = ?";
		PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
		stmt.setInt(1, nota.getCodigo());
	
		dados = stmt.executeQuery();

		if (dados.next()) {
			achado = true;
		}

		stmt.execute();
		stmt.close();

		return achado;
	}

	@Override
	public boolean deletaNota(String notaJson) {
		nota = nota.fromJson(notaJson);
		
		boolean resposta = false;
		String sql = "DELETE FROM nota WHERE codigo = ?";
		PreparedStatement stmt;
		try {
			stmt = conexao.getConexao().prepareStatement(sql);
			stmt.setLong(1, nota.getCodigo());

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
	public String achaNotaPorCodigo(String notaJson) throws ClassNotFoundException, SQLException {
		nota = nota.fromJson(notaJson);
		
		Nota achado = null;
		ResultSet dados = null;
		String sql = "SELECT * FROM nota WHERE codigo = ?";
		PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
		stmt.setLong(1, nota.getCodigo());
	
		dados = stmt.executeQuery();

		if (dados.next()) {
			achado = new Nota();
			achado.setCodigo(dados.getInt(1));
			achado.setTitulo(dados.getString(2));
			achado.setConteudo(dados.getString(3));
			achado.setDono(dados.getInt(4));
		}

		stmt.execute();
		stmt.close();
		
		String achadoToJson = achado.toJson();

		return achadoToJson;
	}
}
