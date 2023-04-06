package edu.testesunitarios.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.testesunitarios.service.CalculadoraTest;
import edu.testesunitarios.service.CalculoValorLocacaoTest;
import edu.testesunitarios.service.LocacaoServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
	CalculadoraTest.class,
	CalculoValorLocacaoTest.class,
	LocacaoServiceTest.class
})
public class SuiteExecucao {
	/* 
	 * Esta classe esta aqui apenas porque precisa estar implementada
	 * para o suite funcionar corretamente, entretanto podemos usar 
	 * @BeforeClasse para iniciar alguma configuracao e @AfterClass 
	 * para finalizar.
	 * 
	 * Por exemplo, em testes funcionais pode ser necessario utilizar
	 * alguma configuracao de banco de dados, neste caso, podemos usar
	 * para efetuar essas configuracoes antes e depois dos testes.
	 */
}
