package net.froggittybitty.adventurepiece.stats;

import net.froggittybitty.adventurepiece.AdventurePiece;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import static net.froggittybitty.adventurepiece.DataAttachmentProvider.*;
import static net.froggittybitty.adventurepiece.DataAttachmentProvider.HAKI_EXP_NEEDED;

@EventBusSubscriber(modid = AdventurePiece.MODID, bus = EventBusSubscriber.Bus.GAME)
public class Haki {

    static int counter = 0;

    @SubscribeEvent
    static void hakiCharge(PlayerTickEvent.Post event){
        Player player = event.getEntity();
        if(player != null && !player.level().isClientSide()){
            if(player.getData(HAKI_AMOUNT) < player.getData(HAKI_AMOUNT_CAP)){
                counter++;
                if(counter >= 4) {
                    player.setData(HAKI_AMOUNT, Math.min(player.getData(HAKI_AMOUNT) + 1f, player.getData(HAKI_AMOUNT_CAP)));
                    counter = 0;
                }
            }
        }
    }

    static void hakiLevelUp(ServerPlayer player){
        player.setData(HAKI_STAT, player.getData(HAKI_STAT) + 1);
        // 3000 at lvl 300
        player.setData(HAKI_AMOUNT_CAP, 10 * player.getData(HAKI_STAT));

        player.setData(HAKI_EXP_NEEDED, 40 + (4 * player.getData(HAKI_STAT)));
    }

    @SubscribeEvent
    static void hakiExpStuff(LivingDamageEvent.Post event){
        if(event.getSource().getDirectEntity() instanceof ServerPlayer player){
            player.setData(HAKI_EXP, player.getData(HAKI_EXP) + 1);
            displayHaki(player);
            if(player.getData(HAKI_EXP) >= player.getData(HAKI_EXP_NEEDED)
                    && player.getData(HAKI_STAT) < player.getData(HAKI_CAP)){
                hakiLevelUp(player);
                player.setData(HAKI_EXP, 4 + player.getData(HAKI_EXP) - player.getData(HAKI_EXP_NEEDED));
                displayHaki(player);
            }
        }
        if(event.getEntity() instanceof ServerPlayer player){
            player.setData(HAKI_EXP, player.getData(HAKI_EXP) + 1);
            displayHaki(player);
            if(player.getData(HAKI_EXP) >= player.getData(HAKI_EXP_NEEDED)
                    && player.getData(HAKI_STAT) < player.getData(HAKI_CAP)){
                hakiLevelUp(player);
                player.setData(HAKI_EXP, 4 + player.getData(HAKI_EXP) - player.getData(HAKI_EXP_NEEDED));
                displayHaki(player);
            }
        }
    }

    static void displayHaki(ServerPlayer player){
        if(player != null) {
            player.sendSystemMessage(Component.literal("Haki Amount: " + player.getData(HAKI_AMOUNT)));
            player.sendSystemMessage(Component.literal("Haki Amount Max: " + player.getData(HAKI_AMOUNT_CAP)));
            player.sendSystemMessage(Component.literal("Haki: " + player.getData(HAKI_STAT)));
            player.sendSystemMessage(Component.literal("Haki exp: " + player.getData(HAKI_EXP)));
            player.sendSystemMessage(Component.literal("Haki exp needed: " + player.getData(HAKI_EXP_NEEDED)));
        }
    }
}
