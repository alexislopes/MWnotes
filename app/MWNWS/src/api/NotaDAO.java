package api;

import java.sql.SQLException;

public interface NotaDAO {
    public boolean insereNota(String notaJson);
    public String achaNotaPorUsuario(String notaJson) throws ClassNotFoundException, SQLException;
    public String achaNotaPorTitulo(String notaJson) throws ClassNotFoundException, SQLException;
    public String achaTodos();
    public boolean atualizaNota(String notaJson);
    public boolean verificaNota(String notaJson) throws ClassNotFoundException, SQLException;
    public boolean deletaNota(String notaJson);
    public String achaNotaPorCodigo(String notaJson) throws ClassNotFoundException, SQLException;
}