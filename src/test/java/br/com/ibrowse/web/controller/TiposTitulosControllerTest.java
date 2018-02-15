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
import br.com.ibrowse.bean.TiposTitulos;
//--- Services 
import br.com.ibrowse.business.service.TiposTitulosService;
import br.com.ibrowse.test.TiposTitulosFactoryForTest;
import br.com.ibrowse.web.common.Message;
import br.com.ibrowse.web.common.MessageHelper;
import br.com.ibrowse.web.common.MessageType;

@RunWith(MockitoJUnitRunner.class)
public class TiposTitulosControllerTest {
	
	@InjectMocks
	private TiposTitulosController tiposTitulosController;
	@Mock
	private TiposTitulosService tiposTitulosService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private TiposTitulosFactoryForTest tiposTitulosFactoryForTest = new TiposTitulosFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<TiposTitulos> list = new ArrayList<TiposTitulos>();
		when(tiposTitulosService.findAll()).thenReturn(list);
		
		// When
		String viewName = tiposTitulosController.list(model);
		
		// Then
		assertEquals("tiposTitulos/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = tiposTitulosController.formForCreate(model);
		
		// Then
		assertEquals("tiposTitulos/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((TiposTitulos)modelMap.get("tiposTitulos")).getOidTiposTitulos());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tiposTitulos/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TiposTitulos tiposTitulos = tiposTitulosFactoryForTest.newTiposTitulos();
		Long oidTiposTitulos = tiposTitulos.getOidTiposTitulos();
		when(tiposTitulosService.findById(oidTiposTitulos)).thenReturn(tiposTitulos);
		
		// When
		String viewName = tiposTitulosController.formForUpdate(model, oidTiposTitulos);
		
		// Then
		assertEquals("tiposTitulos/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tiposTitulos, (TiposTitulos) modelMap.get("tiposTitulos"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tiposTitulos/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		TiposTitulos tiposTitulos = tiposTitulosFactoryForTest.newTiposTitulos();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		TiposTitulos tiposTitulosCreated = new TiposTitulos();
		when(tiposTitulosService.create(tiposTitulos)).thenReturn(tiposTitulosCreated); 
		
		// When
		String viewName = tiposTitulosController.create(tiposTitulos, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/tiposTitulos/form/"+tiposTitulos.getOidTiposTitulos(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tiposTitulosCreated, (TiposTitulos) modelMap.get("tiposTitulos"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TiposTitulos tiposTitulos = tiposTitulosFactoryForTest.newTiposTitulos();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = tiposTitulosController.create(tiposTitulos, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("tiposTitulos/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tiposTitulos, (TiposTitulos) modelMap.get("tiposTitulos"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tiposTitulos/create", modelMap.get("saveAction"));
		
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

		TiposTitulos tiposTitulos = tiposTitulosFactoryForTest.newTiposTitulos();
		
		Exception exception = new RuntimeException("test exception");
		when(tiposTitulosService.create(tiposTitulos)).thenThrow(exception);
		
		// When
		String viewName = tiposTitulosController.create(tiposTitulos, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("tiposTitulos/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tiposTitulos, (TiposTitulos) modelMap.get("tiposTitulos"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tiposTitulos/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tiposTitulos.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		TiposTitulos tiposTitulos = tiposTitulosFactoryForTest.newTiposTitulos();
		Long oidTiposTitulos = tiposTitulos.getOidTiposTitulos();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		TiposTitulos tiposTitulosSaved = new TiposTitulos();
		tiposTitulosSaved.setOidTiposTitulos(oidTiposTitulos);
		when(tiposTitulosService.update(tiposTitulos)).thenReturn(tiposTitulosSaved); 
		
		// When
		String viewName = tiposTitulosController.update(tiposTitulos, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/tiposTitulos/form/"+tiposTitulos.getOidTiposTitulos(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tiposTitulosSaved, (TiposTitulos) modelMap.get("tiposTitulos"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TiposTitulos tiposTitulos = tiposTitulosFactoryForTest.newTiposTitulos();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = tiposTitulosController.update(tiposTitulos, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("tiposTitulos/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tiposTitulos, (TiposTitulos) modelMap.get("tiposTitulos"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tiposTitulos/update", modelMap.get("saveAction"));
		
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

		TiposTitulos tiposTitulos = tiposTitulosFactoryForTest.newTiposTitulos();
		
		Exception exception = new RuntimeException("test exception");
		when(tiposTitulosService.update(tiposTitulos)).thenThrow(exception);
		
		// When
		String viewName = tiposTitulosController.update(tiposTitulos, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("tiposTitulos/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tiposTitulos, (TiposTitulos) modelMap.get("tiposTitulos"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tiposTitulos/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tiposTitulos.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		TiposTitulos tiposTitulos = tiposTitulosFactoryForTest.newTiposTitulos();
		Long oidTiposTitulos = tiposTitulos.getOidTiposTitulos();
		
		// When
		String viewName = tiposTitulosController.delete(redirectAttributes, oidTiposTitulos);
		
		// Then
		verify(tiposTitulosService).delete(oidTiposTitulos);
		assertEquals("redirect:/tiposTitulos", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		TiposTitulos tiposTitulos = tiposTitulosFactoryForTest.newTiposTitulos();
		Long oidTiposTitulos = tiposTitulos.getOidTiposTitulos();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(tiposTitulosService).delete(oidTiposTitulos);
		
		// When
		String viewName = tiposTitulosController.delete(redirectAttributes, oidTiposTitulos);
		
		// Then
		verify(tiposTitulosService).delete(oidTiposTitulos);
		assertEquals("redirect:/tiposTitulos", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "tiposTitulos.error.delete", exception);
	}
	
	
}
