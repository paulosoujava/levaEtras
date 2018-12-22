package br.com.levaetras.service;

import java.util.ArrayList;
import java.util.List;

import br.com.levaetras.model.Address;
import br.com.levaetras.model.Employee;
import br.com.levaetras.model.Zip;
import br.com.levaetras.utils.Consts;

public class ZipService {

    public static List<Zip> getZip(){
        List<Zip> zips = new ArrayList<>();
        Zip z = new Zip();
        z.setAddess("Av. jorge lacerda Costeira");
        z.setZip("88047-001");



        for( int i =0; i < 10; i ++)
            zips.add(z);

        return zips;

    }
}
