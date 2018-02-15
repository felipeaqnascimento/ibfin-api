package br.com.ibrowse.test;

import br.com.ibrowse.bean.ContasPagarRateio;

public class ContasPagarRateioFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public ContasPagarRateio newContasPagarRateio() {

		Long oidContasPagarRateio = mockValues.nextLong();

		ContasPagarRateio contasPagarRateio = new ContasPagarRateio();
		contasPagarRateio.setOidContasPagarRateio(oidContasPagarRateio);
		return contasPagarRateio;
	}
	
}
