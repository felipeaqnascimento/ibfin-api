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
import br.com.ibrowse.bean.CentrosCustos;
//--- Services 
import br.com.ibrowse.business.service.CentrosCustosService;
import br.com.ibrowse.test.CentrosCustosFactoryForTest;
import br.com.ibrowse.web.common.Message;
import br.com.ibrowse.web.common.MessageHelper;
import br.com.ibrowse.web.common.MessageType;

@RunWith(MockitoJUnitRunner.class)
public class CentrosCustosControllerTest {
	
	@InjectMocks
	private CentrosCustosController centrosCustosController;
	@Mock
	private CentrosCustosService centrosCustosService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private CentrosCustosFactoryForTest centrosCustosFactoryForTest = new CentrosCustosFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<CentrosCustos> list = new ArrayList<CentrosCustos>();
		when(centrosCustosService.findAll()).thenReturn(list);
		
		// When
		String viewName = centrosCustosController.list(model);
		
		// Then
		assertEquals("centrosCustos/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = centrosCustosController.formForCreate(model);
		
		// Then
		assertEquals("centrosCustos/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((CentrosCustos)modelMap.get("centrosCustos")).getOidCentrosCustos());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/centrosCustos/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		CentrosCustos centrosCustos = centrosCustosFactoryForTest.newCentrosCustos();
		Long oidCentrosCustos = centrosCustos.getOidCentrosCustos();
		when(centrosCustosService.findById(oidCentrosCustos)).thenReturn(centrosCustos);
		
		// When
		String viewName = centrosCustosController.formForUpdate(model, oidCentrosCustos);
		
		// Then
		assertEquals("centrosCustos/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(centrosCustos, (CentrosCustos) modelMap.get("centrosCustos"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/centrosCustos/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		CentrosCustos centrosCustos = centrosCustosFactoryForTest.newCentrosCustos();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		CentrosCustos centrosCustosCreated = new CentrosCustos();
		when(centrosCustosService.create(centrosCustos)).thenReturn(centrosCustosCreated); 
		
		// When
		String viewName = centrosCustosController.create(centrosCustos, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/centrosCustos/form/"+centrosCustos.getOidCentrosCustos(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(centrosCustosCreated, (CentrosCustos) modelMap.get("centrosCustos"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		CentrosCustos centrosCustos = centrosCustosFactoryForTest.newCentrosCustos();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = centrosCustosController.create(centrosCustos, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("centrosCustos/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(centrosCustos, (CentrosCustos) modelMap.get("centrosCustos"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/centrosCustos/create", modelMap.get("saveAction"));
		
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

		CentrosCustos centrosCustos = centrosCustosFactoryForTest.newCentrosCustos();
		
		Exception exception = new RuntimeException("test exception");
		when(centrosCustosService.create(centrosCustos)).thenThrow(exception);
		
		// When
		String viewName = centrosCustosController.create(centrosCustos, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("centrosCustos/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(centrosCustos, (CentrosCustos) modelMap.get("centrosCustos"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/centrosCustos/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "centrosCustos.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		CentrosCustos centrosCustos = centrosCustosFactoryForTest.newCentrosCustos();
		Long oidCentrosCustos = centrosCustos.getOidCentrosCustos();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		CentrosCustos centrosCustosSaved = new CentrosCustos();
		centrosCustosSaved.setOidCentrosCustos(oidCentrosCustos);
		when(centrosCustosService.update(centrosCustos)).thenReturn(centrosCustosSaved); 
		
		// When
		String viewName = centrosCustosController.update(centrosCustos, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/centrosCustos/form/"+centrosCustos.getOidCentrosCustos(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(centrosCustosSaved, (CentrosCustos) modelMap.get("centrosCustos"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		CentrosCustos centrosCustos = centrosCustosFactoryForTest.newCentrosCustos();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = centrosCustosController.update(centrosCustos, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("centrosCustos/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(centrosCustos, (CentrosCustos) modelMap.get("centrosCustos"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/centrosCustos/update", modelMap.get("saveAction"));
		
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

		CentrosCustos centrosCustos = centrosCustosFactoryForTest.newCentrosCustos();
		
		Exception exception = new RuntimeException("test exception");
		when(centrosCustosService.update(centrosCustos)).thenThrow(exception);
		
		// When
		String viewName = centrosCustosController.update(centrosCustos, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("centrosCustos/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(centrosCustos, (CentrosCustos) modelMap.get("centrosCustos"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/centrosCustos/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "centrosCustos.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		CentrosCustos centrosCustos = centrosCustosFactoryForTest.newCentrosCustos();
		Long oidCentrosCustos = centrosCustos.getOidCentrosCustos();
		
		// When
		String viewName = centrosCustosController.delete(redirectAttributes, oidCentrosCustos);
		
		// Then
		verify(centrosCustosService).delete(oidCentrosCustos);
		assertEquals("redirect:/centrosCustos", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		CentrosCustos centrosCustos = centrosCustosFactoryForTest.newCentrosCustos();
		Long oidCentrosCustos = centrosCustos.getOidCentrosCustos();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(centrosCustosService).delete(oidCentrosCustos);
		
		// When
		String viewName = centrosCustosController.delete(redirectAttributes, oidCentrosCustos);
		
		// Then
		verify(centrosCustosService).delete(oidCentrosCustos);
		assertEquals("redirect:/centrosCustos", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "centrosCustos.error.delete", exception);
	}
	
	
}
