package br.com.ibrowse.test;

import br.com.ibrowse.bean.jpa.ContasReceberEntity;

public class ContasReceberEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public ContasReceberEntity newContasReceberEntity() {

		Long oidContasReceber = mockValues.nextLong();

		ContasReceberEntity contasReceberEntity = new ContasReceberEntity();
		contasReceberEntity.setOidContasReceber(oidContasReceber);
		return contasReceberEntity;
	}
	
}
