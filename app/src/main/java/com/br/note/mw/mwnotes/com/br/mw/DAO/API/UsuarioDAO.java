package com.br.note.mw.mwnotes.com.br.mw.DAO.API;

import com.br.note.mw.mwnotes.com.br.mw.Modelo.Usuario;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public interface UsuarioDAO {
    public String achaUsuarioPorId(String usuarioJson) throws IOException, XmlPullParserException;
    public String achaUsuarioPorNome(String usuarioJson) throws IOException, XmlPullParserException;
    public String achaUsuarioPorUsuario(String usuarioJson) throws IOException, XmlPullParserException;
    public boolean verificaUsuario(String usuarioJson) throws IOException, XmlPullParserException;
    public boolean insereUsuario(String usuarioJson) throws IOException, XmlPullParserException;

}
