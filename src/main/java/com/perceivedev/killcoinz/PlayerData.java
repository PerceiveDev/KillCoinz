/**
 * 
 */
package com.perceivedev.killcoinz;

import java.util.UUID;

import com.perceivedev.perceivecore.config.ConfigSerializable;

/**
 * @author Rayzr
 *
 */
public class PlayerData implements ConfigSerializable {

    private UUID id;
    private long coins = 0;

    PlayerData() {
        // Used by ConfigSerializable
    }

    public PlayerData(UUID id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * @return the number of coins this player has
     */
    public long getCoins() {
        return coins;
    }

    /**
     * @param coins the number of coins to set
     */
    public void setCoins(long coins) {
        this.coins = coins;
    }

    /**
     * @param coins the number of coins to add
     */
    public long addCoins(long coins) {
        return (this.coins += coins);
    }

    /**
     * @param coins the number of coins to add
     */
    public boolean removeCoins(long coins) {
        if (coins > this.coins) {
            // More than we have!
            return false;
        }
        this.coins -= coins;
        return true;
    }

}
