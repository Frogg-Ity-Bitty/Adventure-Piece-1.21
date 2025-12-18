package net.froggittybitty.adventurepiece.masteries;

import net.froggittybitty.adventurepiece.AdventurePiece;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.SwordItem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import static net.froggittybitty.adventurepiece.DataAttachmentProvider.*;

@EventBusSubscriber(modid = AdventurePiece.MODID, bus = EventBusSubscriber.Bus.GAME)
public class SwordMastery {

    static float swordMasteryDmgBoostPercent = 1;
    static float weaponDmg;
    static float attributeDmg;

    static void swordLevelUp(ServerPlayer player){
        player.setData(SWORD_MASTERY, player.getData(SWORD_MASTERY) + 1f);

        swordMasteryDmgBoostPercent = 1 + (1 / player.getData(SWORD_MASTERY));

        player.setData(SWORD_EXP_NEEDED, 40 + (4 * player.getData(SWORD_MASTERY)));
    }

    @SubscribeEvent
    static void swordExpStuff(LivingIncomingDamageEvent event){
        if(event.getSource().getDirectEntity() instanceof ServerPlayer player){
            if(player.getMainHandItem().getItem() instanceof SwordItem swordItem) {
                // Separately increases the base weapon damage
                attributeDmg = (float)player.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue();
                weaponDmg = 1f + (event.getOriginalAmount() - attributeDmg);
                event.setAmount((attributeDmg - 1) + (weaponDmg * swordMasteryDmgBoostPercent));

                player.setData(SWORD_EXP, player.getData(SWORD_EXP) + event.getAmount());
                displaySword(player);
                if (player.getData(SWORD_EXP) >= player.getData(SWORD_EXP_NEEDED)
                        && player.getData(SWORD_MASTERY) < player.getData(SWORD_CAP)) {
                    swordLevelUp(player);
                    player.setData(SWORD_EXP, 4f + player.getData(SWORD_EXP) - player.getData(SWORD_EXP_NEEDED));
                    displaySword(player);
                }
            } else if(player.getMainHandItem().getItem() instanceof AxeItem axeItem) {
                // Separately increases the base weapon damage
                attributeDmg = (float)player.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue();
                weaponDmg = 1f + (event.getOriginalAmount() - attributeDmg);
                event.setAmount((attributeDmg - 1) + (weaponDmg * swordMasteryDmgBoostPercent));

                player.setData(SWORD_EXP, player.getData(SWORD_EXP) + event.getAmount());
                displaySword(player);
                if (player.getData(SWORD_EXP) >= player.getData(SWORD_EXP_NEEDED)
                        && player.getData(SWORD_MASTERY) < player.getData(SWORD_CAP)) {
                    swordLevelUp(player);
                    player.setData(SWORD_EXP, 4f + player.getData(SWORD_EXP) - player.getData(SWORD_EXP_NEEDED));
                    displaySword(player);
                }
            }
        }
    }

    static void displaySword(ServerPlayer player){
        if(player != null) {
            player.sendSystemMessage(Component.literal("weapon dmg: " + weaponDmg));
            player.sendSystemMessage(Component.literal("Sword Mastery: " + player.getData(SWORD_MASTERY)));
            player.sendSystemMessage(Component.literal("Sword Mastery exp: " + player.getData(SWORD_EXP)));
            player.sendSystemMessage(Component.literal("Sword Mastery exp needed: " + player.getData(SWORD_EXP_NEEDED)));
            player.sendSystemMessage(Component.literal("//-------------------------------------------------//"));
        }
    }
}
