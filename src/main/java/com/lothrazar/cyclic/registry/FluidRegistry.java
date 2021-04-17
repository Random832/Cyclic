package com.lothrazar.cyclic.registry;

import com.lothrazar.cyclic.fluid.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Arrays;
import java.util.List;

public class FluidRegistry {

  public static FluidXpJuiceHolder xpjuice;
  public static FluidSlimeHolder slime;
  public static FluidBiomassHolder biomass;
  public static FluidHoneyHolder honey;
  public static FluidMagmaHolder magma;
  public static List<FluidHolderBase> fluids;

  public static void setup() {
    FluidHolderBase.initRegistries(FMLJavaModLoadingContext.get().getModEventBus());
    xpjuice = new FluidXpJuiceHolder();
    slime = new FluidSlimeHolder();
    biomass = new FluidBiomassHolder();
    honey = new FluidHoneyHolder();
    magma = new FluidMagmaHolder();
    fluids = Arrays.asList(xpjuice, slime, biomass, honey, magma);
  }
}
