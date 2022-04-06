package me.tague.endergrab;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.EndermanEntity;

public class PlaceEntityGoal extends Goal {
    private final EndermanEntity enderman;

    public PlaceEntityGoal(EndermanEntity enderman) {
        this.enderman = enderman;
    }

    @Override
    public boolean canStart() {
        return enderman.hasPassengers() &&
                (enderman.getRandom().nextInt(PlaceEntityGoal.toGoalTicks(1200)) == 0 || enderman.getCarriedBlock() != null);
    }

    @Override
    public void tick() {
        if (enderman.hasPassengers()) {
            enderman.removeAllPassengers();
        }
    }
}
