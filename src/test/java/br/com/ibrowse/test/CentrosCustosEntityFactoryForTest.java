package br.com.ibrowse.test;

import br.com.ibrowse.bean.jpa.CentrosCustosEntity;

public class CentrosCustosEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public CentrosCustosEntity newCentrosCustosEntity() {

		Long oidCentrosCustos = mockValues.nextLong();

		CentrosCustosEntity centrosCustosEntity = new CentrosCustosEntity();
		centrosCustosEntity.setOidCentrosCustos(oidCentrosCustos);
		return centrosCustosEntity;
	}
	
}
