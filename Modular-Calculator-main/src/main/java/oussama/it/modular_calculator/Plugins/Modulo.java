package oussama.it.modular_calculator.Plugins;

import oussama.it.modular_calculator.Command;

public class Modulo implements Command {

    @Override
    public double execute(double... args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Le modulo nécessite au moins deux arguments.");
        }

        double result = args[0];
        for (int i = 1; i < args.length; i++) {
            if (args[i] == 0) {
                throw new ArithmeticException("Division par zéro détectée dans l'opération de modulo.");
            }
            result %= args[i];
        }
        return result;
    }

    @Override
    public String getOperationName() {
        return "Modulo";
    }
}
