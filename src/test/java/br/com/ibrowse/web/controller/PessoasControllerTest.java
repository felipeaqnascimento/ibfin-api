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
import br.com.ibrowse.bean.Pessoas;
//--- Services 
import br.com.ibrowse.business.service.PessoasService;
import br.com.ibrowse.test.PessoasFactoryForTest;
import br.com.ibrowse.web.common.Message;
import br.com.ibrowse.web.common.MessageHelper;
import br.com.ibrowse.web.common.MessageType;

@RunWith(MockitoJUnitRunner.class)
public class PessoasControllerTest {
	
	@InjectMocks
	private PessoasController pessoasController;
	@Mock
	private PessoasService pessoasService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private PessoasFactoryForTest pessoasFactoryForTest = new PessoasFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Pessoas> list = new ArrayList<Pessoas>();
		when(pessoasService.findAll()).thenReturn(list);
		
		// When
		String viewName = pessoasController.list(model);
		
		// Then
		assertEquals("pessoas/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = pessoasController.formForCreate(model);
		
		// Then
		assertEquals("pessoas/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Pessoas)modelMap.get("pessoas")).getOidPessoas());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/pessoas/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Pessoas pessoas = pessoasFactoryForTest.newPessoas();
		Long oidPessoas = pessoas.getOidPessoas();
		when(pessoasService.findById(oidPessoas)).thenReturn(pessoas);
		
		// When
		String viewName = pessoasController.formForUpdate(model, oidPessoas);
		
		// Then
		assertEquals("pessoas/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(pessoas, (Pessoas) modelMap.get("pessoas"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/pessoas/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Pessoas pessoas = pessoasFactoryForTest.newPessoas();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Pessoas pessoasCreated = new Pessoas();
		when(pessoasService.create(pessoas)).thenReturn(pessoasCreated); 
		
		// When
		String viewName = pessoasController.create(pessoas, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/pessoas/form/"+pessoas.getOidPessoas(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(pessoasCreated, (Pessoas) modelMap.get("pessoas"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Pessoas pessoas = pessoasFactoryForTest.newPessoas();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = pessoasController.create(pessoas, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("pessoas/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(pessoas, (Pessoas) modelMap.get("pessoas"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/pessoas/create", modelMap.get("saveAction"));
		
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

		Pessoas pessoas = pessoasFactoryForTest.newPessoas();
		
		Exception exception = new RuntimeException("test exception");
		when(pessoasService.create(pessoas)).thenThrow(exception);
		
		// When
		String viewName = pessoasController.create(pessoas, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("pessoas/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(pessoas, (Pessoas) modelMap.get("pessoas"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/pessoas/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "pessoas.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Pessoas pessoas = pessoasFactoryForTest.newPessoas();
		Long oidPessoas = pessoas.getOidPessoas();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Pessoas pessoasSaved = new Pessoas();
		pessoasSaved.setOidPessoas(oidPessoas);
		when(pessoasService.update(pessoas)).thenReturn(pessoasSaved); 
		
		// When
		String viewName = pessoasController.update(pessoas, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/pessoas/form/"+pessoas.getOidPessoas(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(pessoasSaved, (Pessoas) modelMap.get("pessoas"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Pessoas pessoas = pessoasFactoryForTest.newPessoas();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = pessoasController.update(pessoas, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("pessoas/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(pessoas, (Pessoas) modelMap.get("pessoas"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/pessoas/update", modelMap.get("saveAction"));
		
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

		Pessoas pessoas = pessoasFactoryForTest.newPessoas();
		
		Exception exception = new RuntimeException("test exception");
		when(pessoasService.update(pessoas)).thenThrow(exception);
		
		// When
		String viewName = pessoasController.update(pessoas, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("pessoas/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(pessoas, (Pessoas) modelMap.get("pessoas"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/pessoas/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "pessoas.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Pessoas pessoas = pessoasFactoryForTest.newPessoas();
		Long oidPessoas = pessoas.getOidPessoas();
		
		// When
		String viewName = pessoasController.delete(redirectAttributes, oidPessoas);
		
		// Then
		verify(pessoasService).delete(oidPessoas);
		assertEquals("redirect:/pessoas", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Pessoas pessoas = pessoasFactoryForTest.newPessoas();
		Long oidPessoas = pessoas.getOidPessoas();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(pessoasService).delete(oidPessoas);
		
		// When
		String viewName = pessoasController.delete(redirectAttributes, oidPessoas);
		
		// Then
		verify(pessoasService).delete(oidPessoas);
		assertEquals("redirect:/pessoas", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "pessoas.error.delete", exception);
	}
	
	
}
