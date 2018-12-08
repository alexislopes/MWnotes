package api;

import java.sql.SQLException;

public interface UsuarioDAO {
    public String achaUsuarioPorId(String usuarioJson) throws ClassNotFoundException, SQLException;
    public String achaUsuarioPorNome(String usuarioJson) throws ClassNotFoundException, SQLException;
    public String achaUsuarioPorUsuario(String usuarioJson) throws ClassNotFoundException, SQLException;
    public boolean verificaUsuario(String usuarioJson) throws ClassNotFoundException, SQLException;
    public boolean insereUsuario(String usuarioJson);

}
