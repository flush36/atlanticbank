package br.com.bancoatlantico.atlanticbank.security;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.bancoatlantico.atlanticbank.model.Usuario;
import br.com.bancoatlantico.atlanticbank.service.UsuarioService;

@Component
@WebFilter
public class WebFilterInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		try {
			
			if (request.getMethod().equals("OPTIONS")){
                return true;
            }
			
			String token = request.getHeader("Authorization");
			Usuario usuario = usuarioService.findByToken(token);
			request.setAttribute(AllowConfiguration.AUTHETICATION, usuario);
			return true;
		}catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
		} 
	}
}
