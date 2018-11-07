package com.br.note.mw.mwnotes;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class WSCliente {

    public boolean verificaUsuario(String usuario, String senha) throws IOException, XmlPullParserException {

        boolean retorno = false;

        SoapObject soap = new SoapObject("http://WebService", "verificaUsuario");
        soap.addProperty("usuario", usuario);
        soap.addProperty("senha", senha);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);

        HttpTransportSE http = new HttpTransportSE("http://192.168.15.10:8080/MVNotesWebService/services/WebService?wsdl");
        http.call("verificaUsuario", envelope);

        Object resposta = envelope.getResponse();

        if (resposta.toString().equalsIgnoreCase("true")) {
            retorno = true;
        }

        return retorno;
    }

}
