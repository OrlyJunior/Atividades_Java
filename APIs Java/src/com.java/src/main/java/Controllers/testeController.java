package Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Contorller para testes")
public class testeController {
	
	@GetMapping("/teste")
	@ApiOperation(value = "Método teste", notes = "Deletar após mais testes")
	public String teste() {
		return "teste";
	}
}