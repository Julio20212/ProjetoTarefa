package com.tarefa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarefa.entity.Tarefa;

public interface TarefaRepository extends JpaRepository <Tarefa, Long> {

}
