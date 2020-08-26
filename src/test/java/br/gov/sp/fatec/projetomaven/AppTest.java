package br.gov.sp.fatec.projetomaven;

import org.junit.Test;

import static org.junit.Assert.*;

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
        Calculator calc = new Calculator();
        assertEquals(2.0, calc.sum(), 0);
    }
    @Test
    public void testSubtract()
    {
        Calculator calc = new Calculator();
        assertEquals(0.0, calc.subtract(), 0);
    }
    @Test
    public void testMultiply()
    {
        Calculator calc = new Calculator();
        assertEquals(1.0, calc.multiply(), 0);
    }
    @Test
    public void testDivide()
    {
        Calculator calc = new Calculator();
        assertEquals(1.0,  calc.divide(), 0);
    }
}
