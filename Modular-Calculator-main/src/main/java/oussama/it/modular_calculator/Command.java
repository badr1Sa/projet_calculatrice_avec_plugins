package oussama.it.modular_calculator;

public interface Command {
    double execute(double... args);
    String getOperationName();

}
