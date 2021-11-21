package com.fillswim.spring.rest;

import com.fillswim.spring.rest.entity.Employee;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {

    private final RestTemplate restTemplate;

    private final String URL = "http://localhost:8080/ZT61_Spring_REST_Server/api/employees";

    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Метод GET        /api/employees                  Получение всех работников
    // Метод GET        /api/employees{employeeID}      Получение одного работника
    // Метод POST       /api/employees                  Добавление работника
    // Метод PUT        /api/employees                  Изменение работника
    // Метод DELETE     /api/employees{employeeID}      Удаление работника


    // Метод GET        /api/employees                  Получение всех работников
    public List<Employee> getAllEmployees() {

        // Отправляется запрос и его результат получается в responseEntity
        ResponseEntity<List<Employee>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {});

        List<Employee> allEmployees = responseEntity.getBody();

        return allEmployees;
    }

    // Метод GET        /api/employees{employeeID}      Получение одного работника
    public Employee getEmployee(int id) {

        Employee employee = restTemplate.getForObject(URL + "/" + id, Employee.class);

        return employee;
    }

    // Сохранение / добавление работника
    // если id=0 - будет отправляться http запрос на создание работника в БД
    // если id!=0 - будет отправляться http запрос на изменение уже существующего работника в БД
    // Метод POST       /api/employees                  Добавление работника
    // Метод PUT        /api/employees                  Изменение работника
    public void saveEmployee(Employee employee) {

        int id = employee.getId();

        if (id == 0) {
            // Добавление нового работника в базу
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, employee, String.class);
            // Вывод добавленного работника на экран
            System.out.println("New employee was added to DB");
            System.out.println(responseEntity.getBody());
        } else {
            // Обновление работника в ДБ
            restTemplate.put(URL, employee);
            System.out.println("Employee with ID = " + id + " was updated");

        }

    }

    // Метод DELETE     /api/employees{employeeID}      Удаление работника
    public void deleteEmployee(int id) {
        restTemplate.delete(URL + "/" + id);
        System.out.println("Employee with id = " + id + " deleted from Database");
    }
}
