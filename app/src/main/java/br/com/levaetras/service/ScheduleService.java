package br.com.levaetras.service;

import java.util.ArrayList;
import java.util.List;

import br.com.levaetras.model.Address;
import br.com.levaetras.model.Client;
import br.com.levaetras.model.Employee;
import br.com.levaetras.model.Schedule;
import br.com.levaetras.utils.Consts;

public class ScheduleService {

    public static Schedule getScheduleConfirmed(){
        Schedule schedules = new Schedule();
        Client c = new Client();
        List<Client> list = new ArrayList<>();
        c.setEmail("email");
        c.setName("PAulo");
        c.setCpf("123.123.123-12");
        c.setPhone("48 996297813");
        c.setPhoto("https://cptstatic.s3.amazonaws.com/imagens/produtos/480px/5712/treinamento-de-motorista-particular-01.jpg");
        Address a = new Address();
        a.setAddress("Rua Repouso");
        a.setCity("Rio de Janeiro");
        a.setNeighborhood("Jardim Guanabara");
        a.setNumber("102");
        a.setState("RJ");
        a.setZip("123.001");
        a.setState(Consts.CONFIRMED);
        c.setAddress(a);

        schedules.setDay("Data: 01");
        schedules.setHour("13:00 Hs");
        schedules.setMonth("01");
        schedules.setQdtdVacancy(5);
        for (int i = 0; i < 4; i++)
            list.add(c);

        schedules.setClients(list);
        return schedules;

    }

    public static Schedule getScheduleNotConfirmed(){
        Schedule schedules = new Schedule();
        Client c = new Client();
        Client c1 = new Client();
        List<Client> list = new ArrayList<>();

        c.setEmail("email");
        c.setName("Paulo");
        c.setCpf("123.123.123-12");
        c.setPhone("48 996297813");
        c.setPhoto("https://cptstatic.s3.amazonaws.com/imagens/produtos/480px/5712/treinamento-de-motorista-particular-01.jpg");
        c.setStatus(Consts.WAITING);

        c1.setEmail("email");
        c1.setName("Arnaldo");
        c1.setCpf("123.123.123-12");
        c1.setPhone("48 996297813");
        c1.setPhoto("https://cptstatic.s3.amazonaws.com/imagens/produtos/480px/5712/treinamento-de-motorista-particular-01.jpg");
        c1.setStatus(Consts.CONFIRM);

        Address a = new Address();
        a.setAddress("Rua Repouso");
        a.setCity("Rio de Janeiro");
        a.setNeighborhood("Jardim Guanabara");
        a.setNumber("102");
        a.setState("RJ");
        a.setZip("123.001");
        a.setState(Consts.WAITING);

        c.setAddress(a);
        c1.setAddress(a);

        schedules.setDay("Data: 01");
        schedules.setHour("13:00 Hs");
        schedules.setMonth("01");
        schedules.setQdtdVacancy(5);

            list.add(c);
            list.add(c1);

        schedules.setClients(list);
        return schedules;

    }

    public static List<Schedule> getSchedule(){
        Schedule schedules = new Schedule();
        List<Schedule> list = new ArrayList<>();
        schedules.setDay("Data: 11/ 01/2019");
        schedules.setHour("13:00 Hs");
        schedules.setMonth("JANEIRO");
        schedules.setDesc("Aqui vai uma descrição com restrição de caracteres");
        schedules.setQdtdVacancy(5);
        for (int i = 0; i < 4; i++)
            list.add(schedules);

        return list;

    }
}
