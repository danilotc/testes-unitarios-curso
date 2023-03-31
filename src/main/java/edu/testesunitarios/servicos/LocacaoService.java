package edu.testesunitarios.servicos;

import static edu.testesunitarios.utils.DataUtils.adicionarDias;

import java.util.Date;

import edu.testesunitarios.entidades.Filme;
import edu.testesunitarios.entidades.Locacao;
import edu.testesunitarios.entidades.Usuario;
import edu.testesunitarios.excecoes.FilmeSemEstoqueException;
import edu.testesunitarios.excecoes.LocacaoException;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, Filme filme) throws FilmeSemEstoqueException, LocacaoException {
		if (usuario == null) {
			throw new LocacaoException("Usuario vazio");
		}
		
		if (filme == null) {
			throw new LocacaoException("Filme vazio");
		}
		
		if (filme.getEstoque() == 0) {
			throw new FilmeSemEstoqueException();
		}
		
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}
}