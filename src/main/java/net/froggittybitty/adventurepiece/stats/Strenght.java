package net.froggittybitty.adventurepiece.stats;

import net.froggittybitty.adventurepiece.AdventurePiece;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import static net.froggittybitty.adventurepiece.DataAttachmentProvider.*;

@EventBusSubscriber(modid = AdventurePiece.MODID, bus = EventBusSubscriber.Bus.GAME)
public class Strenght {

    // STATS DONT COPY ON DEATH BUT ATTRIBUTES DO

    static void strengthLevelUp(ServerPlayer player){
        // levels up
        player.setData(STRENGTH_STAT, player.getData(STRENGTH_STAT) + 1f);

        // 31 dmg/hit at lvl 300
        player.getAttribute(Attributes.ATTACK_DAMAGE)
                .setBaseValue(1 + (player.getData(STRENGTH_STAT) / 10));

        player.getAttribute(Attributes.ATTACK_KNOCKBACK)
                .setBaseValue((5f / 300f ) * player.getData(STRENGTH_STAT));

        player.getAttribute(Attributes.BLOCK_BREAK_SPEED)
                .setBaseValue(1 + (0.02 * player.getData(STRENGTH_STAT)));

        player.setData(STRENGTH_EXP_NEEDED, 20 + (4 * player.getData(STRENGTH_STAT)));
    }

    @SubscribeEvent
    static void strengthExpStuff(LivingDamageEvent.Post event){
        if(event.getSource().getDirectEntity() instanceof ServerPlayer player){
            player.setData(STRENGTH_EXP, player.getData(STRENGTH_EXP) + event.getNewDamage());
            displayStrength(player);
            if(player.getData(STRENGTH_EXP) >= player.getData(STRENGTH_EXP_NEEDED)
                    && player.getData(STRENGTH_STAT) < player.getData(STRENGTH_CAP)){
                strengthLevelUp(player);
                player.setData(STRENGTH_EXP, 4 + player.getData(STRENGTH_EXP) - player.getData(STRENGTH_EXP_NEEDED));
                displayStrength(player);
            }
        }
    }

    static void displayStrength(ServerPlayer player){
        if(player != null) {
            player.sendSystemMessage(Component.literal("Strenght: " + player.getData(STRENGTH_STAT)));
            player.sendSystemMessage(Component.literal("Strenght exp: " + player.getData(STRENGTH_EXP)));
            player.sendSystemMessage(Component.literal("Strenght exp needed: " + player.getData(STRENGTH_EXP_NEEDED)));
        }
    }
}
