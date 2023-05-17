package com.example.hw14.Controller;

import com.example.hw14.ApiResponse.ApiResponse;
import com.example.hw14.Model.Employees;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeesController {
    ArrayList<Employees> employee=new ArrayList<>();

    @GetMapping("/get")
    public ArrayList getEmployee(){
        return employee;
    }

    @PostMapping("/add")
    public ResponseEntity addEmployee(@Valid @RequestBody Employees employees, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        employee.add(employees);
        return ResponseEntity.status(200).body(new ApiResponse("Employee added"));
    }

    @PutMapping ("/update/{index}")
    public ResponseEntity updateEmployee(@Valid @RequestBody Employees employees, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(message));
        }
        employee.add(employees);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Employee updated"));
    }

    @DeleteMapping  ("/delete/{index}")
    public ResponseEntity deleteEmployee(@PathVariable int index){

        employee.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Employee deleted"));
    }

    @PutMapping("/apply/{index}")
    public ResponseEntity applyForLeave(@PathVariable int index) {

        if (employee.get(index).isOnLeave()) {

            return ResponseEntity.status(400).body(new ApiResponse("Employee is already on leave "));
        }
        if (employee.get(index).getAnnualLeave() == 0) {
            return ResponseEntity.status(400).body(new ApiResponse("Employee has no more annual leave days"));

        }
        employee.get(index).setOnLeave(true);
        employee.get(index).setAnnualLeave(employee.get(index).getAnnualLeave() - 1);
        return ResponseEntity.status(200).body(new ApiResponse("Leave applied successfully"));

    }








}
