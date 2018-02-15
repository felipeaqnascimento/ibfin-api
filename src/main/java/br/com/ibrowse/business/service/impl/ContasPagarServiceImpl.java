/*
 * Created on 23 mai 2017 ( Time 18:19:41 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package br.com.ibrowse.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ibrowse.bean.ContasPagar;
import br.com.ibrowse.bean.jpa.ContasPagarEntity;
import br.com.ibrowse.business.service.ContasPagarService;
import br.com.ibrowse.business.service.mapping.ContasPagarServiceMapper;
import br.com.ibrowse.data.repository.jpa.ContasPagarJpaRepository;

/**
 * Implementation of ContasPagarService
 */
@Component
@Transactional
public class ContasPagarServiceImpl implements ContasPagarService {

	@Resource
	private ContasPagarJpaRepository contasPagarJpaRepository;

	@Resource
	private ContasPagarServiceMapper contasPagarServiceMapper;
	
	@Override
	public ContasPagar findById(Long oidContasPagar) {
		ContasPagarEntity contasPagarEntity = contasPagarJpaRepository.findOne(oidContasPagar);
		return contasPagarServiceMapper.mapContasPagarEntityToContasPagar(contasPagarEntity);
	}

	@Override
	public List<ContasPagar> findAll() {
		Iterable<ContasPagarEntity> entities = contasPagarJpaRepository.findAll();
		List<ContasPagar> beans = new ArrayList<ContasPagar>();
		for(ContasPagarEntity contasPagarEntity : entities) {
			beans.add(contasPagarServiceMapper.mapContasPagarEntityToContasPagar(contasPagarEntity));
		}
		return beans;
	}

	@Override
	public ContasPagar save(ContasPagar contasPagar) {
		return update(contasPagar) ;
	}

	@Override
	public ContasPagar create(ContasPagar contasPagar) {
		ContasPagarEntity contasPagarEntity = contasPagarJpaRepository.findByNrDocumentoFiscal(contasPagar.getNrDocumentoFiscal());
		if( contasPagarEntity != null ) {
			throw new IllegalStateException("Registro já existente!");
		}
		contasPagarEntity = new ContasPagarEntity();
		contasPagarServiceMapper.mapContasPagarToContasPagarEntity(contasPagar, contasPagarEntity);
		ContasPagarEntity contasPagarEntitySaved = contasPagarJpaRepository.save(contasPagarEntity);
		return contasPagarServiceMapper.mapContasPagarEntityToContasPagar(contasPagarEntitySaved);
	}

	@Override
	public ContasPagar update(ContasPagar contasPagar) {
		ContasPagarEntity contasPagarEntity = contasPagarJpaRepository.findOne(contasPagar.getOidContasPagar());
		contasPagarServiceMapper.mapContasPagarToContasPagarEntity(contasPagar, contasPagarEntity);
		ContasPagarEntity contasPagarEntitySaved = contasPagarJpaRepository.save(contasPagarEntity);
		return contasPagarServiceMapper.mapContasPagarEntityToContasPagar(contasPagarEntitySaved);
	}

	@Override
	public void delete(Long oidContasPagar) {
		contasPagarJpaRepository.delete(oidContasPagar);
	}

	public ContasPagarJpaRepository getContasPagarJpaRepository() {
		return contasPagarJpaRepository;
	}

	public void setContasPagarJpaRepository(ContasPagarJpaRepository contasPagarJpaRepository) {
		this.contasPagarJpaRepository = contasPagarJpaRepository;
	}

	public ContasPagarServiceMapper getContasPagarServiceMapper() {
		return contasPagarServiceMapper;
	}

	public void setContasPagarServiceMapper(ContasPagarServiceMapper contasPagarServiceMapper) {
		this.contasPagarServiceMapper = contasPagarServiceMapper;
	}

}
