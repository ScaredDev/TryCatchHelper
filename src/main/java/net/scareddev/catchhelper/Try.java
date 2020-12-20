package net.scareddev.catchhelper;

import lombok.AllArgsConstructor;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author ScaredDev
 * @created on Dez, 2020
 */
public class Try {

    public static Failure run(final TryRunnable tryRunnable) {
        Objects.requireNonNull(tryRunnable, "Invalid Runnable ?");
        try {
            tryRunnable.run();
            return new Failure(null);
        } catch (final Throwable throwable) {
            return new Failure(throwable);
        }
    }

    @AllArgsConstructor
    static class Failure {
        private final Throwable throwable;

        public final void onFailure(final Consumer<Throwable> throwableConsumer) {
            if (throwable == null) return;
            throwableConsumer.accept(throwable);
        }
    }

    @FunctionalInterface
    public interface TryRunnable {
        static TryRunnable of(TryRunnable tryRunnable) { return tryRunnable; }

        void run() throws Throwable;

        default Runnable ignored() {
            return () -> {
                try { run(); } catch (final Throwable ignored) {}
            };
        }
    }
}
