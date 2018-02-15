package br.com.ibrowse.test;

import br.com.ibrowse.bean.jpa.ContasPagarEntity;

public class ContasPagarEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public ContasPagarEntity newContasPagarEntity() {

		Long oidContasPagar = mockValues.nextLong();

		ContasPagarEntity contasPagarEntity = new ContasPagarEntity();
		contasPagarEntity.setOidContasPagar(oidContasPagar);
		return contasPagarEntity;
	}
	
}
