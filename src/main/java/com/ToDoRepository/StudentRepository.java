package com.ToDoRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ToDoEntity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	
}