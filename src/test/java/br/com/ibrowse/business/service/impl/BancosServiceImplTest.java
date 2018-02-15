/*
 * Created on 23 mai 2017 ( Time 18:19:40 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package br.com.ibrowse.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.ibrowse.bean.Bancos;
import br.com.ibrowse.bean.jpa.BancosEntity;
import br.com.ibrowse.business.service.mapping.BancosServiceMapper;
import br.com.ibrowse.data.repository.jpa.BancosJpaRepository;
import br.com.ibrowse.test.BancosEntityFactoryForTest;
import br.com.ibrowse.test.BancosFactoryForTest;
import br.com.ibrowse.test.MockValues;

/**
 * Test : Implementation of BancosService
 */
@RunWith(MockitoJUnitRunner.class)
public class BancosServiceImplTest {

	@InjectMocks
	private BancosServiceImpl bancosService;
	@Mock
	private BancosJpaRepository bancosJpaRepository;
	@Mock
	private BancosServiceMapper bancosServiceMapper;
	
	private BancosFactoryForTest bancosFactoryForTest = new BancosFactoryForTest();

	private BancosEntityFactoryForTest bancosEntityFactoryForTest = new BancosEntityFactoryForTest();

	private MockValues mockValues = new MockValues();
	
	@Test
	public void findById() {
		// Given
		Long oidBancos = mockValues.nextLong();
		
		BancosEntity bancosEntity = bancosJpaRepository.findOne(oidBancos);
		
		Bancos bancos = bancosFactoryForTest.newBancos();
		when(bancosServiceMapper.mapBancosEntityToBancos(bancosEntity)).thenReturn(bancos);

		// When
		Bancos bancosFound = bancosService.findById(oidBancos);

		// Then
		assertEquals(bancos.getOidBancos(),bancosFound.getOidBancos());
	}

	@Test
	public void findAll() {
		// Given
		List<BancosEntity> bancosEntitys = new ArrayList<BancosEntity>();
		BancosEntity bancosEntity1 = bancosEntityFactoryForTest.newBancosEntity();
		bancosEntitys.add(bancosEntity1);
		BancosEntity bancosEntity2 = bancosEntityFactoryForTest.newBancosEntity();
		bancosEntitys.add(bancosEntity2);
		when(bancosJpaRepository.findAll()).thenReturn(bancosEntitys);
		
		Bancos bancos1 = bancosFactoryForTest.newBancos();
		when(bancosServiceMapper.mapBancosEntityToBancos(bancosEntity1)).thenReturn(bancos1);
		Bancos bancos2 = bancosFactoryForTest.newBancos();
		when(bancosServiceMapper.mapBancosEntityToBancos(bancosEntity2)).thenReturn(bancos2);

		// When
		List<Bancos> bancossFounds = bancosService.findAll();

		// Then
		assertTrue(bancos1 == bancossFounds.get(0));
		assertTrue(bancos2 == bancossFounds.get(1));
	}

	@Test
	public void create() {
		// Given
		Bancos bancos = bancosFactoryForTest.newBancos();

		BancosEntity bancosEntity = bancosEntityFactoryForTest.newBancosEntity();
		when(bancosJpaRepository.findOne(bancos.getOidBancos())).thenReturn(null);
		
		bancosEntity = new BancosEntity();
		bancosServiceMapper.mapBancosToBancosEntity(bancos, bancosEntity);
		BancosEntity bancosEntitySaved = bancosJpaRepository.save(bancosEntity);
		
		Bancos bancosSaved = bancosFactoryForTest.newBancos();
		when(bancosServiceMapper.mapBancosEntityToBancos(bancosEntitySaved)).thenReturn(bancosSaved);

		// When
		Bancos bancosResult = bancosService.create(bancos);

		// Then
		assertTrue(bancosResult == bancosSaved);
	}

	@Test
	public void createKOExists() {
		// Given
		Bancos bancos = bancosFactoryForTest.newBancos();

		BancosEntity bancosEntity = bancosEntityFactoryForTest.newBancosEntity();
		when(bancosJpaRepository.findOne(bancos.getOidBancos())).thenReturn(bancosEntity);

		// When
		Exception exception = null;
		try {
			bancosService.create(bancos);
		} catch(Exception e) {
			exception = e;
		}

		// Then
		assertTrue(exception instanceof IllegalStateException);
		assertEquals("already.exists", exception.getMessage());
	}

	@Test
	public void update() {
		// Given
		Bancos bancos = bancosFactoryForTest.newBancos();

		BancosEntity bancosEntity = bancosEntityFactoryForTest.newBancosEntity();
		when(bancosJpaRepository.findOne(bancos.getOidBancos())).thenReturn(bancosEntity);
		
		BancosEntity bancosEntitySaved = bancosEntityFactoryForTest.newBancosEntity();
		when(bancosJpaRepository.save(bancosEntity)).thenReturn(bancosEntitySaved);
		
		Bancos bancosSaved = bancosFactoryForTest.newBancos();
		when(bancosServiceMapper.mapBancosEntityToBancos(bancosEntitySaved)).thenReturn(bancosSaved);

		// When
		Bancos bancosResult = bancosService.update(bancos);

		// Then
		verify(bancosServiceMapper).mapBancosToBancosEntity(bancos, bancosEntity);
		assertTrue(bancosResult == bancosSaved);
	}

	@Test
	public void delete() {
		// Given
		Long oidBancos = mockValues.nextLong();

		// When
		bancosService.delete(oidBancos);

		// Then
		verify(bancosJpaRepository).delete(oidBancos);
		
	}

}
