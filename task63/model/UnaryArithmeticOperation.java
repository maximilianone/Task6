package task63.model;

public class UnaryArithmeticOperation {
    private double value;

    public UnaryArithmeticOperation() {
    }

    public UnaryArithmeticOperation(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public void increment(){
        ++value;
    }

    public void decrement(){
        --value;
    }

    public void changeSign(){
        value=-value;
    }
}
