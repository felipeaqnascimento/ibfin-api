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
import br.com.ibrowse.bean.ContasPagar;
//--- Services 
import br.com.ibrowse.business.service.ContasPagarService;
import br.com.ibrowse.test.ContasPagarFactoryForTest;
import br.com.ibrowse.web.common.Message;
import br.com.ibrowse.web.common.MessageHelper;
import br.com.ibrowse.web.common.MessageType;

@RunWith(MockitoJUnitRunner.class)
public class ContasPagarControllerTest {
	
	@InjectMocks
	private ContasPagarController contasPagarController;
	@Mock
	private ContasPagarService contasPagarService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private ContasPagarFactoryForTest contasPagarFactoryForTest = new ContasPagarFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<ContasPagar> list = new ArrayList<ContasPagar>();
		when(contasPagarService.findAll()).thenReturn(list);
		
		// When
		String viewName = contasPagarController.list(model);
		
		// Then
		assertEquals("contasPagar/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = contasPagarController.formForCreate(model);
		
		// Then
		assertEquals("contasPagar/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((ContasPagar)modelMap.get("contasPagar")).getOidContasPagar());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/contasPagar/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		ContasPagar contasPagar = contasPagarFactoryForTest.newContasPagar();
		Long oidContasPagar = contasPagar.getOidContasPagar();
		when(contasPagarService.findById(oidContasPagar)).thenReturn(contasPagar);
		
		// When
		String viewName = contasPagarController.formForUpdate(model, oidContasPagar);
		
		// Then
		assertEquals("contasPagar/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasPagar, (ContasPagar) modelMap.get("contasPagar"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/contasPagar/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		ContasPagar contasPagar = contasPagarFactoryForTest.newContasPagar();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		ContasPagar contasPagarCreated = new ContasPagar();
		when(contasPagarService.create(contasPagar)).thenReturn(contasPagarCreated); 
		
		// When
		String viewName = contasPagarController.create(contasPagar, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/contasPagar/form/"+contasPagar.getOidContasPagar(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasPagarCreated, (ContasPagar) modelMap.get("contasPagar"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		ContasPagar contasPagar = contasPagarFactoryForTest.newContasPagar();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = contasPagarController.create(contasPagar, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("contasPagar/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasPagar, (ContasPagar) modelMap.get("contasPagar"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/contasPagar/create", modelMap.get("saveAction"));
		
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

		ContasPagar contasPagar = contasPagarFactoryForTest.newContasPagar();
		
		Exception exception = new RuntimeException("test exception");
		when(contasPagarService.create(contasPagar)).thenThrow(exception);
		
		// When
		String viewName = contasPagarController.create(contasPagar, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("contasPagar/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasPagar, (ContasPagar) modelMap.get("contasPagar"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/contasPagar/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "contasPagar.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		ContasPagar contasPagar = contasPagarFactoryForTest.newContasPagar();
		Long oidContasPagar = contasPagar.getOidContasPagar();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		ContasPagar contasPagarSaved = new ContasPagar();
		contasPagarSaved.setOidContasPagar(oidContasPagar);
		when(contasPagarService.update(contasPagar)).thenReturn(contasPagarSaved); 
		
		// When
		String viewName = contasPagarController.update(contasPagar, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/contasPagar/form/"+contasPagar.getOidContasPagar(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasPagarSaved, (ContasPagar) modelMap.get("contasPagar"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		ContasPagar contasPagar = contasPagarFactoryForTest.newContasPagar();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = contasPagarController.update(contasPagar, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("contasPagar/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasPagar, (ContasPagar) modelMap.get("contasPagar"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/contasPagar/update", modelMap.get("saveAction"));
		
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

		ContasPagar contasPagar = contasPagarFactoryForTest.newContasPagar();
		
		Exception exception = new RuntimeException("test exception");
		when(contasPagarService.update(contasPagar)).thenThrow(exception);
		
		// When
		String viewName = contasPagarController.update(contasPagar, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("contasPagar/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasPagar, (ContasPagar) modelMap.get("contasPagar"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/contasPagar/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "contasPagar.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		ContasPagar contasPagar = contasPagarFactoryForTest.newContasPagar();
		Long oidContasPagar = contasPagar.getOidContasPagar();
		
		// When
		String viewName = contasPagarController.delete(redirectAttributes, oidContasPagar);
		
		// Then
		verify(contasPagarService).delete(oidContasPagar);
		assertEquals("redirect:/contasPagar", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		ContasPagar contasPagar = contasPagarFactoryForTest.newContasPagar();
		Long oidContasPagar = contasPagar.getOidContasPagar();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(contasPagarService).delete(oidContasPagar);
		
		// When
		String viewName = contasPagarController.delete(redirectAttributes, oidContasPagar);
		
		// Then
		verify(contasPagarService).delete(oidContasPagar);
		assertEquals("redirect:/contasPagar", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "contasPagar.error.delete", exception);
	}
	
	
}
