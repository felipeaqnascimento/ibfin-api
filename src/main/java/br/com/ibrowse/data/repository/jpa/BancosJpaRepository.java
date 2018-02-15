package br.com.ibrowse.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.ibrowse.bean.jpa.BancosEntity;

/**
 * Repository : Bancos.
 */
public interface BancosJpaRepository extends PagingAndSortingRepository<BancosEntity, Long> {
	
	public BancosEntity findByCdBanco(Long cdBanco);

}
