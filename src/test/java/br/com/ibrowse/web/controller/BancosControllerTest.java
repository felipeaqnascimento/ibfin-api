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
import br.com.ibrowse.bean.Bancos;
//--- Services 
import br.com.ibrowse.business.service.BancosService;
import br.com.ibrowse.test.BancosFactoryForTest;
import br.com.ibrowse.web.common.Message;
import br.com.ibrowse.web.common.MessageHelper;
import br.com.ibrowse.web.common.MessageType;

@RunWith(MockitoJUnitRunner.class)
public class BancosControllerTest {
	
	@InjectMocks
	private BancosController bancosController;
	@Mock
	private BancosService bancosService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private BancosFactoryForTest bancosFactoryForTest = new BancosFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Bancos> list = new ArrayList<Bancos>();
		when(bancosService.findAll()).thenReturn(list);
		
		// When
		String viewName = bancosController.list(model);
		
		// Then
		assertEquals("bancos/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = bancosController.formForCreate(model);
		
		// Then
		assertEquals("bancos/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Bancos)modelMap.get("bancos")).getOidBancos());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/bancos/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Bancos bancos = bancosFactoryForTest.newBancos();
		Long oidBancos = bancos.getOidBancos();
		when(bancosService.findById(oidBancos)).thenReturn(bancos);
		
		// When
		String viewName = bancosController.formForUpdate(model, oidBancos);
		
		// Then
		assertEquals("bancos/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(bancos, (Bancos) modelMap.get("bancos"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/bancos/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Bancos bancos = bancosFactoryForTest.newBancos();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Bancos bancosCreated = new Bancos();
		when(bancosService.create(bancos)).thenReturn(bancosCreated); 
		
		// When
		String viewName = bancosController.create(bancos, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/bancos/form/"+bancos.getOidBancos(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(bancosCreated, (Bancos) modelMap.get("bancos"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Bancos bancos = bancosFactoryForTest.newBancos();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = bancosController.create(bancos, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("bancos/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(bancos, (Bancos) modelMap.get("bancos"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/bancos/create", modelMap.get("saveAction"));
		
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

		Bancos bancos = bancosFactoryForTest.newBancos();
		
		Exception exception = new RuntimeException("test exception");
		when(bancosService.create(bancos)).thenThrow(exception);
		
		// When
		String viewName = bancosController.create(bancos, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("bancos/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(bancos, (Bancos) modelMap.get("bancos"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/bancos/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "bancos.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Bancos bancos = bancosFactoryForTest.newBancos();
		Long oidBancos = bancos.getOidBancos();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Bancos bancosSaved = new Bancos();
		bancosSaved.setOidBancos(oidBancos);
		when(bancosService.update(bancos)).thenReturn(bancosSaved); 
		
		// When
		String viewName = bancosController.update(bancos, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/bancos/form/"+bancos.getOidBancos(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(bancosSaved, (Bancos) modelMap.get("bancos"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Bancos bancos = bancosFactoryForTest.newBancos();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = bancosController.update(bancos, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("bancos/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(bancos, (Bancos) modelMap.get("bancos"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/bancos/update", modelMap.get("saveAction"));
		
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

		Bancos bancos = bancosFactoryForTest.newBancos();
		
		Exception exception = new RuntimeException("test exception");
		when(bancosService.update(bancos)).thenThrow(exception);
		
		// When
		String viewName = bancosController.update(bancos, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("bancos/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(bancos, (Bancos) modelMap.get("bancos"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/bancos/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "bancos.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Bancos bancos = bancosFactoryForTest.newBancos();
		Long oidBancos = bancos.getOidBancos();
		
		// When
		String viewName = bancosController.delete(redirectAttributes, oidBancos);
		
		// Then
		verify(bancosService).delete(oidBancos);
		assertEquals("redirect:/bancos", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Bancos bancos = bancosFactoryForTest.newBancos();
		Long oidBancos = bancos.getOidBancos();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(bancosService).delete(oidBancos);
		
		// When
		String viewName = bancosController.delete(redirectAttributes, oidBancos);
		
		// Then
		verify(bancosService).delete(oidBancos);
		assertEquals("redirect:/bancos", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "bancos.error.delete", exception);
	}
	
	
}
