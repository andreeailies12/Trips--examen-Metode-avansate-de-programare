package com.example.examen.domain.validators;

import com.example.examen.domain.Excursie;

public class ExcursieValidator implements Validator<Long, Excursie> {
    public void validate(Excursie entity) throws ValidationException {

        String oras = entity.getOras();
        String atractie = entity.getAtractie();
        String categorie = entity.getCategorie();
        Double pret = entity.getPret();
        String regex = "[0-9]*$";
        String regexName = "^[A-Za-z0-9 -]*$";


        String errors = "";


        if (!oras.matches(regexName)) {
            errors += "Oras invalid\n";
        }
        if (!atractie.matches(regexName)) {
            errors += "Atractie invalid\n";
        }

//        if (!categorie.matches(regex)) {
//            errors += "Categorie invalida\n";
//        }
//        if(pret >0)
//        {errors += "Pret invalid\n";
//        }

        if (!errors.equals(""))
        {
            throw new ValidationException(errors);
        }
    }
}
