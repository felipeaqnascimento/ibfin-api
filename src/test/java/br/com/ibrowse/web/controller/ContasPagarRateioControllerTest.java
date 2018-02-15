package br.com.ibrowse.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//--- Entities
import br.com.ibrowse.bean.ContasPagarRateio;
//--- Services 
import br.com.ibrowse.business.service.ContasPagarRateioService;
import br.com.ibrowse.test.ContasPagarRateioFactoryForTest;
import br.com.ibrowse.web.common.Message;
import br.com.ibrowse.web.common.MessageHelper;
import br.com.ibrowse.web.common.MessageType;

@RunWith(MockitoJUnitRunner.class)
public class ContasPagarRateioControllerTest {
	
	@InjectMocks
	private ContasPagarRateioController contasPagarRateioController;
	@Mock
	private ContasPagarRateioService contasPagarRateioService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private ContasPagarRateioFactoryForTest contasPagarRateioFactoryForTest = new ContasPagarRateioFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<ContasPagarRateio> list = new ArrayList<ContasPagarRateio>();
		when(contasPagarRateioService.findAll()).thenReturn(list);
		
		// When
		String viewName = contasPagarRateioController.list(model);
		
		// Then
		assertEquals("contasPagarRateio/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = contasPagarRateioController.formForCreate(model);
		
		// Then
		assertEquals("contasPagarRateio/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((ContasPagarRateio)modelMap.get("contasPagarRateio")).getOidContasPagarRateio());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/contasPagarRateio/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		ContasPagarRateio contasPagarRateio = contasPagarRateioFactoryForTest.newContasPagarRateio();
		Long oidContasPagarRateio = contasPagarRateio.getOidContasPagarRateio();
		when(contasPagarRateioService.findById(oidContasPagarRateio)).thenReturn(contasPagarRateio);
		
		// When
		String viewName = contasPagarRateioController.formForUpdate(model, oidContasPagarRateio);
		
		// Then
		assertEquals("contasPagarRateio/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasPagarRateio, (ContasPagarRateio) modelMap.get("contasPagarRateio"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/contasPagarRateio/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		ContasPagarRateio contasPagarRateio = contasPagarRateioFactoryForTest.newContasPagarRateio();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		ContasPagarRateio contasPagarRateioCreated = new ContasPagarRateio();
		when(contasPagarRateioService.create(contasPagarRateio)).thenReturn(contasPagarRateioCreated); 
		
		// When
		String viewName = contasPagarRateioController.create(contasPagarRateio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/contasPagarRateio/form/"+contasPagarRateio.getOidContasPagarRateio(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasPagarRateioCreated, (ContasPagarRateio) modelMap.get("contasPagarRateio"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		ContasPagarRateio contasPagarRateio = contasPagarRateioFactoryForTest.newContasPagarRateio();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = contasPagarRateioController.create(contasPagarRateio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("contasPagarRateio/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasPagarRateio, (ContasPagarRateio) modelMap.get("contasPagarRateio"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/contasPagarRateio/create", modelMap.get("saveAction"));
		
	}

	@Test
	public void createException() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		ContasPagarRateio contasPagarRateio = contasPagarRateioFactoryForTest.newContasPagarRateio();
		
		Exception exception = new RuntimeException("test exception");
		when(contasPagarRateioService.create(contasPagarRateio)).thenThrow(exception);
		
		// When
		String viewName = contasPagarRateioController.create(contasPagarRateio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("contasPagarRateio/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasPagarRateio, (ContasPagarRateio) modelMap.get("contasPagarRateio"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/contasPagarRateio/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "contasPagarRateio.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		ContasPagarRateio contasPagarRateio = contasPagarRateioFactoryForTest.newContasPagarRateio();
		Long oidContasPagarRateio = contasPagarRateio.getOidContasPagarRateio();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		ContasPagarRateio contasPagarRateioSaved = new ContasPagarRateio();
		contasPagarRateioSaved.setOidContasPagarRateio(oidContasPagarRateio);
		when(contasPagarRateioService.update(contasPagarRateio)).thenReturn(contasPagarRateioSaved); 
		
		// When
		String viewName = contasPagarRateioController.update(contasPagarRateio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/contasPagarRateio/form/"+contasPagarRateio.getOidContasPagarRateio(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasPagarRateioSaved, (ContasPagarRateio) modelMap.get("contasPagarRateio"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		ContasPagarRateio contasPagarRateio = contasPagarRateioFactoryForTest.newContasPagarRateio();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = contasPagarRateioController.update(contasPagarRateio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("contasPagarRateio/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasPagarRateio, (ContasPagarRateio) modelMap.get("contasPagarRateio"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/contasPagarRateio/update", modelMap.get("saveAction"));
		
	}

	@Test
	public void updateException() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		ContasPagarRateio contasPagarRateio = contasPagarRateioFactoryForTest.newContasPagarRateio();
		
		Exception exception = new RuntimeException("test exception");
		when(contasPagarRateioService.update(contasPagarRateio)).thenThrow(exception);
		
		// When
		String viewName = contasPagarRateioController.update(contasPagarRateio, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("contasPagarRateio/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasPagarRateio, (ContasPagarRateio) modelMap.get("contasPagarRateio"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/contasPagarRateio/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "contasPagarRateio.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		ContasPagarRateio contasPagarRateio = contasPagarRateioFactoryForTest.newContasPagarRateio();
		Long oidContasPagarRateio = contasPagarRateio.getOidContasPagarRateio();
		
		// When
		String viewName = contasPagarRateioController.delete(redirectAttributes, oidContasPagarRateio);
		
		// Then
		verify(contasPagarRateioService).delete(oidContasPagarRateio);
		assertEquals("redirect:/contasPagarRateio", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		ContasPagarRateio contasPagarRateio = contasPagarRateioFactoryForTest.newContasPagarRateio();
		Long oidContasPagarRateio = contasPagarRateio.getOidContasPagarRateio();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(contasPagarRateioService).delete(oidContasPagarRateio);
		
		// When
		String viewName = contasPagarRateioController.delete(redirectAttributes, oidContasPagarRateio);
		
		// Then
		verify(contasPagarRateioService).delete(oidContasPagarRateio);
		assertEquals("redirect:/contasPagarRateio", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "contasPagarRateio.error.delete", exception);
	}
	
	
}
