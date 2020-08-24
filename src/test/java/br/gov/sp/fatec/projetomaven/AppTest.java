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
    public void testSoma()
    {
        Calculadora calc = new Calculadora();
        assertEquals(2, (int) calc.soma());
    }
}
