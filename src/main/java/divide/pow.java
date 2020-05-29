package divide;

public class pow {

    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n > 0) {
            return pow(x, n);
        } else {
            return 1 / pow(x, -(long)n);
        }
    }


    private double pow(double x, long n) {

        if (n == 1) return x;

        if (n % 2 == 0) {
            double v = pow(x, n / 2);
            return v * v;
        } else {
            return pow(x, n - 1) * x;
        }
    }
}
