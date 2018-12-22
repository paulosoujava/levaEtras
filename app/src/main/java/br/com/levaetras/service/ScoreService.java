package br.com.levaetras.service;

import java.util.ArrayList;
import java.util.List;

import br.com.levaetras.model.Score;

public class ScoreService {

    public static List<Score> getScore(){

        List<Score> list = new ArrayList<>();
        Score s = new Score();
        Score s1 = new Score();


        s.setNameClient("Paulo");
        s.setScore("3.5");
        s.setDesc("Péssimo atendimento, nunca mais utilizarei os seus serviços");
        s.setAnswer("Pedimos desculpa pelo acontecido");


        s1.setNameClient("Jose");
        s1.setScore("4.5");
        s1.setDesc("Parabéns excelete atendimento");
        s1.setAnswer("Agradecemos a preferencia");

        for( int i = 0; i < 5; i++ ) {
            list.add(s);
            list.add(s1);
        }

        return list;
    }
}
