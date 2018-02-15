package br.com.ibrowse.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.ibrowse.bean.jpa.TiposTitulosEntity;

/**
 * Repository : TiposTitulos.
 */
public interface TiposTitulosJpaRepository extends PagingAndSortingRepository<TiposTitulosEntity, Long> {
	
	public TiposTitulosEntity findByDsTiposTitulos(String dsTiposTitulos);

}
