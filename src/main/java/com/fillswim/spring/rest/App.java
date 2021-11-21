package com.fillswim.spring.rest;

import com.fillswim.spring.rest.configuration.MyConfig;
import com.fillswim.spring.rest.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);

        // Вывод всех работников
        List<Employee> allEmployees = communication.getAllEmployees();
        for (Employee employee : allEmployees) {
            System.out.println(employee);
        }

        // Получение одного работника
//        Employee employee = communication.getEmployee(1);
//        System.out.println(employee);

        // Создание и добавление работника в БД
//        Employee employee = new Employee("Sveta", "Sokolova", "HR", 900);
//        communication.saveEmployee(employee);

        // Изменение существующего работника в БД
//        Employee employee = new Employee("Sveta", "Sokolova", "IT", 1200);
//        employee.setId(5);
//        communication.saveEmployee(employee);

        // Удаление работника
//        communication.deleteEmployee(5);

    }
}
