package br.com.ibrowse.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.ibrowse.bean.jpa.ContasPagarEntity;

/**
 * Repository : ContasPagar.
 */
public interface ContasPagarJpaRepository extends PagingAndSortingRepository<ContasPagarEntity, Long> {
	
	public ContasPagarEntity findByNrDocumentoFiscal(String nrDocumentoFiscal);

}
