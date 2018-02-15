package br.com.ibrowse.test;

import br.com.ibrowse.bean.jpa.PessoasEntity;

public class PessoasEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public PessoasEntity newPessoasEntity() {

		Long oidPessoas = mockValues.nextLong();

		PessoasEntity pessoasEntity = new PessoasEntity();
		pessoasEntity.setOidPessoas(oidPessoas);
		return pessoasEntity;
	}
	
}
