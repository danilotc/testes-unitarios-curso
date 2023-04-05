package edu.testesunitarios.service;

import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import edu.testesunitarios.entidades.Filme;
import edu.testesunitarios.entidades.Locacao;
import edu.testesunitarios.entidades.Usuario;
import edu.testesunitarios.excecoes.FilmeSemEstoqueException;
import edu.testesunitarios.excecoes.LocacaoException;
import edu.testesunitarios.servicos.LocacaoService;

//Estudar mais sobre DDT (Data Driven Test)

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTest {
	
	private LocacaoService service;
	
	@Parameter
	public List<Filme> filmes;
	
	@Parameter(value = 1)
	public Double valorLocacao;
	
	@Parameter(value = 2)
	public String cenario;
	
	@Before
	public void iniciarInstanciaLocacaoService_paraTodosTestes() {
		service = new LocacaoService();
	}
	
	private static Filme filme1 = new Filme("Filme 1", 2, 4.0);
	private static Filme filme2 = new Filme("Filme 2", 2, 4.0);
	private static Filme filme3 = new Filme("Filme 3", 2, 4.0);
	private static Filme filme4 = new Filme("Filme 4", 2, 4.0);
	private static Filme filme5 = new Filme("Filme 5", 2, 4.0);
	private static Filme filme6 = new Filme("Filme 6", 2, 4.0);
	
	@Parameters(name="{2}")
	public static Collection<Object[]> getParametros() {
		return Arrays.asList(new Object[][] {
			{Arrays.asList(filme1, filme2, filme3), 11.0, "3o Filme: 25%"},
			{Arrays.asList(filme1, filme2, filme3, filme4), 13.0, "4o Filme: 50%"},
			{Arrays.asList(filme1, filme2, filme3, filme4, filme5), 14.0, "5o Filme: 75%"},
			{Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 14.0, "6o Filme: 100%"},
		});
	}
	
	@Test
	public void deveCalcularValorLocacaoConsiderandoDescontos() 
			throws FilmeSemEstoqueException, LocacaoException {
		//cenario
		Usuario usuario = new Usuario("Usuario 1");
		
		//acao
		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		//verificacao
		Assert.assertThat(resultado.getValor(), is(valorLocacao));
	}
}
