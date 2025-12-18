package net.froggittybitty.adventurepiece;

import com.mojang.serialization.Codec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class DataAttachmentProvider {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, AdventurePiece.MODID);

    public static void Register(IEventBus eventBus){
        ATTACHMENT_TYPES.register(eventBus);
    }

//----------------------------------------------------------------------------------------------------
    //-STATS-//
    public static final Supplier<AttachmentType<Float>> STRENGTH_STAT = ATTACHMENT_TYPES.register(
            "strength_stat", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> STRENGTH_EXP = ATTACHMENT_TYPES.register(
            "strength_exp", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> STRENGTH_EXP_NEEDED = ATTACHMENT_TYPES.register(
            "strength_exp_needed", () -> AttachmentType.builder(() -> 20f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> STRENGTH_CAP = ATTACHMENT_TYPES.register(
            "strength_cap", () -> AttachmentType.builder(() -> 300f).serialize(Codec.FLOAT).build());

    public static final Supplier<AttachmentType<Float>> ENDURANCE_STAT = ATTACHMENT_TYPES.register(
            "endurance_stat", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> ENDURANCE_EXP = ATTACHMENT_TYPES.register(
            "endurance_exp", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> ENDURANCE_EXP_NEEDED = ATTACHMENT_TYPES.register(
            "endurance_exp_needed", () -> AttachmentType.builder(() -> 20f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> ENDURANCE_CAP = ATTACHMENT_TYPES.register(
            "endurance_cap", () -> AttachmentType.builder(() -> 300f).serialize(Codec.FLOAT).build());

    public static final Supplier<AttachmentType<Float>> AGILITY_STAT = ATTACHMENT_TYPES.register(
            "agility_stat", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> AGILITY_EXP = ATTACHMENT_TYPES.register(
            "agility_exp", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> AGILITY_EXP_NEEDED = ATTACHMENT_TYPES.register(
            "agility_exp_needed", () -> AttachmentType.builder(() -> 20f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> AGILITY_CAP = ATTACHMENT_TYPES.register(
            "agility_cap", () -> AttachmentType.builder(() -> 300f).serialize(Codec.FLOAT).build());

    //-HAKI-
    public static final Supplier<AttachmentType<Float>> HAKI_STAT = ATTACHMENT_TYPES.register(
            "haki_stat", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> HAKI_EXP = ATTACHMENT_TYPES.register(
            "haki_exp", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> HAKI_EXP_NEEDED = ATTACHMENT_TYPES.register(
            "haki_exp_needed", () -> AttachmentType.builder(() -> 40f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> HAKI_CAP = ATTACHMENT_TYPES.register(
            "haki_cap", () -> AttachmentType.builder(() -> 300f).serialize(Codec.FLOAT).build());

    public static final Supplier<AttachmentType<Float>> HAKI_AMOUNT = ATTACHMENT_TYPES.register(
            "haki_amount", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> HAKI_AMOUNT_CAP = ATTACHMENT_TYPES.register(
            "haki_amount_cap", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());

    public static final Supplier<AttachmentType<Boolean>> HAKI_ON = ATTACHMENT_TYPES.register(
            "haki_on", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build());

    //-STAMINA-
    public static final Supplier<AttachmentType<Float>> STAMINA = ATTACHMENT_TYPES.register(
            "stamina", () -> AttachmentType.builder(() -> 100f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> STAMINA_CAP = ATTACHMENT_TYPES.register(
            "stamina_cap", () -> AttachmentType.builder(() -> 100f).serialize(Codec.FLOAT).build());

//----------------------------------------------------------------------------------------------------//
    //-MASTERIES-//
    //-HAKI-//
    public static final Supplier<AttachmentType<Float>> CONQUERORS_MASTERY = ATTACHMENT_TYPES.register(
            "conquerors_mastery", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> CONQUERORS_EXP = ATTACHMENT_TYPES.register(
            "conquerors_exp", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> CONQUERORS_EXP_NEEDED = ATTACHMENT_TYPES.register(
            "conquerors_exp_needed", () -> AttachmentType.builder(() -> 20f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> CONQUERORS_CAP = ATTACHMENT_TYPES.register(
            "conquerors_cap", () -> AttachmentType.builder(() -> 300f).serialize(Codec.FLOAT).build());

    public static final Supplier<AttachmentType<Float>> ARMAMENT_MASTERY = ATTACHMENT_TYPES.register(
            "armament_mastery", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> ARMAMENT_EXP = ATTACHMENT_TYPES.register(
            "armament_exp", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> ARMAMENT_EXP_NEEDED = ATTACHMENT_TYPES.register(
            "armament_exp_needed", () -> AttachmentType.builder(() -> 20f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> ARMAMENT_CAP = ATTACHMENT_TYPES.register(
            "armament_cap", () -> AttachmentType.builder(() -> 300f).serialize(Codec.FLOAT).build());

    public static final Supplier<AttachmentType<Float>> OBSERVATION_MASTERY = ATTACHMENT_TYPES.register(
            "observation_mastery", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> OBSERVATION_EXP = ATTACHMENT_TYPES.register(
            "observation_exp", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> OBSERVATION_EXP_NEEDED = ATTACHMENT_TYPES.register(
            "observation_exp_needed", () -> AttachmentType.builder(() -> 20f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> OBSERVATION_CAP = ATTACHMENT_TYPES.register(
            "observation_cap", () -> AttachmentType.builder(() -> 300f).serialize(Codec.FLOAT).build());

    //-COMBAT-//
    public static final Supplier<AttachmentType<Float>> SWORD_MASTERY = ATTACHMENT_TYPES.register(
            "sword_mastery", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> SWORD_EXP = ATTACHMENT_TYPES.register(
            "sword_exp", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> SWORD_EXP_NEEDED = ATTACHMENT_TYPES.register(
            "sword_exp_needed", () -> AttachmentType.builder(() -> 40f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> SWORD_CAP = ATTACHMENT_TYPES.register(
            "sword_cap", () -> AttachmentType.builder(() -> 300f).serialize(Codec.FLOAT).build());

    public static final Supplier<AttachmentType<Float>> FIST_MASTERY = ATTACHMENT_TYPES.register(
            "fist_mastery", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> FIST_EXP = ATTACHMENT_TYPES.register(
            "fist_exp", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> FIST_EXP_NEEDED = ATTACHMENT_TYPES.register(
            "fist_exp_needed", () -> AttachmentType.builder(() -> 20f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> FIST_CAP = ATTACHMENT_TYPES.register(
            "fist_cap", () -> AttachmentType.builder(() -> 300f).serialize(Codec.FLOAT).build());

    public static final Supplier<AttachmentType<Float>> KICK_MASTERY = ATTACHMENT_TYPES.register(
            "kick_mastery", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> KICK_EXP = ATTACHMENT_TYPES.register(
            "kick_exp", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> KICK_EXP_NEEDED = ATTACHMENT_TYPES.register(
            "kick_exp_needed", () -> AttachmentType.builder(() -> 20f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> KICK_CAP = ATTACHMENT_TYPES.register(
            "kick_cap", () -> AttachmentType.builder(() -> 300f).serialize(Codec.FLOAT).build());

    public static final Supplier<AttachmentType<Boolean>> IS_KICKING = ATTACHMENT_TYPES.register(
            "is_kicking", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build());
    public static final Supplier<AttachmentType<Boolean>> IS_MIXED_FIGHTING = ATTACHMENT_TYPES.register(
            "is_mixed_fighting", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build());
    
    //-DEVIL-FRUIT-//
    public static final Supplier<AttachmentType<Float>> DEVILFRUIT_MASTERY = ATTACHMENT_TYPES.register(
            "devilfruit_mastery", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> DEVILFRUIT_EXP = ATTACHMENT_TYPES.register(
            "devilfruit_exp", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> DEVILFRUIT_EXP_NEEDED = ATTACHMENT_TYPES.register(
            "devilfruit_exp_needed", () -> AttachmentType.builder(() -> 20f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> DEVILFRUIT_CAP = ATTACHMENT_TYPES.register(
            "devilfruit_cap", () -> AttachmentType.builder(() -> 300f).serialize(Codec.FLOAT).build());

//----------------------------------------------------------------------------------------------------//
    //-RACES-//
    public static final Supplier<AttachmentType<Boolean>> IS_GIANT = ATTACHMENT_TYPES.register(
            "is_giant", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build());

    public static final Supplier<AttachmentType<Boolean>> IS_FISHMAN = ATTACHMENT_TYPES.register(
            "is_fishman", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build());

    public static final Supplier<AttachmentType<Boolean>> IS_HUMAN = ATTACHMENT_TYPES.register(
            "is_human", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build());

    public static final Supplier<AttachmentType<Boolean>> IS_MINK = ATTACHMENT_TYPES.register(
            "is_mink", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build());

    public static final Supplier<AttachmentType<Boolean>> IS_TONTATTA = ATTACHMENT_TYPES.register(
            "is_tontatta", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build());

    public static final Supplier<AttachmentType<Boolean>> IS_LUNARIAN = ATTACHMENT_TYPES.register(
            "is_lunarian", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build());

    public static final Supplier<AttachmentType<Boolean>> IS_BUCCANEER = ATTACHMENT_TYPES.register(
            "is_buccaneer", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build());

    public static final Supplier<AttachmentType<Boolean>> IS_SKYPIEAN = ATTACHMENT_TYPES.register(
            "is_skypiean", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build());

    public static final Supplier<AttachmentType<Boolean>> HAS_RACE = ATTACHMENT_TYPES.register(
            "has_race", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build());

//----------------------------------------------------------------------------------------------------//

    public static final Supplier<AttachmentType<Boolean>> CAREFUL_GIANT = ATTACHMENT_TYPES.register(
            "careful_giant", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build());

}
