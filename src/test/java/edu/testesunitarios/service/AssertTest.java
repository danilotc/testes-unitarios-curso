package edu.testesunitarios.service;

import org.junit.Assert;
import org.junit.Test;

import edu.testesunitarios.entidades.Usuario;

public class AssertTest {
	
	@Test
	public void test() {
		/* 
		 * Booleanos
		 * 
		 * Usar o minimo de negacoes possiveis
		 * 
		 * Para verificar se verdadeiro: assertTrue(true)
		 * Para verificar se falso: assertFalse(false)
		 */
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		
		/* 
		 * Igualdades
		 * 
		 * Verifica se um valor eh igual a outro tratando
		 * cada valor de forma diferente.
		 * 
		 * Uma mensagem de erro pode ser adicionada e ira
		 * aparecer quando um erro no teste acontecer.
		 */
		Assert.assertEquals("Erro de comparacao", 1, 1);
		
		/* 
		 * Igualdade com valores reais (Double)
		 * 
		 * assertEquals com valores double esta depreciado,
		 * mas eh possivel passar um terceiro parametro que
		 * serve como margem de erro, o delta de comparacao.
		 * 
		 * Esse terceiro parametro eh um valor que ira medir
		 * quantas casas decimais sera considerada no valor
		 * esperado (primeiro parametro).
		 * 
		 * Ex.: sera considerado apenas 0.512 do primeiro
		 * parametro, se o delta de comparacao tivesse com
		 * mais de tres casas decimais, um erro aconteceria
		 * porque o valor recebido (segundo parametro) so 
		 * tem 3 casas decimais.
		 * 
		 * assertEquals(valorEsperado, valorRecebido, delta)
		 */
		Assert.assertEquals(0.51234, 0.512, 0.001);
		
		/*
		 * Outro exemplo de igualdade com valores reais usando
		 * delta de comparacao para especificar limite de casas
		 * decimais a ser considerada (double)
		 * 
		 * A constante PI da classe Math possui um numero de
		 * casas decimais muito grande, por isso, neste caso,
		 * devemos usar o delta de comparacao para especificar
		 * quantas casas decimais sera considerada.
		 * 
		 * assertEquals(valorEsperado, valorRecebido, delta)
		 * 
		 * O terceiro parametro (0.01) indica que somente duas
		 * casas decimais sera considerado no valor esperado
		 * comparado com o valor recebido.
		 */
		Assert.assertEquals(Math.PI, 3.14, 0.01);
		
		/* 
		 * Primitivos
		 * 
		 * No metodo assertEquals nao funciona o conceito de
		 * boxing, por isso nao ha variacao de um tipo para
		 * outro. Entao devemos comparar VALORES PRIMITIVOS
		 * dessa forma, transformando um valor para poder
		 * ser comparado com outro.
		 * 
		 * Transformacoes:
		 * 
		 * primitivo para objeto: Wrapper.valueOf(valor)
		 * objeto para primitivo: valor.primitivoValue()
		 */
		int i = 5;
		Integer i2 = 5;
		// Transformando o valor esperado em objeto para poder
		// ser comparado com o valor recebido, que eh um objeto
		Assert.assertEquals(Integer.valueOf(i), i2);
		// Transformando o valor recebido em primitivo para poder
		// ser comparado com o valor esperado, que eh um primitivo
		Assert.assertEquals(i, i2.intValue());
		
		/* 
		 * Strings
		 * 
		 * Para o metodo assertEquals
		 * 
		 * Importante considerar a posicao dos parametros,
		 * o primeiro eh o valor esperado e o segundo eh o
		 * valor recebido.
		 * 
		 * assertEquals(valorEsperado, valorRecebido)
		 */
		Assert.assertEquals("bola", "bola"); // espero que sejam iguais
		Assert.assertNotEquals("bola", "casa"); // espero que sejam diferentes
		
		/* 
		 * Strings (continuacao)
		 * 
		 * Para o metodo assertTrue
		 * 
		 * Para verificar se duas strings sao iguais, usamos
		 * o metodo equalsIgnoreCase para ignorar maiusucas
		 * e minusculas na comparacao.
		 * 
		 * stringEsperada.equalsIgnoreCase(stringRecebida)
		 * 
		 * Podemos verificar tambem se a string recebida
		 * comeca com as iniciais da string esperada...
		 * 
		 * stringEsperada.startsWith(comecoDaStringRecebida)
		 */
		//Assert.assertEquals("bola", "Bola"); // nao funciona assim
		Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
		Assert.assertTrue("bola".startsWith("bo"));
		
		/*
		 * Igualdade de Objetos
		 * 
		 * Somente o proprio objeto eh quem deve dizer se
		 * eh igual ou diferente de outro objeto e isso eh
		 * feito atraves dos metodos equals e hashCode. 
		 * 
		 * Entao, o primeiro passo eh implementar os metodos 
		 * equals e hashCode na classe Usuario que sera usada
		 * para ver o comportamento dos metodos:
		 * 
		 * assertEquals
		 * assertSame
		 * assertNotSame
		 * assertTrue
		 * assertNull
		 * assertNotNull
		 */
		Usuario u1 = new Usuario("Usuario 1");
		Usuario u2 = new Usuario("Usuario 1");
		Usuario u3 = u2;
		Usuario u4 = null;
		
		// verifica se u2 eh igual a u1 - considerando nome
		Assert.assertEquals(u1, u2);
		
		// verifica se u3 eh da mesma instancia que u2
		Assert.assertSame(u2, u3);
		
		// verifica se u2 nao eh da mesma instancias que u1
		// sao iguais, porem de instancias diferentes
		Assert.assertNotSame(u1, u2);
		
		// verifica se o objeto u4 esta nulo, funciona, mas
		// existe um outro metodo para avaliar isso.
		Assert.assertTrue(u4 == null);
		
		// [este eh o metodo] verifica se um objeto esta nulo
		Assert.assertNull(u4);
		
		// verifica se um objeto nao esta nulo
		Assert.assertNotNull(u2);
	}
}