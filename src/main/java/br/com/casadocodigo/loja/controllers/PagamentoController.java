package br.com.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.model.CarrinhoCompras;
import br.com.casadocodigo.loja.model.DadosPagamento;
import br.com.casadocodigo.loja.model.Usuario;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
	private CarrinhoCompras carrinho;
	
	@Autowired
	RestTemplate restTemplate;
	
	@SuppressWarnings("unused")
	@Autowired
	private MailSender sender;
	
	@RequestMapping(value="/finalizar", method=RequestMethod.POST)
	public Callable<ModelAndView>  finalizar(@AuthenticationPrincipal Usuario usr, RedirectAttributes model){
		return () -> {
			String uri = "http://book-payment.herokuapp.com/payment";
			
			try {
				String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()), String.class);
				model.addFlashAttribute("sucesso", response);
				System.out.println(response);
				this.carrinho.clean();
//				enviaEmailToUsuario(usr);
				return new ModelAndView("redirect:/produtos");
			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				model.addFlashAttribute("falha", "Valor maior que o permitido");
				return new ModelAndView("redirect:/produtos");
			}
		};
	}
	
	public void enviaEmailToUsuario(Usuario usuario) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Compra Finalizada");
		email.setTo(usuario.getEmail());
		email.setText("Sua compra foi finalizada.\n Valor total de: " + carrinho.getTotal());
		email.setFrom("compras@casadocodigo.com.br");
//		sender.send(email);
		System.out.println("envio de email off");
	}
}