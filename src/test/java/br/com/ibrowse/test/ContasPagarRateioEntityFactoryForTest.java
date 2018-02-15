package br.com.ibrowse.test;

import br.com.ibrowse.bean.jpa.ContasPagarRateioEntity;

public class ContasPagarRateioEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public ContasPagarRateioEntity newContasPagarRateioEntity() {

		Long oidContasPagarRateio = mockValues.nextLong();

		ContasPagarRateioEntity contasPagarRateioEntity = new ContasPagarRateioEntity();
		contasPagarRateioEntity.setOidContasPagarRateio(oidContasPagarRateio);
		return contasPagarRateioEntity;
	}
	
}
