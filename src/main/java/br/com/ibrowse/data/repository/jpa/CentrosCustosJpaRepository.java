package br.com.ibrowse.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.ibrowse.bean.jpa.CentrosCustosEntity;

/**
 * Repository : CentrosCustos.
 */
public interface CentrosCustosJpaRepository extends PagingAndSortingRepository<CentrosCustosEntity, Long> {
	
	public CentrosCustosEntity findByDsCentrosCustos(String dsCentrosCustos);

}
