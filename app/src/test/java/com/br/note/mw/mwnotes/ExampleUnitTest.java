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
        usuario.setNome("Alexis");
        usuario.setUsuario("alexis");
        usuario.setSenha("1234");
        System.out.println(usuario.fromJson(usuario.toJson()).getNome());
        System.out.println(usuario.fromJson(usuario.toJson()).getUsuario());
        System.out.println(usuario.fromJson(usuario.toJson()).getSenha());
    }

    @Test
    public void toJsonNotaTeste(){
        nota.setTitulo("Teste");
        nota.setConteudo("Isso é um teste!");
        System.out.println(nota.toJson());
    }
}
