package socketsudp;

public class Operaciones {

    private int numero;

    public void insertarNumero(int numero) {
        this.numero = numero;
    }

    public long calcularFibonacci() {
        return fibonacci(this.numero);
    }

    private long fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public long calcularFactorial() {
        return factorial(this.numero);
    }

    private long factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }

    public long calcularSumatoria() {
        return sumatoria(this.numero);
    }

    private long sumatoria(int n) {
        return (n * (n + 1)) / 2;
    }
}