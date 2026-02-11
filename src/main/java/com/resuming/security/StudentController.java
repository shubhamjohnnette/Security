package com.resuming.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {




    private List<Student> students = new ArrayList<>(List.of(
            new Student(1,"Navin",60)
    ,new Student(2,"Shubham",70)));


    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request ){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
return students;
    }
}
