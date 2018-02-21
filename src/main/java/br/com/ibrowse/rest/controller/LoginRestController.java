package br.com.ibrowse.rest.controller;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ibrowse.business.service.UsuariosService;
import br.com.ibrowse.dto.LoginFinanceiroDTO;
import br.com.ibrowse.dto.LoginResponseFinanceiroDTO;

@Controller
public class LoginRestController {

	@Resource
	private UsuariosService usuariosService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponseFinanceiroDTO authentication(@RequestBody LoginFinanceiroDTO loginFinanceiroDTO)
			throws SQLException {
		return usuariosService.login(loginFinanceiroDTO);
	}
}
