package com.tarefa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarefa.entity.Tarefa;
import com.tarefa.service.TarefaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
	private final TarefaService tarefaService;

	@Autowired
	public  TarefaController( TarefaService tarefaService) {
		this.tarefaService = tarefaService;
	}

	@GetMapping("/{id}")
	public ResponseEntity< Tarefa> getTarefaById(@PathVariable Long id) {
		 Tarefa tarefa = tarefaService.getTarefaById(id);
		if (tarefa != null) {
			return ResponseEntity.ok(tarefa);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List< Tarefa>> getAllTarefas() {
		List<Tarefa> tarefas = tarefaService.getAllTarefa();
		return ResponseEntity.ok(tarefas);
	}

	@PostMapping("/")
	public ResponseEntity<Tarefa> criarTarefas(@RequestBody @Valid Tarefa tarefa) {
		Tarefa criarTarefa = tarefaService.salvarTarefa(tarefa);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarTarefa);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Tarefa> updateTarefa(@PathVariable Long id, @RequestBody @Valid Tarefa tarefa) {
		Tarefa updatedTarefa = tarefaService.updateTarefa(id, tarefa);
		if (updatedTarefa != null) {
			return ResponseEntity.ok(updatedTarefa);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Tarefa> deleteTarefa(@PathVariable Long id) {
		boolean deleted = tarefaService.deleteTarefa(id);
		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
