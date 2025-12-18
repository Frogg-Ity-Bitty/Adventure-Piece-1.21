package net.froggittybitty.adventurepiece.masteries;

import net.froggittybitty.adventurepiece.AdventurePiece;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import static net.froggittybitty.adventurepiece.DataAttachmentProvider.*;

@EventBusSubscriber(modid = AdventurePiece.MODID, bus = EventBusSubscriber.Bus.GAME)
public class FistMastery {

    static void swordLevelUp(ServerPlayer player){
        player.setData(FIST_MASTERY, player.getData(FIST_MASTERY) + 1f);

        player.setData(FIST_EXP_NEEDED, 20 + (4 * player.getData(FIST_MASTERY)));
    }

    @SubscribeEvent
    static void swordExpStuff(LivingIncomingDamageEvent event){
        if(event.getSource().getDirectEntity() instanceof ServerPlayer player){
            if(player.getMainHandItem().isEmpty()) {
                player.setData(FIST_EXP, player.getData(FIST_EXP) + event.getAmount());
                displaySword(player);
                if (player.getData(FIST_EXP) >= player.getData(FIST_EXP_NEEDED)
                        && player.getData(FIST_MASTERY) < player.getData(SWORD_CAP)) {
                    swordLevelUp(player);
                    player.setData(FIST_EXP, 4f + player.getData(FIST_EXP) - player.getData(FIST_EXP_NEEDED));
                    displaySword(player);
                }
            }
        }
    }

    static void displaySword(ServerPlayer player){
        if(player != null) {
            player.sendSystemMessage(Component.literal("Fist Mastery: " + player.getData(FIST_MASTERY)));
            player.sendSystemMessage(Component.literal("Fist Mastery exp: " + player.getData(FIST_EXP)));
            player.sendSystemMessage(Component.literal("Fist Mastery exp needed: " + player.getData(FIST_EXP_NEEDED)));
            player.sendSystemMessage(Component.literal("//-------------------------------------------------//"));
        }
    }
}
