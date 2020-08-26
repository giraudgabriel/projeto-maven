package br.gov.sp.fatec.projetomaven;

public class Calculator {
    private float n1, n2;

    public Calculator(float n1, float n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    public Calculator() {
        this(1, 1);
    }

    public float sum() {
        return n1 + n2;
    }

    public float subtract() {
        return n1 - n2;
    }

    public float divide() {
        return n1/n2;
    }

    public float multiply() {
        return n1 * n2;
    }
}
