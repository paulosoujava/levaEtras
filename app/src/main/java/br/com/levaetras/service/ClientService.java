package br.com.levaetras.service;

import java.util.ArrayList;
import java.util.List;

import br.com.levaetras.model.Address;
import br.com.levaetras.model.Client;
import br.com.levaetras.utils.Consts;

public class ClientService {

    public static List<Client> getClient() {

        Client c = new Client();
        Client c1 = new Client();
        Client c2 = new Client();
        List<Client> list = new ArrayList<>();

        c.setEmail("email");
        c.setName("Paulo");
        c.setCpf("123.123.123-12");
        c.setPhone("48 996297813");
        c.setPhoto("https://cptstatic.s3.amazonaws.com/imagens/produtos/480px/5712/treinamento-de-motorista-particular-01.jpg");

        c1.setEmail("email");
        c1.setName("Jose");
        c1.setCpf("123.123.123-12");
        c1.setPhone("48 996297813");
        c1.setPhoto("https://cptstatic.s3.amazonaws.com/imagens/produtos/480px/5712/treinamento-de-motorista-particular-01.jpg");


        c2.setEmail("email");
        c2.setName("Pedro");
        c2.setCpf("123.123.123-12");
        c2.setPhone("48 996297813");
        c2.setPhoto("https://cptstatic.s3.amazonaws.com/imagens/produtos/480px/5712/treinamento-de-motorista-particular-01.jpg");


        Address a = new Address();
        a.setAddress("Rua Repouso");
        a.setCity("Rio de Janeiro");
        a.setNeighborhood("Jardim Guanabara");
        a.setNumber("102");
        a.setState("RJ");
        a.setZip("123.001");
        a.setState(Consts.CONFIRMED);

        c.setAddress(a);
        c1.setAddress(a);
        c2.setAddress(a);


        list.add(c);
        list.add(c1);
        list.add(c2);


        return list;

    }


}
