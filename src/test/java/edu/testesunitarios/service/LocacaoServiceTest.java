package edu.testesunitarios.service;

import static edu.testesunitarios.metchers.MatchersProprios.caiNumaSegunda;
import static edu.testesunitarios.metchers.MatchersProprios.ehHoje;
import static edu.testesunitarios.metchers.MatchersProprios.ehHojeComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assume;
import org.junit.Before;
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
import edu.testesunitarios.utils.DataUtils;

public class LocacaoServiceTest {
	
	private LocacaoService service;

	@Rule
	public final ErrorCollector erro = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void iniciarInstanciaLocacaoService_paraTodosTestes() {
		service = new LocacaoService();
	}
	
	@Test
	public void deveAlugarFilme() throws Exception {
		Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		
		//cenario
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0)); // erro acontece se estoque = 0

		//acao
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		//verificacao
		erro.checkThat(locacao.getValor(), is(equalTo(5.0)));
		erro.checkThat(locacao.getDataLocacao(), ehHoje());
		erro.checkThat(locacao.getDataRetorno(), ehHojeComDiferencaDias(1));
	}
	
	/*
	 * Se uma excecao nao eh lancada o log de failure trace mostra apenas a
	 * expectativa de receber uma Exception chamada FilmeSemEstoqueException.
	 */
	@Test(expected = FilmeSemEstoqueException.class)
	public void naoDeveAlugarFilmeSemEstoque() throws Exception {
		//cenario
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 5.0)); // falha se estoque != 0

		//acao
		service.alugarFilme(usuario, filmes);
	}
	
	/*
	 * Adcionando um breakpoint no service.alugarFilme(null, filme) para debugar
	 * verificamos que mesmo o estoque sendo igual a 0 este teste sera realizado
	 * com sucesso porque usuario nulo eh a primeira verificacao do servico, logo 
	 * se usuario == null a excecao sera criada, lancada e os outros fluxos nao 
	 * serao verificados.
	 */
	@Test
	public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException {
		//cenario
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));

		//acao
		try {
			service.alugarFilme(null, filmes);
			fail();
		} catch (LocacaoException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}
	
	@Test
	public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocacaoException {
		//cenario
		Usuario usuario = new Usuario("Usuario 1");

		exception.expect(LocacaoException.class);
		exception.expectMessage("Filme vazio");
		
		//acao
		service.alugarFilme(usuario, null);
	}
	
	@Test
	public void deveDevolverNaSegundaAoAlugarNoSabado() throws FilmeSemEstoqueException, LocacaoException {
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		
		//cenario
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));
		
		//acao
		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		//verificacao
//		assertThat(resultado.getDataRetorno(), new DiaSemanaMatcher(Calendar.MONDAY));
//		assertThat(resultado.getDataRetorno(), caiEm(Calendar.MONDAY));
		assertThat(resultado.getDataRetorno(), caiNumaSegunda());
	}
}
