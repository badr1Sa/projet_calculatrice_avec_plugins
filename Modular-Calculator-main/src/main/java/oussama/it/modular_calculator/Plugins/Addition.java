package oussama.it.modular_calculator.Plugins;

import oussama.it.modular_calculator.Command;

public class Addition implements Command {

    @Override
    public double execute(double... args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("L'addition nécessite au moins deux arguments.");
        }

        double sum = 0;
        for (double num : args) {
            sum += num;
        }
        return sum;
    }

    @Override
    public String getOperationName() {
        return "Addition";
    }
}
