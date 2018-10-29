package com.br.note.mw.mwnotes.com.br.mw.DAO.API;

import com.br.note.mw.mwnotes.com.br.mw.Modelo.Nota;

import java.sql.SQLException;
import java.util.List;

public interface NotaDAO {
    public boolean insereNota(Nota nota) throws SQLException;
    public Nota achaNotaPorUsuario(String usuario);
    public Nota achaNotaPorTag(String tag);
    public List<Nota> achaTodos();
    public boolean atualizaNota(int codigo, String tag, String nota, int usuario);
    public boolean verificaNota(Nota nota);
    public boolean deletaNota(String tag);
    public Nota achaNotaPorCodigo(int codigo);
}
