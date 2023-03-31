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
	 * Se uma excecao eh lancada e este teste funcionar o log de failure trace 
	 * nao mostra nenhuma informacao util de rastreamento alem da exceptativa 
	 * de receber uma Exception.
	 */
	@Test(expected = Exception.class)
	public void testLocacao_filmeSemEstoque() throws Exception {
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 0, 5.0);

		//acao
		service.alugarFilme(usuario, filme);
	}
	
	/*
	 * Se uma excecao eh lancada e este teste funcionar o log de failure trace
	 * gera uma falha, mostra o texto definido no metodo fail e rastreia apenas
	 * o local onde este metodo foi inserido.
	 * 
	 * Para rastrear o problema sera necessario executar com debug.
	 */
	@Test
	public void testLocacao_filmeSemEstoque_comTryCatch() {
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 0, 5.0);

		//acao
		try {
			service.alugarFilme(usuario, filme);
			fail("Deveria ter lancado uma excecao"); // garante o sucesso do teste
		} catch (Exception e) {
			assertThat(e.getMessage(), is("Filme sem estoque"));
		}
	}
	
	/*
	 * A Rule precisa ser inserida antes de chamar o metodo que pode
	 * criar a excecao. Do contratio, sera gerado um erro com rastreio 
	 * de dois pontos e uma mensagem:
	 * 
	 *   1. O teste que chamou o metodo que gerou a exception.
	 *   2. O metodo que gerou a exception.
	 *   3. A  mensagem definida na criacao da exception.
	 */
	@Test
	public void testLocacao_filmeSemEstoque_comRule() throws Exception {
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 0, 5.0);

		exception.expect(Exception.class);
		exception.expectMessage("Filme sem estoque");
		
		//acao
		service.alugarFilme(usuario, filme);
	}
}
