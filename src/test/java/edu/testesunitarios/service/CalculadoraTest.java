package edu.testesunitarios.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.testesunitarios.excecoes.NaoPodeDividirPorZeroException;
import edu.testesunitarios.servicos.Calculadora;

public class CalculadoraTest {
	
	public Calculadora calc;
	
	@Before
	public void setup() {
		calc = new Calculadora();
	}
	
	@Test
	public void deveSomarDoisValores() {
		//cenario
		int a = 5;
		int b = 3;
		
		//acao
		int resultado = calc.somar(a, b);
		
		//verificacao
		Assert.assertEquals(8, resultado);
	}
	
	@Test
	public void deveSubtrairDoisValores() {
		//cenario
		int a = 8;
		int b = 5;
		
		//acao
		int resultado = calc.subtrair(a, b);
		
		//verificacao
		Assert.assertEquals(3, resultado);
	}
	
	@Test
	public void deveMultiplicarDoisValores() {
		//cenario
		int a = 3;
		int b = 5;
		
		//acao
		int resultado = calc.multiplicar(a, b);
		
		//verificacao
		Assert.assertEquals(15, resultado);
	}
	
	@Test
	public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException {
		//cenario
		double a = 10;
		double b = 2;
		
		//acao
		double resultado = calc.dividir(a, b);
		
		//verificacao
		Assert.assertEquals(5.0, resultado, 0.1);
	}
	
	@Test(expected = NaoPodeDividirPorZeroException.class)
	public void deveLancarExcecaoAoDividirPorZero() throws NaoPodeDividirPorZeroException {
		//cenario
		double a = 10;
		double b = 0;
		
		//acao
		calc.dividir(a, b);
	}
}
