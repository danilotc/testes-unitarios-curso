package edu.testesunitarios.service;

import static edu.testesunitarios.utils.DataUtils.isMesmaData;
import static edu.testesunitarios.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import edu.testesunitarios.entidades.Filme;
import edu.testesunitarios.entidades.Locacao;
import edu.testesunitarios.entidades.Usuario;
import edu.testesunitarios.excecoes.FilmeSemEstoqueException;
import edu.testesunitarios.excecoes.LocacaoException;
import edu.testesunitarios.servicos.LocacaoService;

public class LocacaoServiceTest {

	@Rule
	public final ErrorCollector erro = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testeLocacao() throws Exception {
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 2, 5.0); // erro acontece se estoque = 0

		//acao
		Locacao locacao = service.alugarFilme(usuario, filme);
		
		//verificacao
		erro.checkThat(locacao.getValor(), is(equalTo(5.0)));
		erro.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		erro.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
	}
	
	/*
	 * Se uma excecao nao eh lancada o log de failure trace mostra apenas a
	 * expectativa de receber uma Exception chamada FilmeSemEstoqueException.
	 */
	@Test(expected = FilmeSemEstoqueException.class)
	public void testLocacao_filmeSemEstoque() throws Exception {
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 0, 5.0); // falha se estoque != 0

		//acao
		service.alugarFilme(usuario, filme);
	}
	
	/*
	 * Adcionando um breakpoint no service.alugarFilme(null, filme) para debugar
	 * verificamos que mesmo o estoque sendo igual a 0 este teste sera realizado
	 * com sucesso porque usuario nulo eh a primeira verificacao do servico, logo 
	 * se usuario == null a excecao sera criada, lancada e os outros fluxos nao 
	 * serao verificados.
	 */
	@Test
	public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException {
		//cenario
		LocacaoService service = new LocacaoService();
		Filme filme = new Filme("Filme 1", 1, 5.0);

		//acao
		try {
			service.alugarFilme(null, filme);
			fail();
		} catch (LocacaoException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}
	
	@Test
	public void testLocacao_filmeVazio() throws FilmeSemEstoqueException, LocacaoException {
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");

		exception.expect(Exception.class);
		exception.expectMessage("Filme vazio");
		
		//acao
		service.alugarFilme(usuario, null);
	}
}
