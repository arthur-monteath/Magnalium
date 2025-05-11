package com.magnalium.engine.core;

/**
 * Receives callbacks for the game loop.
 */
public interface GameLoopListener {
    /**
     * Called on each update tick. dt is seconds since last update.
     */
    void update(double dt);

    /**
     * Called on each render tick.
     */
    void render();
}