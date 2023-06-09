package edu.testesunitarios.service;

import static edu.testesunitarios.builders.FilmeBuilder.umFilme;
import static edu.testesunitarios.builders.UsuarioBuilder.umUsuario;
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
import org.mockito.Mockito;

import edu.testesunitarios.dao.LocacaoDAO;
import edu.testesunitarios.entidades.Filme;
import edu.testesunitarios.entidades.Locacao;
import edu.testesunitarios.entidades.Usuario;
import edu.testesunitarios.excecoes.FilmeSemEstoqueException;
import edu.testesunitarios.excecoes.LocacaoException;
import edu.testesunitarios.servicos.LocacaoService;
import edu.testesunitarios.servicos.SpcService;

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
		LocacaoDAO dao = Mockito.mock(LocacaoDAO.class);
		service.setLocacaoDAO(dao);
		SpcService spc = Mockito.mock(SpcService.class);
		service.setSpcService(spc);
	}
	
	private static Filme filme1 = umFilme().comValor(4.0).agora();
	private static Filme filme2 = umFilme().comValor(4.0).agora();
	private static Filme filme3 = umFilme().comValor(4.0).agora();
	private static Filme filme4 = umFilme().comValor(4.0).agora();
	private static Filme filme5 = umFilme().comValor(4.0).agora();
	private static Filme filme6 = umFilme().comValor(4.0).agora();
	
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
		Usuario usuario = umUsuario().agora();
		
		//acao
		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		//verificacao
		Assert.assertThat(resultado.getValor(), is(valorLocacao));
	}
}
