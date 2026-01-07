package com.ganatan.services;

import com.ganatan.models.Essai;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EssaiService {

    private final JdbcTemplate jdbcTemplate;

    public EssaiService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Essai> findAll() {
        // Test : afficher l'utilisateur connecté
        jdbcTemplate.query("SELECT USER FROM DUAL", (rs) -> {
            System.out.println(">>> CONNECTED AS: " + rs.getString(1));
        });

        // Test : afficher le nombre de lignes dans la table ESSAI
        jdbcTemplate.query("SELECT COUNT(*) FROM ESSAI", (rs) -> {
            System.out.println(">>> ROWS IN ESSAI: " + rs.getInt(1));
        });

//        String sql = "SELECT ID, NOM FROM ESSAI";
        String sql = "SELECT ID, NOM FROM ESSAI";
        List<Essai> items = jdbcTemplate.query(sql, rowMapper);
        System.out.println("00000000001:" + items);
        return items;
    }

    private final RowMapper<Essai> rowMapper = (rs, rowNum) ->
        new Essai(rs.getLong("ID"), rs.getString("NOM"));
}
