package me.tague.endergrab.mixin;

import me.tague.endergrab.PickUpEntityGoal;
import me.tague.endergrab.PlaceEntityGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
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

    @Inject(method="initGoals", at=@At("HEAD"))
    void initGoalsHead(CallbackInfo ci) {
        this.goalSelector.add(10, new PlaceEntityGoal((EndermanEntity) (Object)this));
        this.goalSelector.add(11, new PickUpEntityGoal((EndermanEntity) (Object)this));
    }
}
