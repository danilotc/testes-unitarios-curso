package edu.testesunitarios.service;

import org.junit.Assert;
import org.junit.Test;

/*
 * Este teste nao esta passando desta forma porque a ordem de
 * execucao deles esta sendo gerenciada pelo JUnit de forma 
 * aleatoria e os testes dependem um do outro.
 */
public class OrdemTest {

	private static int contador = 0;
	
	@Test
	public void inicio() {
		contador = 1;
	}

	@Test
	public void verifica() {
		Assert.assertEquals(1, contador);
	}
}
