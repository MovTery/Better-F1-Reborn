package com.movtery.betterf1;

import com.movtery.betterf1.client.HUDState;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(BetterF1.MOD_ID)
public class BetterF1 {
    public static final String MOD_ID = "betterf1";
    public static final String MOD_NAME = "BetterF1";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static HUDState state = HUDState.ALL_VISIBLE;
    public static boolean isHidden(boolean hudHidden) {
        return hudHidden && !state.equals(HUDState.NO_HUD);
    }

    public BetterF1(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.debug(MOD_NAME + " initialized");
        }
    }
}
