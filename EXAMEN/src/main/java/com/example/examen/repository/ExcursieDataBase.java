package com.example.examen.repository;

import com.example.examen.domain.Excursie;
import com.example.examen.domain.validators.Validator;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ExcursieDataBase implements Repository<Long, Excursie>{
    private final String url;
    private final String username;
    private final String password;
    private final Validator<Long, Excursie> validator;

    /**
     * Constructor cu parametri pentru repositoryUtilizator
     *
     * @param url       string
     * @param username  string
     * @param password  string
     * @param validator Validator
     */
    public ExcursieDataBase(String url, String username, String password, Validator<Long, Excursie> validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
    }

    @Override
    public int findId(String oras, String atractie, String categorie, Double pret
    ) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from Excursii where (oras = (?) and atractie = (?) and categorie = (?) and pret" +
                     " = (?))")
        ) {
            statement.setString(1, oras);
            statement.setString(2, atractie);
            statement.setString(3, categorie);
            statement.setDouble(4, pret
            );

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");

                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Excursie findOne(Long id) {

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from Excursii where id = (?)")
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String oras = resultSet.getString("oras");
                String atractie = resultSet.getString("atractie");
                String categorie = resultSet.getString("categorie");
                Double pret = resultSet.getDouble("pret" +
                        "");

                return new Excursie(oras, atractie, categorie, pret);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<Excursie> findbyName(String oras, String atractie) {
        Set<Excursie> excursii = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from Excursii where (oras = (?) and atractie = (?))")) {
            statement.setString(1, oras);
            statement.setString(2, atractie);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String categorie = resultSet.getString("categorie");
                Double pret = resultSet.getDouble("pret" +
                        "");

                Excursie s = new Excursie(oras, atractie, categorie, pret);
                excursii.add(s);
            }
            return excursii;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return excursii;

    }

    @Override
    public Iterable<Excursie> findAll() {

        Set<Excursie> excursii = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from excursii");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                String oras = resultSet.getString("oras");
                String atractie = resultSet.getString("atractie");
                String categorie = resultSet.getString("categorie");
                Double pret = resultSet.getDouble("pret" +
                        "");
                Excursie excursie = new Excursie(oras, atractie, categorie, pret);
                excursii.add(excursie);
            }
            return excursii;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return excursii;
    }

    @Override
    public Excursie save(Excursie entity) {

        String sql = "insert into excursii (oras,atractie,categorie,pret" +
                ") values ( ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, entity.getOras());
            ps.setString(2, entity.getAtractie());
            ps.setString(3, entity.getCategorie());
            ps.setDouble(4, entity.getPret());
            if (ps.executeUpdate() == 0) {
                return entity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Excursie delete(Long id) {

        String sql = "delete from excursii where id=?";

        Excursie user = this.findOne(id);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            if (ps.executeUpdate() == 0) {
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Excursie update(Excursie entity) {
        return null;
    }

}
