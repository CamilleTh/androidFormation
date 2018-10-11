package lu.intech.calculettebindservice.services;

public class Result {

    private char operation;
    private Double resultat;

    public Result(char operation, Double resultat) {
        this.operation = operation;
        this.resultat = resultat;
    }

    public Double getResultat() {
        return resultat;
    }

    public char getOperation() {
        return operation;
    }
}
