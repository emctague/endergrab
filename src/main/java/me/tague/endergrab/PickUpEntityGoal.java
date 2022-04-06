package me.tague.endergrab;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.Random;

public class PickUpEntityGoal extends Goal {
    private final EndermanEntity enderman;

    public PickUpEntityGoal(EndermanEntity enderman) {
        this.enderman = enderman;
    }

    @Override
    public boolean canStart() {
        return !enderman.hasPassengers() &&
                enderman.getRandom().nextInt(PickUpEntityGoal.toGoalTicks(1200)) == 0;
    }

    @Override
    public void tick() {
        Random random = enderman.getRandom();
        World world = enderman.world;

        if (enderman.hasPassengers()) return;

        var others = world.getOtherEntities(enderman,
                Box.from(BlockBox.create(enderman.getBlockPos().add(-2, -2, -2),
                        enderman.getBlockPos().add(2, 5, 2))),
                EntityPredicates.VALID_LIVING_ENTITY.and(EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR));

        if (!others.isEmpty()) {
            others.get(random.nextInt(others.size())).startRiding(enderman, true);
        }
    }
}
