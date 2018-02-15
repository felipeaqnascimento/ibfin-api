package br.com.ibrowse.test;

import br.com.ibrowse.bean.jpa.BancosEntity;

public class BancosEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public BancosEntity newBancosEntity() {

		Long oidBancos = mockValues.nextLong();

		BancosEntity bancosEntity = new BancosEntity();
		bancosEntity.setOidBancos(oidBancos);
		return bancosEntity;
	}
	
}
