package efe.crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import efe.crm.bean.Action;

public interface ActionDao  extends JpaRepository<Action, Integer> {

	List<Action> findByEffectueFalseOrderByDateLimiteDesc();
}
