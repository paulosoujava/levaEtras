package br.com.levaetras.service;

import java.util.ArrayList;
import java.util.List;

import br.com.levaetras.model.Address;
import br.com.levaetras.model.Client;
import br.com.levaetras.model.Contact;
import br.com.levaetras.model.Employee;
import br.com.levaetras.utils.Consts;

public class ContactService {

    public static List<Contact> getContact() {
        List<Contact> list = new ArrayList<>();
        Contact c = new Contact();
        c.setDate_hour("12/01/2019 12:10 ");
        c.setStatus("NOT_READ");
        c.setName("Jenefer");
        c.setUrl_photo("https://cptstatic.s3.amazonaws.com/imagens/produtos/480px/5712/treinamento-de-motorista-particular-01.jpg");

        for (int i = 0; i < 10; i++)
        list.add(c);

        return list;

    }


}
