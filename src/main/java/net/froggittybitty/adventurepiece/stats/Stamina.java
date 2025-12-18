package net.froggittybitty.adventurepiece.stats;

import net.froggittybitty.adventurepiece.AdventurePiece;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SwordItem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import static net.froggittybitty.adventurepiece.DataAttachmentProvider.*;

@EventBusSubscriber(modid = AdventurePiece.MODID, bus = EventBusSubscriber.Bus.GAME)
public class Stamina {

    // STATS DONT COPY ON DEATH BUT ATTRIBUTES DO

    //stamina is 100 + End + str + agi recharges 1 per second, uses some when dealing damage

    static int counter = 0;

    @SubscribeEvent
    static void staminaCharge(PlayerTickEvent.Post event){
        Player player = event.getEntity();
        if(player != null && !player.level().isClientSide()){
            if(player.getData(STAMINA) < player.getData(STAMINA_CAP)){
                counter++;
                if(counter >= 20) {
                    player.setData(STAMINA, Math.min(player.getData(STAMINA) + 1f, player.getData(STAMINA_CAP)));
                    counter = 0;
                }
            }
        }
    }

    @SubscribeEvent
    static void staminaUse(LivingIncomingDamageEvent event){
        if(event.getSource().getDirectEntity() instanceof ServerPlayer player){
            if(event.getOriginalAmount() > player.getData(STAMINA)){
                event.setAmount(player.getData(STAMINA));
            }
            player.setData(STAMINA, Math.max(player.getData(STAMINA) - event.getOriginalAmount(), 0));
            player.setData(STAMINA_CAP, 100 + player.getData(ENDURANCE_STAT) + player.getData(STRENGTH_STAT) + player.getData(AGILITY_STAT));
            displayStamina(player);
        }
    }

    static void displayStamina(ServerPlayer player){
        if(player != null) {
            player.sendSystemMessage(Component.literal("Stamina: " + player.getData(STAMINA)));
            player.sendSystemMessage(Component.literal("Max Stamina: " + player.getData(STAMINA_CAP)));
            player.sendSystemMessage(Component.literal("//-------------------------------------------------//"));
        }
    }

}
