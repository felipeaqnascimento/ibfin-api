package br.com.ibrowse.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.ibrowse.bean.jpa.ContasReceberEntity;

/**
 * Repository : ContasReceber.
 */
public interface ContasReceberJpaRepository extends PagingAndSortingRepository<ContasReceberEntity, Long> {

}
