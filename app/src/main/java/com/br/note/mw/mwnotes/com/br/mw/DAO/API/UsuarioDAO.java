package com.br.note.mw.mwnotes.com.br.mw.DAO.API;

import com.br.note.mw.mwnotes.com.br.mw.Modelo.Usuario;

public interface UsuarioDAO {
    public Usuario achaUsuarioPorId(Long id);
    public Usuario achaUsuarioPorNome(String nome);
    public boolean verificaUsuario(Usuario usuario);

}
