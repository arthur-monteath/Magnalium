package com.magnalium.engine.core;

public class Engine implements Runnable {
    private final int fps;
    private final int ups;
    private final GameLoopListener listener;
    private Thread thread;

    public Engine(int fps, int ups, GameLoopListener listener) {
        this.fps = fps;
        this.ups = ups;
        this.listener = listener;
    }

    /**
     * Starts the engine on its own thread.
     */
    public void start() {
        thread = new Thread(this, "Game-Loop-Thread");
        thread.start();
    }

    @Override
    public void run() {
        final double timePerFrame = 1_000_000_000.0 / fps;
        final double timePerUpdate = 1_000_000_000.0 / ups;
        long previousTime = System.nanoTime();
        double deltaU = 0, deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();
            long elapsed = currentTime - previousTime;
            previousTime = currentTime;

            deltaU += elapsed / timePerUpdate;
            deltaF += elapsed / timePerFrame;

            if (deltaU >= 1) {
                listener.update(elapsed / 1_000_000_000.0);
                deltaU--;
            }

            if (deltaF >= 1) {
                listener.render();
                deltaF--;
            }
        }
    }
}