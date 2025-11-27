package net.froggittybitty.adventurepiece.stats;

import net.froggittybitty.adventurepiece.AdventurePiece;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import static net.froggittybitty.adventurepiece.DataAttachmentProvider.*;

@EventBusSubscriber(modid = AdventurePiece.MODID, bus = EventBusSubscriber.Bus.GAME)
public class Endurance {

    // STATS DONT COPY ON DEATH BUT ATTRIBUTES DO

    static void enduranceLevelUp(ServerPlayer player){
        // levels up
        player.setData(ENDURANCE_STAT, player.getData(ENDURANCE_STAT) + 1f);
        // 320 HP at lvl 300
        player.getAttribute(Attributes.MAX_HEALTH)
                .setBaseValue(20f + player.getData(ENDURANCE_STAT));

        // 18 armor at lvl 300
        player.getAttribute(Attributes.ARMOR)
                .setBaseValue(0.06f * player.getData(ENDURANCE_STAT));

        // 51% reduction at lvl 300
        player.getAttribute(Attributes.KNOCKBACK_RESISTANCE)
                .setBaseValue(0.0017 * player.getData(ENDURANCE_STAT));
        player.getAttribute(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE)
                .setBaseValue(0.0017 * player.getData(ENDURANCE_STAT));

        player.setData(ENDURANCE_EXP_NEEDED, 20 + (4 * player.getData(ENDURANCE_STAT)));
    }

    @SubscribeEvent
    static void enduranceExpStuff(LivingDamageEvent.Post event){
        if(event.getEntity() instanceof ServerPlayer player){
            player.setData(ENDURANCE_EXP, player.getData(ENDURANCE_EXP) + event.getNewDamage());
            displayEndurance(player);
            if(player.getData(ENDURANCE_EXP) >= player.getData(ENDURANCE_EXP_NEEDED)
                    && player.getData(ENDURANCE_STAT) < player.getData(ENDURANCE_CAP)){
                enduranceLevelUp(player);
                player.setData(ENDURANCE_EXP, 4 + player.getData(ENDURANCE_EXP) - player.getData(ENDURANCE_EXP_NEEDED));
                displayEndurance(player);
            }
        }
    }

    static void displayEndurance(ServerPlayer player){
        if(player != null) {
            player.sendSystemMessage(Component.literal("Endurance: " + player.getData(ENDURANCE_STAT)));
            player.sendSystemMessage(Component.literal("Endurance exp: " + player.getData(ENDURANCE_EXP)));
            player.sendSystemMessage(Component.literal("Endurance exp needed: " + player.getData(ENDURANCE_EXP_NEEDED)));
        }
    }
}
