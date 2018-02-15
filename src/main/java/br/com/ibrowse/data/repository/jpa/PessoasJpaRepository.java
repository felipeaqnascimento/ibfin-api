package br.com.ibrowse.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.ibrowse.bean.jpa.PessoasEntity;

/**
 * Repository : Pessoas.
 */
public interface PessoasJpaRepository extends PagingAndSortingRepository<PessoasEntity, Long> {

	public PessoasEntity findByNrCpfCnpj(String nrCpfCnpj);
}
