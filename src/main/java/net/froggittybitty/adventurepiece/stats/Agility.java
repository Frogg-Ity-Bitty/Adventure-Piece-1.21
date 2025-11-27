package net.froggittybitty.adventurepiece.stats;

import net.froggittybitty.adventurepiece.AdventurePiece;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import static net.froggittybitty.adventurepiece.DataAttachmentProvider.*;
import static net.minecraft.stats.Stats.*;

@EventBusSubscriber(modid = AdventurePiece.MODID, bus = EventBusSubscriber.Bus.GAME)
public class Agility {

    static float distanceSprint;
    static float agiExp;

    static void agilityLevelUp(ServerPlayer player){
        // levels up
        player.setData(AGILITY_STAT, player.getData(AGILITY_STAT) + 1f);

        // real fast, so fast I-frames are the bottleneck
        player.getAttribute(Attributes.ATTACK_SPEED)
                .setBaseValue(4 + (4 / player.getData(AGILITY_STAT)));

        // good speed, doesnt loose control
        player.getAttribute(Attributes.MOVEMENT_SPEED)
                .setBaseValue(0.1 + (0.001 * player.getData(AGILITY_STAT)));
        // double speed at lvl 300 (leaves room for fishmen
        player.getAttribute(NeoForgeMod.SWIM_SPEED)
                .setBaseValue(1 + (0.003 * player.getData(AGILITY_STAT)));

        // 1.7 blocks at lvl 300 (5.5 block vertical)
        player.getAttribute(Attributes.STEP_HEIGHT)
                .setBaseValue(0.5 + (0.004 * player.getData(AGILITY_STAT)));

        // 1 at lvl 300 (
        player.getAttribute(Attributes.JUMP_STRENGTH)
                .setBaseValue(0.419 + (0.00193 * player.getData(AGILITY_STAT)));
        // 60% reduction at lvl 300
        player.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER)
                .setBaseValue(1 - (0.002 * player.getData(AGILITY_STAT)));

        player.setData(AGILITY_EXP_NEEDED, player.getData(AGILITY_EXP) + 30 + (5 * player.getData(AGILITY_STAT)));
    }

    @SubscribeEvent
    static void agilityExpJumping(LivingEvent.LivingJumpEvent event){
        if(event.getEntity() instanceof ServerPlayer player){
            agiExp++;
            displayAgility(player);
        }
    }

    @SubscribeEvent
    static void agilityExpRunning(PlayerTickEvent.Post event){
        if(event.getEntity() instanceof ServerPlayer player){
            distanceSprint = (player.getStats().getValue(CUSTOM.get(SPRINT_ONE_CM)) / 100f);
            player.setData(AGILITY_EXP, distanceSprint + agiExp);
            if(player.getData(AGILITY_EXP) >= player.getData(AGILITY_EXP_NEEDED)
                    && player.getData(AGILITY_STAT) < player.getData(AGILITY_CAP)){
                agilityLevelUp(player);
                displayAgility(player);
            }
        }
    }

    static void displayAgility(ServerPlayer player){
        if(player != null) {
            player.sendSystemMessage(Component.literal("Agility: " + player.getData(AGILITY_STAT)));
            player.sendSystemMessage(Component.literal("Agility exp: " + player.getData(AGILITY_EXP)));
            player.sendSystemMessage(Component.literal("Agility exp needed: " + player.getData(AGILITY_EXP_NEEDED)));
        }
    }
}
