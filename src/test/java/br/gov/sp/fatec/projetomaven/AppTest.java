package br.gov.sp.fatec.projetomaven;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testSum()
    {
        final Calculator calc = new Calculator();
        assertEquals(2.0, calc.sum(), 0);
    }

    @Test
    public void testSubtract() {
        final Calculator calc = new Calculator();
        assertEquals(0.0, calc.subtract(), 0);
    }

    @Test
    public void testMultiply() {
        final Calculator calc = new Calculator();
        assertEquals(1.0, calc.multiply(), 0);
    }

    @Test
    public void testDivide() {
        final Calculator calc = new Calculator();
        assertEquals(1.0,  calc.divide(), 0);
    }
}
