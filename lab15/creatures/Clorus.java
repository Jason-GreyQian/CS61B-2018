package creatures;

import huglife.*;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;
    private static final double MOVELOSS = 0.03;
    private static final double STAYLOSS = 0.01;
    /**
     * fraction of energy to retain when replicating.
     */
    private double repEnergyRetained = 0.5;
    /**
     * fraction of energy to bestow upon offspring.
     */
    private double repEnergyGiven = 0.5;
    private double moveProbability = 0.5;

    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    /**
     * Called when this creature moves.
     */
    @Override
    public void move() {
        energy -= MOVELOSS;
    }

    /**
     * Called when this creature attacks C.
     *
     * @param c
     */
    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    /**
     * Called when this creature chooses replicate.
     * Must return a creature of the same type.
     */
    @Override
    public Clorus replicate() {
        double childEnergy = energy * repEnergyGiven;
        energy = energy * repEnergyGiven;
        return new Clorus(childEnergy);
    }

    /**
     * Called when this creature chooses stay.
     */
    @Override
    public void stay() {
        energy -= STAYLOSS;
    }

    /**
     * Returns an action. The creature is provided information about its
     * immediate NEIGHBORS with which to make a decision.
     *
     * @param neighbors
     */
    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        if (empties.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }
        List<Direction> plips = getNeighborsOfType(neighbors, "plip");
        if (!plips.isEmpty()) {
            Direction moveDir = HugLifeUtils.randomEntry(plips);
            return new Action(Action.ActionType.ATTACK, moveDir);
        }
        Direction moveDir = HugLifeUtils.randomEntry(empties);
        if (energy >= 1.0) {
            return new Action(Action.ActionType.REPLICATE, moveDir);
        } else {
            return new Action(Action.ActionType.MOVE, moveDir);
        }

    }

    /**
     * Required method that returns a color.
     */
    @Override
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }
}
