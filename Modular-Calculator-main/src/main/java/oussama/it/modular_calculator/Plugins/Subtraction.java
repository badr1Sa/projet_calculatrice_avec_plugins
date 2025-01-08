package oussama.it.modular_calculator.Plugins;

import oussama.it.modular_calculator.Command;

public class Subtraction implements Command {

    @Override
    public double execute(double... args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("La soustraction nécessite au moins deux arguments.");
        }

        double result = args[0];
        for (int i = 1; i < args.length; i++) {
            result -= args[i];
        }
        return result;
    }

    @Override
    public String getOperationName() {
        return "Subtraction";
    }
}
