package br.com.bridge.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.bridge.domain.vo.v1.PessoaJuridicaVO;
import br.com.bridge.service.PessoaJuridicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Empresa Endpoint")
@RestController
@RequestMapping("/empresas/v1")
public class PessoaJuridicaController {
	
	@Autowired
	PessoaJuridicaService service;
	
	@CrossOrigin("localhost:8080")
	@RequestMapping(method = RequestMethod.GET, produces = {"application/json", "application/xml"})
	@Operation(summary="Listar todas as empresas")
	@ResponseStatus(value = HttpStatus.OK)
	public List<PessoaJuridicaVO> findAll() {
		List<PessoaJuridicaVO> empresasVO = service.findAll();
		empresasVO.stream().forEach(e -> e.add(linkTo(methodOn(PessoaJuridicaController.class).findById(e.getKey())).withSelfRel()));
		return empresasVO;
	}
	
	@CrossOrigin("localhost:8080")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
	@Operation(summary="Listar empresas por ID")
	@ResponseStatus(value = HttpStatus.OK)
	public PessoaJuridicaVO findById(@PathVariable("id") Long id) {
		PessoaJuridicaVO empresaVO = service.findById(id);
		empresaVO.add(linkTo(methodOn(PessoaJuridicaController.class).findById(id)).withSelfRel());
		return empresaVO;
	}
	
	@PostMapping(consumes = {"application/json","application/xml"}, produces = {"application/json","application/xml"})
	@Operation(summary="Cadastrar nova empresa")
	@ResponseStatus(value = HttpStatus.CREATED)
	public PessoaJuridicaVO create(@Valid @RequestBody PessoaJuridicaVO empresa) {
		PessoaJuridicaVO empresaVO = service.insert(empresa);
		empresaVO.add(linkTo(methodOn(PessoaJuridicaController.class).findById(empresaVO.getKey())).withSelfRel());
		return empresaVO;
	}
	
	@PutMapping(consumes = {"application/json","application/xml"}, produces = {"application/json","application/xml"})
	@Operation(summary="Atualizar dados de empresa")
	@ResponseStatus(value = HttpStatus.OK)
	public PessoaJuridicaVO update(@Valid @RequestBody PessoaJuridicaVO empresa) {
		PessoaJuridicaVO empresaVO = service.update(empresa);
		empresaVO.add(linkTo(methodOn(PessoaJuridicaController.class).findById(empresaVO.getKey())).withSelfRel());
		return empresaVO;
	}
	
	@DeleteMapping(value="/{id}")
	@Operation(summary="Deletar empresas por ID")
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

}
