package com.br.note.mw.mwnotes;

import com.br.note.mw.mwnotes.com.br.mw.DAO.API.UsuarioDAO;
import com.br.note.mw.mwnotes.com.br.mw.Modelo.Usuario;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class WSClienteUsuario implements UsuarioDAO {

    private SoapObject soap;
    private SoapSerializationEnvelope envelope;
    private HttpTransportSE http;
    private Object resposta;

    public WSClienteUsuario(){
        envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        http = new HttpTransportSE("http://192.168.15.10:8080/MWNotesWebService/services/WebService?wsdl");
    }


    @Override
    public String achaUsuarioPorId(String usuarioJson) throws IOException, XmlPullParserException {
        String retorno;

        soap = new SoapObject("http://WebService", "achaUsuarioPorId");
        soap.addProperty("usuarioJson", usuarioJson);

        envelope.setOutputSoapObject(soap);

        http.call("achaUsuarioPorId", envelope);

        resposta = envelope.getResponse();
        retorno = resposta.toString();

        return retorno;
    }

    @Override
    public String achaUsuarioPorNome(String usuarioJson) throws IOException, XmlPullParserException {
        String retorno;

        soap = new SoapObject("http://WebService", "achaUsuarioPorNome");
        soap.addProperty("usuarioJson", usuarioJson);

        envelope.setOutputSoapObject(soap);

        http.call("achaUsuarioPorNome", envelope);

        resposta = envelope.getResponse();
        retorno = resposta.toString();

        return retorno;
    }


    @Override
    public String achaUsuarioPorUsuario(String usuarioJson) throws IOException, XmlPullParserException {
        String retorno;

        soap = new SoapObject("http://WebService", "achaUsuarioPorUsuario");
        soap.addProperty("usuarioJson", usuarioJson);

        envelope.setOutputSoapObject(soap);

        http.call("achaUsuarioPorUsuario", envelope);

        resposta = envelope.getResponse();
        retorno = resposta.toString();

        return retorno;
    }

    @Override
    public boolean verificaUsuario(String usuarioJson) throws IOException, XmlPullParserException {

        boolean retorno = false;

        soap = new SoapObject("http://WebService", "verificaUsuario");
        soap.addProperty("usuarioJson", usuarioJson);

        envelope.setOutputSoapObject(soap);

        http.call("verificaUsuario", envelope);

        resposta = envelope.getResponse();

        if (resposta.toString().equalsIgnoreCase("true")) {
            retorno = true;
        }

        return retorno;
    }

    @Override
    public boolean insereUsuario(String usuarioJson) throws IOException, XmlPullParserException {
        boolean retorno = false;

        soap = new SoapObject("http://WebService", "insereUsuario");
        soap.addProperty(usuarioJson, usuarioJson);

        envelope.setOutputSoapObject(soap);

        http.call("insereUsuario", envelope);

        resposta = envelope.getResponse();

        if (resposta.toString().equalsIgnoreCase("true")){
            retorno = true;
        }

        return retorno;
    }

}
