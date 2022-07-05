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

import br.com.bridge.domain.vo.v1.CursoVO;
import br.com.bridge.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Curso Endpoint")
@RestController
@RequestMapping("/cursos/v1")
public class CursoController {

	@Autowired
	CursoService service;

	@CrossOrigin("localhost:8080")
	@RequestMapping(method = RequestMethod.GET, produces = {"application/json", "application/xml"})
	@Operation(summary="Listar todos os cursos")
	@ResponseStatus(value = HttpStatus.OK)
	public List<CursoVO> findAll() {
		List<CursoVO> cursosVO = service.findAll();
		cursosVO.stream().forEach(c -> c.add(linkTo(methodOn(CursoController.class).findById(c.getKey())).withSelfRel()));
		return cursosVO;
	}

	@CrossOrigin("localhost:8080")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
	@Operation(summary="Listar cursos por ID")
	@ResponseStatus(value = HttpStatus.OK)
	public CursoVO findById(@PathVariable("id") Long id) {
		CursoVO cursoVO = service.findById(id);
		cursoVO.add(linkTo(methodOn(CursoController.class).findById(id)).withSelfRel());
		return cursoVO;
	}

	@PostMapping(consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	@Operation(summary="Cadastrar novo curso")
	@ResponseStatus(value = HttpStatus.CREATED)
	public CursoVO create(@Valid @RequestBody CursoVO curso) {
		CursoVO cursoVO = service.insert(curso);
		cursoVO.add(linkTo(methodOn(CursoController.class).findById(cursoVO.getKey())).withSelfRel());
		return cursoVO;
	}

	@PutMapping(consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	@Operation(summary="Atualizar dados de curso")
	@ResponseStatus(value = HttpStatus.OK)
	public CursoVO update(@Valid @RequestBody CursoVO curso) {
		CursoVO cursoVO = service.update(curso);
		cursoVO.add(linkTo(methodOn(CursoController.class).findById(cursoVO.getKey())).withSelfRel());
		return cursoVO;
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary="Deletar cursos por ID")
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

}
