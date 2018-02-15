package br.com.ibrowse.test;

import br.com.ibrowse.bean.Pessoas;

public class PessoasFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Pessoas newPessoas() {

		Long oidPessoas = mockValues.nextLong();

		Pessoas pessoas = new Pessoas();
		pessoas.setOidPessoas(oidPessoas);
		return pessoas;
	}
	
}
