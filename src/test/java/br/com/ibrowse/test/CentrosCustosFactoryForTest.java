package br.com.ibrowse.test;

import br.com.ibrowse.bean.CentrosCustos;

public class CentrosCustosFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public CentrosCustos newCentrosCustos() {

		Long oidCentrosCustos = mockValues.nextLong();

		CentrosCustos centrosCustos = new CentrosCustos();
		centrosCustos.setOidCentrosCustos(oidCentrosCustos);
		return centrosCustos;
	}
	
}
