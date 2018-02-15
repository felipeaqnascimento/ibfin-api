package br.com.ibrowse.test;

import br.com.ibrowse.bean.ContasReceber;

public class ContasReceberFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public ContasReceber newContasReceber() {

		Long oidContasReceber = mockValues.nextLong();

		ContasReceber contasReceber = new ContasReceber();
		contasReceber.setOidContasReceber(oidContasReceber);
		return contasReceber;
	}
	
}
