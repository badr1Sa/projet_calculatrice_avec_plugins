package oussama.it.modular_calculator.Plugins;

import oussama.it.modular_calculator.Command;

public class Multiplication implements Command {

    @Override
    public double execute(double... args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("La multiplication nécessite au moins deux arguments.");
        }

        double result = 1;
        for (double num : args) {
            result *= num;
        }
        return result;
    }

    @Override
    public String getOperationName() {
        return "Multiplication";
    }
}
