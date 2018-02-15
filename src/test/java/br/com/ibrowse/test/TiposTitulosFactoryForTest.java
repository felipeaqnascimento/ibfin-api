package br.com.ibrowse.test;

import br.com.ibrowse.bean.TiposTitulos;

public class TiposTitulosFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TiposTitulos newTiposTitulos() {

		Long oidTiposTitulos = mockValues.nextLong();

		TiposTitulos tiposTitulos = new TiposTitulos();
		tiposTitulos.setOidTiposTitulos(oidTiposTitulos);
		return tiposTitulos;
	}
	
}
