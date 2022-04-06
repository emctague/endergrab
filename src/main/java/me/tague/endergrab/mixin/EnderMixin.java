package me.tague.endergrab.mixin;

import me.tague.endergrab.PickUpEntityGoal;
import me.tague.endergrab.PlaceEntityGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndermanEntity.class)
public abstract class EnderMixin extends HostileEntity {
    protected EnderMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals", at = @At("HEAD"))
    void initGoalsHead(CallbackInfo ci) {
        this.goalSelector.add(10, new PlaceEntityGoal((EndermanEntity) (Object) this));
        this.goalSelector.add(11, new PickUpEntityGoal((EndermanEntity) (Object) this));
    }

    @Override
    public double getMountedHeightOffset() {
        return 0.3;
    }

    @Override
    public void updatePassengerPosition(Entity passenger) {
        if (!this.hasPassenger(passenger)) {
            return;
        }
        double d = this.getY() + this.getMountedHeightOffset() + passenger.getHeightOffset();
        var look = Vec3d.fromPolar(this.getPitch(), this.getYaw());
        passenger.setPosition(this.getX() + look.x, d, this.getZ() + look.z);
    }
}
