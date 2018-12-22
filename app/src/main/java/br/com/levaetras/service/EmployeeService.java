package br.com.levaetras.service;

import java.util.ArrayList;
import java.util.List;

import br.com.levaetras.model.Address;
import br.com.levaetras.model.Employee;
import br.com.levaetras.utils.Consts;

public class EmployeeService {

    public static List<Employee> getEmployee(){
        List<Employee> employees = new ArrayList<>();
        Employee e = new Employee();
        e.setCnh("123123123");
        e.setCnhType("AB");
        e.setCpf("123.123.122-12");
        e.setDateBirthday("12/12/2018");
        e.setName("Paulo");
        e.setStatus(Consts.STATUS_START);
        e.setUrlPhoto("https://cptstatic.s3.amazonaws.com/imagens/produtos/480px/5712/treinamento-de-motorista-particular-01.jpg");
        Address a = new Address();
        a.setAddress("Rua Repouso");
        a.setCity("Rio de Janeiro");
        a.setNeighborhood("Jardim Guanabara");
        a.setNumber("102");
        a.setState("RJ");
        a.setZip("123.001");
        e.setAddress(a);

        for( int i =0; i < 10; i ++)
            employees.add(e);

        return employees;

    }
}
