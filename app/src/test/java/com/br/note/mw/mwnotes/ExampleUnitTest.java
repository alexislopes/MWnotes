package com.br.note.mw.mwnotes;

import com.br.Modelo.Nota;
import com.br.Modelo.Usuario;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    Usuario usuario;
    Nota nota;

    @Before
    public void setUp(){
        usuario = new Usuario();
        nota = new Nota();
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void toJsonUsuarioTeste(){
        usuario.setNome("Alexis");
        usuario.setUsuario("alexis");
        usuario.setSenha("1234");
        System.out.println(usuario.toJson());
    }

    @Test
    public void fromJsonUsuarioTeste(){
        String usuarioJson = "{'id':3,'nome':'Alexis','usuario':'alexis','senha':'1234'}";
        Usuario iai = usuario.fromJson(usuarioJson);
        System.out.println(usuario.fromJson(usuarioJson));
        System.out.println(usuario.fromJson(iai.toJson()).getUsuario());
        System.out.println(usuario.fromJson(iai.toJson()).getSenha());
        System.out.println(usuario.fromJson(iai.toJson()).getNome());
        System.out.println(usuario.fromJson(iai.toJson()).getId());
    }

    @Test
    public void toJsonNotaTeste(){
        nota.setTitulo("Teste");
        nota.setConteudo("Isso é um teste!");
        System.out.println(nota.toJson());
    }
}
