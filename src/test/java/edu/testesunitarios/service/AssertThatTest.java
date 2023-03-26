package edu.testesunitarios.service;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;

/**
 * Criada para testar o metodo <b>assertThat</b>. Os metodos foram
 * importados de forma estatica para garantir a fuidez da leitura
 * de cada instrucao, evitando ser chamado a partir da classe.
 * 
 * @see org.junit.Assert
 * @see ort.junit.Test
 */
public class AssertThatTest {

	@Test
	public void temItem() {
		// Verifique que na lista de strings tem os itens "Java" e "Kotlin"
		assertThat(Arrays.asList("Java", "Kotlin", "Scala"), hasItems("Java", "Kotlin"));
	}
	
	@Test
	public void ehVerdadeiro() {
		// Verifique que a expressao 2 x 3 eh 6
		assertThat(2 * 3, is(6));
	}
	
	@Test
	public void naoEhVerdadeiro() {
		// Verifique que a expressao 5 + 3 nao eh 9
		// Mostra uma mensagem quando for igual (junit4 --> string a esquerda)
		assertThat("5 + 3 eh 8", 5 + 3, is(not(9)));
	}
	
	@Test
	public void ehIgual() {
		// Verifique que o numero 23 eh igual a 23
		assertThat(23, is(equalTo(23)));
	}
}
