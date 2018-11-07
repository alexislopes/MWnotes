package com.br.note.mw.mwnotes.com.br.mw.DAO.CORE;

import com.br.note.mw.mwnotes.com.br.mw.DAO.API.NotaDAO;
import com.br.note.mw.mwnotes.com.br.mw.Modelo.Nota;

import java.sql.SQLException;
import java.util.List;

public class NotaDAOCouchbase implements NotaDAO {
    @Override
    public boolean insereNota(Nota nota) throws SQLException {
        return false;
    }

    @Override
    public Nota achaNotaPorUsuario(String usuario) {
        return null;
    }

    @Override
    public Nota achaNotaPorTag(String tag) {
        return null;
    }

    @Override
    public List<Nota> achaTodos() {
        return null;
    }

    @Override
    public boolean atualizaNota(int codigo, String tag, String nota, int usuario) {
        return false;
    }

    @Override
    public boolean verificaNota(Nota nota) {
        return false;
    }

    @Override
    public boolean deletaNota(String tag) {
        return false;
    }

    @Override
    public Nota achaNotaPorCodigo(int codigo) {
        return null;
    }
}
