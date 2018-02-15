package br.com.ibrowse.test;

import br.com.ibrowse.bean.jpa.TiposTitulosEntity;

public class TiposTitulosEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TiposTitulosEntity newTiposTitulosEntity() {

		Long oidTiposTitulos = mockValues.nextLong();

		TiposTitulosEntity tiposTitulosEntity = new TiposTitulosEntity();
		tiposTitulosEntity.setOidTiposTitulos(oidTiposTitulos);
		return tiposTitulosEntity;
	}
	
}
