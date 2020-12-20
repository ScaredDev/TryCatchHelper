package net.scareddev.catchhelper;

/**
 * @author ScaredDev
 * @created on Dez, 2020
 */
public class Example {

    public static void main(String[] args) {
        Try.run(() -> {
            double lol = Double.parseDouble(null);
        }).onFailure(throwable -> System.out.println("Error")); // Error

        Try.run(() -> {
            double right = 2.3454;
            System.out.println("" + right);
        }).onFailure(Throwable::printStackTrace); // No Error
    }
}
