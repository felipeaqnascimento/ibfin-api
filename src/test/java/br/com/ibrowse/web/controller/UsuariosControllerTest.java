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

//--- Entities
import br.com.ibrowse.bean.Usuarios;
import br.com.ibrowse.test.UsuariosFactoryForTest;

//--- Services 
import br.com.ibrowse.business.service.UsuariosService;


import br.com.ibrowse.web.common.Message;
import br.com.ibrowse.web.common.MessageHelper;
import br.com.ibrowse.web.common.MessageType;
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

@RunWith(MockitoJUnitRunner.class)
public class UsuariosControllerTest {
	
	@InjectMocks
	private UsuariosController usuariosController;
	@Mock
	private UsuariosService usuariosService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private UsuariosFactoryForTest usuariosFactoryForTest = new UsuariosFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<Usuarios> list = new ArrayList<Usuarios>();
		when(usuariosService.findAll()).thenReturn(list);
		
		// When
		String viewName = usuariosController.list(model);
		
		// Then
		assertEquals("usuarios/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = usuariosController.formForCreate(model);
		
		// Then
		assertEquals("usuarios/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((Usuarios)modelMap.get("usuarios")).getOidUsuarios());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/usuarios/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Usuarios usuarios = usuariosFactoryForTest.newUsuarios();
		BigDecimal oidUsuarios = usuarios.getOidUsuarios();
		when(usuariosService.findById(oidUsuarios)).thenReturn(usuarios);
		
		// When
		String viewName = usuariosController.formForUpdate(model, oidUsuarios);
		
		// Then
		assertEquals("usuarios/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(usuarios, (Usuarios) modelMap.get("usuarios"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/usuarios/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Usuarios usuarios = usuariosFactoryForTest.newUsuarios();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Usuarios usuariosCreated = new Usuarios();
		when(usuariosService.create(usuarios)).thenReturn(usuariosCreated); 
		
		// When
		String viewName = usuariosController.create(usuarios, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/usuarios/form/"+usuarios.getOidUsuarios(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(usuariosCreated, (Usuarios) modelMap.get("usuarios"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Usuarios usuarios = usuariosFactoryForTest.newUsuarios();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = usuariosController.create(usuarios, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("usuarios/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(usuarios, (Usuarios) modelMap.get("usuarios"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/usuarios/create", modelMap.get("saveAction"));
		
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

		Usuarios usuarios = usuariosFactoryForTest.newUsuarios();
		
		Exception exception = new RuntimeException("test exception");
		when(usuariosService.create(usuarios)).thenThrow(exception);
		
		// When
		String viewName = usuariosController.create(usuarios, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("usuarios/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(usuarios, (Usuarios) modelMap.get("usuarios"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/usuarios/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "usuarios.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		Usuarios usuarios = usuariosFactoryForTest.newUsuarios();
		BigDecimal oidUsuarios = usuarios.getOidUsuarios();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		Usuarios usuariosSaved = new Usuarios();
		usuariosSaved.setOidUsuarios(oidUsuarios);
		when(usuariosService.update(usuarios)).thenReturn(usuariosSaved); 
		
		// When
		String viewName = usuariosController.update(usuarios, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/usuarios/form/"+usuarios.getOidUsuarios(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(usuariosSaved, (Usuarios) modelMap.get("usuarios"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		Usuarios usuarios = usuariosFactoryForTest.newUsuarios();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = usuariosController.update(usuarios, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("usuarios/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(usuarios, (Usuarios) modelMap.get("usuarios"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/usuarios/update", modelMap.get("saveAction"));
		
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

		Usuarios usuarios = usuariosFactoryForTest.newUsuarios();
		
		Exception exception = new RuntimeException("test exception");
		when(usuariosService.update(usuarios)).thenThrow(exception);
		
		// When
		String viewName = usuariosController.update(usuarios, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("usuarios/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(usuarios, (Usuarios) modelMap.get("usuarios"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/usuarios/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "usuarios.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Usuarios usuarios = usuariosFactoryForTest.newUsuarios();
		BigDecimal oidUsuarios = usuarios.getOidUsuarios();
		
		// When
		String viewName = usuariosController.delete(redirectAttributes, oidUsuarios);
		
		// Then
		verify(usuariosService).delete(oidUsuarios);
		assertEquals("redirect:/usuarios", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		Usuarios usuarios = usuariosFactoryForTest.newUsuarios();
		BigDecimal oidUsuarios = usuarios.getOidUsuarios();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(usuariosService).delete(oidUsuarios);
		
		// When
		String viewName = usuariosController.delete(redirectAttributes, oidUsuarios);
		
		// Then
		verify(usuariosService).delete(oidUsuarios);
		assertEquals("redirect:/usuarios", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "usuarios.error.delete", exception);
	}
	
	
}
