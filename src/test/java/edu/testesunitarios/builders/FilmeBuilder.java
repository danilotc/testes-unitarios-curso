package edu.testesunitarios.builders;

import edu.testesunitarios.entidades.Filme;

public class FilmeBuilder {

	private Filme filme;
	
	/* eh privado para nao ser possivel instanciar de 
	 * forma externa, sera permitido instanciar apenas
	 * dentro desta classe.*/
	private FilmeBuilder() {}
	
	public static FilmeBuilder umFilme() {
		/* Criando instancia de FilmeBuilder e Filme, filme precisa 		
		 * ser instanciado atraves da intancia de builder */
		FilmeBuilder builder = new FilmeBuilder();
		builder.filme = new Filme();
		/* Os valores da instancia de filme estao sendo setados atraves
		 * da instancia de builder. Precisa ser assim, do contrario nao
		 * sera possivel setar os valores de filme. */
		builder.filme.setEstoque(2);
		builder.filme.setNome("Filme 1");
		builder.filme.setPrecoLocacao(4.0);
		/* Retornando a intancia de builder, isso porque este metodo 
		 * esta retornando um objeto do tipo FilmeBuilder */
		return builder;
	}
	
	public static FilmeBuilder umFilmeSemEstoque() {
		/* criando instancias */
		FilmeBuilder builder = new FilmeBuilder();
		builder.filme = new Filme();
		/* setando atributos de filme, desta vez o estoque eh zero
		 * para fazer o que o nome do metodo diz */
		builder.filme.setEstoque(0);
		builder.filme.setNome("Filme 1");
		builder.filme.setPrecoLocacao(4.0);
		/* retornando objeto */
		return builder;
	}
	
	/*
	 * Devem retornar objeto do tipo FilmeBuilder, nao precisa ser
	 * estatico porque ja usam objetos instanciados, por isso esta
	 * classe eh usada para setar valores de forma encadeada.
	 * 
	 * Por ser um metodo de configuracao ele sempre dever estar
	 * entre o metodo estatico, que normalmente cria a instancia,
	 * e o metodo final, que retorna o objeto necessario.
	 * 
	 * FilmeBuilder.umFilme().semEstoque().agora()
	 * FilmeBuilder.umFilmeSemEstoque().agora()
	 * 
	 * Observe a fluidez da leitura considerando importacao estatica
	 * ------------------------------
	 * umFilme().semEstoque().agora()
	 * umFilmeSemEstoque().agora();
	 */
	public FilmeBuilder semEstoque() {
		filme.setEstoque(0);
		return this;
	}
	
	/*
	 * Use este metodo na cadeia somente quando precisar configurar
	 * um valor especifico para o estoque de um determinado teste
	 */
	public FilmeBuilder comValor(Double valor) {
		filme.setPrecoLocacao(valor);
		return this;
	}
	
	/* 
	 * Na cadeia de builder este metodo eh considerado o metodo final,
	 * aquele que retorna o que realmente precisamos depois de todas
	 * as configuracoes realizadas nos encadeamentos anteriores.
	 * 
	 * Se alguma configuracao especifica precisar ser feita para um
	 * deternado teste, ela deve ser adicionada antes deste metodo,
	 * nos exemplos abaixo umFime e umFilmeSemEstoque sao metodos
	 * estaticos que criam instancias e podem ser chamados sem ter 
	 * uma instancia de FilmeBuilder.
	 * 
	 * Contudo, o ultimo metodo da cadeia deve ser este que retorna o 
	 * objeto que precisamos em nosso cenario, que eh um filme.
	 * 
	 * FilmeBuilder.umFilme().agora()
	 * FilmeBuilder.umFilme().comValor(0.0).agora()
	 * FilmeBuilder.umFilme().semEstoque().agora()
	 * FilmeBuilder.umFilmeSemEstoque().agora()
	 */
	public Filme agora() {
		return filme;
	}
}
