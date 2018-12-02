package com.br.DAO.API;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.sql.SQLException;

public interface NotaDAO {
    public boolean insereNota(String notaJson) throws SQLException, IOException, XmlPullParserException;
    public String achaNotaPorUsuario(String notaJson) throws IOException, XmlPullParserException;
    public String achaNotaPorTitulo(String notaJson) throws IOException, XmlPullParserException;
    public String achaTodos();
    public boolean atualizaNota(String notaJson) throws IOException, XmlPullParserException;
    public boolean verificaNota(String notaJson) throws IOException, XmlPullParserException;
    public boolean deletaNota(String notaJson) throws IOException, XmlPullParserException;
    public String achaNotaPorCodigo(String notaJson) throws IOException, XmlPullParserException;
}
