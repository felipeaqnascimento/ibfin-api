package br.com.ibrowse.test;

import br.com.ibrowse.bean.Bancos;

public class BancosFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Bancos newBancos() {

		Long oidBancos = mockValues.nextLong();

		Bancos bancos = new Bancos();
		bancos.setOidBancos(oidBancos);
		return bancos;
	}
	
}
