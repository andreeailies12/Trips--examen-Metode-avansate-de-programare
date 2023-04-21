package com.example.examen;

import com.example.examen.domain.Excursie;
import com.example.examen.domain.validators.ExcursieValidator;
import com.example.examen.repository.ExcursieDataBase;
import com.example.examen.repository.Repository;
import com.example.examen.service.ServiceExcursie;
import com.example.examen.ui.UI;


public class Main {
  public static void main(String[] args) {
   System.out.println("Hello world!");
   String username = "postgres";
   String pasword = "andreea";
   String url = "jdbc:postgresql://localhost:5432/examen";
   Repository<Long, Excursie> excursiedb =
           new ExcursieDataBase(url, username, pasword, new ExcursieValidator());
   ServiceExcursie excursieservice = new ServiceExcursie(excursiedb, new ExcursieValidator());
   UI ui = new UI(excursieservice);
   ui.start();
   System.out.println("Hello world!");
   //excursiedb.findAll().forEach(x-> System.out.println(x));
  }
}
