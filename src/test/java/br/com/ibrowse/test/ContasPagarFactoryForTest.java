package br.com.ibrowse.test;

import br.com.ibrowse.bean.ContasPagar;

public class ContasPagarFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public ContasPagar newContasPagar() {

		Long oidContasPagar = mockValues.nextLong();

		ContasPagar contasPagar = new ContasPagar();
		contasPagar.setOidContasPagar(oidContasPagar);
		return contasPagar;
	}
	
}
