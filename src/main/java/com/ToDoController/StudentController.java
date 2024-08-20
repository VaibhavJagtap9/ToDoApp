package com.ToDoController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.ToDoEntity.Student;
import com.ToDoService.StudentService;

@org.springframework.stereotype.Controller
public class StudentController {

    @Autowired
    private StudentService service;
    
    @GetMapping("/home")
    public String home() {
        return "home"; // view page html file ==> home.html
    }
    
    // Get student details
    @GetMapping("/students")
    public String getAllStudents(Model model) {
        model.addAttribute("students", service.getAllStudents());
        return "students";
    }
    
    // Registration form
    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        Student student = new Student(); // to hold student object
        model.addAttribute("student", student);
        return "create-student";
    }
    
    // Add student button
    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        service.saveStudent(student);
        return "redirect:/students";
    }
    
    // Edit student form
    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable int id, Model model) {
        model.addAttribute("student", service.getById(id));
        return "edit_student";
    }
    
    // Update student details
    @PostMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable int id, @ModelAttribute("student") Student student) {
        Student existingStudent = service.getById(id);
        if (existingStudent != null) {
            existingStudent.setFirstName(student.getFirstName());
            existingStudent.setLastName(student.getLastName());
            existingStudent.setEmail(student.getEmail());
            service.saveStudent(existingStudent);
        }
        return "redirect:/students";
    }
    
    // Delete student by ID
    @GetMapping("/students/{id}")
    public String deleteById(@PathVariable int id) {
        service.deleteById(id);
        return "redirect:/students";
    }
}
