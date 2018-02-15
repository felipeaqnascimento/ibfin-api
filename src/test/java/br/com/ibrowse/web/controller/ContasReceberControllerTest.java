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
import br.com.ibrowse.bean.ContasReceber;
//--- Services 
import br.com.ibrowse.business.service.ContasReceberService;
import br.com.ibrowse.test.ContasReceberFactoryForTest;
import br.com.ibrowse.web.common.Message;
import br.com.ibrowse.web.common.MessageHelper;
import br.com.ibrowse.web.common.MessageType;

@RunWith(MockitoJUnitRunner.class)
public class ContasReceberControllerTest {
	
	@InjectMocks
	private ContasReceberController contasReceberController;
	@Mock
	private ContasReceberService contasReceberService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private ContasReceberFactoryForTest contasReceberFactoryForTest = new ContasReceberFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<ContasReceber> list = new ArrayList<ContasReceber>();
		when(contasReceberService.findAll()).thenReturn(list);
		
		// When
		String viewName = contasReceberController.list(model);
		
		// Then
		assertEquals("contasReceber/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = contasReceberController.formForCreate(model);
		
		// Then
		assertEquals("contasReceber/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((ContasReceber)modelMap.get("contasReceber")).getOidContasReceber());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/contasReceber/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		ContasReceber contasReceber = contasReceberFactoryForTest.newContasReceber();
		Long oidContasReceber = contasReceber.getOidContasReceber();
		when(contasReceberService.findById(oidContasReceber)).thenReturn(contasReceber);
		
		// When
		String viewName = contasReceberController.formForUpdate(model, oidContasReceber);
		
		// Then
		assertEquals("contasReceber/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasReceber, (ContasReceber) modelMap.get("contasReceber"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/contasReceber/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		ContasReceber contasReceber = contasReceberFactoryForTest.newContasReceber();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		ContasReceber contasReceberCreated = new ContasReceber();
		when(contasReceberService.create(contasReceber)).thenReturn(contasReceberCreated); 
		
		// When
		String viewName = contasReceberController.create(contasReceber, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/contasReceber/form/"+contasReceber.getOidContasReceber(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasReceberCreated, (ContasReceber) modelMap.get("contasReceber"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		ContasReceber contasReceber = contasReceberFactoryForTest.newContasReceber();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = contasReceberController.create(contasReceber, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("contasReceber/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasReceber, (ContasReceber) modelMap.get("contasReceber"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/contasReceber/create", modelMap.get("saveAction"));
		
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

		ContasReceber contasReceber = contasReceberFactoryForTest.newContasReceber();
		
		Exception exception = new RuntimeException("test exception");
		when(contasReceberService.create(contasReceber)).thenThrow(exception);
		
		// When
		String viewName = contasReceberController.create(contasReceber, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("contasReceber/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasReceber, (ContasReceber) modelMap.get("contasReceber"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/contasReceber/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "contasReceber.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		ContasReceber contasReceber = contasReceberFactoryForTest.newContasReceber();
		Long oidContasReceber = contasReceber.getOidContasReceber();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		ContasReceber contasReceberSaved = new ContasReceber();
		contasReceberSaved.setOidContasReceber(oidContasReceber);
		when(contasReceberService.update(contasReceber)).thenReturn(contasReceberSaved); 
		
		// When
		String viewName = contasReceberController.update(contasReceber, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/contasReceber/form/"+contasReceber.getOidContasReceber(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasReceberSaved, (ContasReceber) modelMap.get("contasReceber"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		ContasReceber contasReceber = contasReceberFactoryForTest.newContasReceber();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = contasReceberController.update(contasReceber, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("contasReceber/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasReceber, (ContasReceber) modelMap.get("contasReceber"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/contasReceber/update", modelMap.get("saveAction"));
		
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

		ContasReceber contasReceber = contasReceberFactoryForTest.newContasReceber();
		
		Exception exception = new RuntimeException("test exception");
		when(contasReceberService.update(contasReceber)).thenThrow(exception);
		
		// When
		String viewName = contasReceberController.update(contasReceber, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("contasReceber/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(contasReceber, (ContasReceber) modelMap.get("contasReceber"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/contasReceber/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "contasReceber.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		ContasReceber contasReceber = contasReceberFactoryForTest.newContasReceber();
		Long oidContasReceber = contasReceber.getOidContasReceber();
		
		// When
		String viewName = contasReceberController.delete(redirectAttributes, oidContasReceber);
		
		// Then
		verify(contasReceberService).delete(oidContasReceber);
		assertEquals("redirect:/contasReceber", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		ContasReceber contasReceber = contasReceberFactoryForTest.newContasReceber();
		Long oidContasReceber = contasReceber.getOidContasReceber();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(contasReceberService).delete(oidContasReceber);
		
		// When
		String viewName = contasReceberController.delete(redirectAttributes, oidContasReceber);
		
		// Then
		verify(contasReceberService).delete(oidContasReceber);
		assertEquals("redirect:/contasReceber", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "contasReceber.error.delete", exception);
	}
	
	
}
