package fr.kara.heria.rushffa.data.api.kit;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlayerKits {

    private int sword;
    private int pickaxe;
    private int apple;
    private int tnt;
    private int flint;
    private int blocks;

    public PlayerKits() {
        this.sword = 0;
        this.pickaxe = 1;
        this.apple = 2;
        this.tnt = 3;
        this.flint = 4;
        this.blocks = 5;
    }
}
