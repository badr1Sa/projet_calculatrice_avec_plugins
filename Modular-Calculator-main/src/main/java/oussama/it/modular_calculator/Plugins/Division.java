package oussama.it.modular_calculator.Plugins;

import oussama.it.modular_calculator.Command;

public class Division implements Command {

    @Override
    public double execute(double... args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("La division nécessite au moins deux arguments.");
        }

        double result = args[0];
        for (int i = 1; i < args.length; i++) {
            if (args[i] == 0) {
                throw new ArithmeticException("Division par zéro détectée.");
            }
            result /= args[i];
        }
        return result;
    }

    @Override
    public String getOperationName() {
        return "Division";
    }
}
