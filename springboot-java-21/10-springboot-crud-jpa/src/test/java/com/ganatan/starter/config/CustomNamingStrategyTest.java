package com.ganatan.starter.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomNamingStrategyTest {

    private static JdbcEnvironment jdbcEnvironment;

    @BeforeAll
    static void setup() {
        jdbcEnvironment = mock(JdbcEnvironment.class);
    }

    private Identifier createIdentifier(String text) {
        return Identifier.toIdentifier(text);
    }

    @Test
    void testPostgresqlKeepsTableName() {
        CustomNamingStrategy strategy = new CustomNamingStrategy("postgresql");

        Identifier result = strategy.toPhysicalTableName(createIdentifier("person"), jdbcEnvironment);

        assertEquals("person", result.getText());
    }

    @Test
    void testOracleAddsPrefixToTable() {
        CustomNamingStrategy strategy = new CustomNamingStrategy("oracle");

        Identifier result = strategy.toPhysicalTableName(createIdentifier("person"), jdbcEnvironment);

        assertEquals("starter_person", result.getText());
    }

    @Test
    void testOracleDoesNotDuplicatePrefixForTable() {
        CustomNamingStrategy strategy = new CustomNamingStrategy("oracle");

        Identifier result = strategy.toPhysicalTableName(createIdentifier("starter_person"), jdbcEnvironment);

        assertEquals("starter_person", result.getText());
    }

    @Test
    void testOracleAddsPrefixToOtherTable() {
        CustomNamingStrategy strategy = new CustomNamingStrategy("oracle");

        Identifier result = strategy.toPhysicalTableName(createIdentifier("city"), jdbcEnvironment);

        assertEquals("starter_city", result.getText());
    }

    @Test
    void testPostgresqlKeepsSequenceName() {
        CustomNamingStrategy strategy = new CustomNamingStrategy("postgresql");

        Identifier result = strategy.toPhysicalSequenceName(createIdentifier("person_seq"), jdbcEnvironment);

        assertEquals("person_seq", result.getText());
    }

    @Test
    void testOracleAddsPrefixToSequence() {
        CustomNamingStrategy strategy = new CustomNamingStrategy("oracle");

        Identifier result = strategy.toPhysicalSequenceName(createIdentifier("person_seq"), jdbcEnvironment);

        assertEquals("starter_person_seq", result.getText());
    }

    @Test
    void testOracleDoesNotDuplicatePrefixForSequence() {
        CustomNamingStrategy strategy = new CustomNamingStrategy("oracle");

        Identifier result = strategy.toPhysicalSequenceName(createIdentifier("starter_person_seq"), jdbcEnvironment);

        assertEquals("starter_person_seq", result.getText());
    }

    @Test
    void testNullIdentifierReturnsNull() {
        CustomNamingStrategy strategy = new CustomNamingStrategy("oracle");

        assertAll(
            () -> assertNull(strategy.toPhysicalTableName(null, jdbcEnvironment)),
            () -> assertNull(strategy.toPhysicalSequenceName(null, jdbcEnvironment))
        );
    }
}


//package com.ganatan.starter.config;
//
//import org.hibernate.boot.model.naming.Identifier;
//import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class CustomNamingStrategyTest {
//
//    private static JdbcEnvironment jdbcEnvironment;
//
//    @BeforeAll
//    static void setup() {
//        jdbcEnvironment = mock(JdbcEnvironment.class);
//    }
//
//    @Test
//    void testPostgresqlKeepsName() {
//        CustomNamingStrategy strategy = new CustomNamingStrategy("postgresql");
//        Identifier input = Identifier.toIdentifier("person");
//
//        Identifier result = strategy.toPhysicalTableName(input, jdbcEnvironment);
//
//        assertEquals("person", result.getText());
//    }
//
//    @Test
//    void testOracleAddsPrefix() {
//        CustomNamingStrategy strategy = new CustomNamingStrategy("oracle");
//        Identifier input = Identifier.toIdentifier("person");
//
//        Identifier result = strategy.toPhysicalTableName(input, jdbcEnvironment);
//
//        assertEquals("starter_person", result.getText());
//    }
//
//    @Test
//    void testOracleDoesNotDuplicatePrefix() {
//        CustomNamingStrategy strategy = new CustomNamingStrategy("oracle");
//        Identifier input = Identifier.toIdentifier("starter_person");
//
//        Identifier result = strategy.toPhysicalTableName(input, jdbcEnvironment);
//
//        assertEquals("starter_person", result.getText());
//    }
//
//    @Test
//    void testOtherTableNameUnchangedForPostgresql() {
//        CustomNamingStrategy strategy = new CustomNamingStrategy("postgresql");
//        Identifier input = Identifier.toIdentifier("city");
//
//        Identifier result = strategy.toPhysicalTableName(input, jdbcEnvironment);
//
//        assertEquals("city", result.getText());
//    }
//
//    @Test
//    void testOtherTableNamePrefixedForOracle() {
//        CustomNamingStrategy strategy = new CustomNamingStrategy("oracle");
//        Identifier input = Identifier.toIdentifier("city");
//
//        Identifier result = strategy.toPhysicalTableName(input, jdbcEnvironment);
//
//        assertEquals("starter_city", result.getText());
//    }
//}
