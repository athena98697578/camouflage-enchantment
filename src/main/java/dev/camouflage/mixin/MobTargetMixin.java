package dev.camouflage.mixin;

import dev.camouflage.CamouflageEnchantment;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public abstract class MobTargetMixin {
    @Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
    private void camouflage$ignoreEnchantedPlayer(LivingEntity target, CallbackInfo ci) {
        if (target instanceof Player player
                && CamouflageEnchantment.isWearingCamouflageHelmet(player)) {
            ci.cancel();
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void camouflage$dropExistingTarget(CallbackInfo ci) {
        Mob mob = (Mob) (Object) this;
        LivingEntity target = mob.getTarget();

        if (target instanceof Player player
                && CamouflageEnchantment.isWearingCamouflageHelmet(player)) {
            mob.setTarget(null);
        }
    }
}
