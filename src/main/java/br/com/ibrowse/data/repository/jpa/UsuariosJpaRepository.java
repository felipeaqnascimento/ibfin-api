package br.com.ibrowse.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import br.com.ibrowse.bean.jpa.UsuariosEntity;

/**
 * Repository : Usuarios.
 */
public interface UsuariosJpaRepository extends PagingAndSortingRepository<UsuariosEntity, Long> {
	
	

}
