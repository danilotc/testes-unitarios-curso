package edu.testesunitarios.servicos;

import static edu.testesunitarios.utils.DataUtils.adicionarDias;

import java.util.Date;
import java.util.List;

import edu.testesunitarios.entidades.Filme;
import edu.testesunitarios.entidades.Locacao;
import edu.testesunitarios.entidades.Usuario;
import edu.testesunitarios.excecoes.FilmeSemEstoqueException;
import edu.testesunitarios.excecoes.LocacaoException;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocacaoException {
		if (usuario == null) {
			throw new LocacaoException("Usuario vazio");
		}
		
		if (filmes == null || filmes.isEmpty()) {
			throw new LocacaoException("Filme vazio");
		}
		
		for (Filme filme : filmes) {
			if (filme.getEstoque() == 0) {
				throw new FilmeSemEstoqueException();
			}
		}
		
		Locacao locacao = new Locacao();
		locacao.setFilmes(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		
		Double valorTotal = 0d;
		for (int indice = 0; indice < filmes.size(); indice++) {
			Filme filme = filmes.get(indice);
			Double valorFilme = filme.getPrecoLocacao();
			if (indice == 2) valorFilme *= 0.75;
			if (indice == 3) valorFilme *= 0.5;
			if (indice == 4) valorFilme *= 0.25;
			if (indice == 5) valorFilme = 0d;
			valorTotal += valorFilme;
		}
		locacao.setValor(valorTotal);

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}
}