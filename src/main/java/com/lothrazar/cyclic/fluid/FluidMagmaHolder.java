package com.lothrazar.cyclic.fluid;

import com.lothrazar.cyclic.ModCyclic;
import com.lothrazar.cyclic.fluid.block.MagmaFluidBlock;
import com.lothrazar.cyclic.registry.MaterialRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

//Thanks to example https://github.com/MinecraftForge/MinecraftForge/blob/1.15.x/src/test/java/net/minecraftforge/debug/fluid/NewFluidTest.java
public class FluidMagmaHolder extends FluidHolderBase {

  private static final String id = "magma";
  public static RegistryObject<FlowingFluid> STILL = FLUIDS.register(id, () -> new MagmaFluidBlock.Source(FluidMagmaHolder.properties));
  public static RegistryObject<FlowingFluid> FLOWING = FLUIDS.register(id + "_flowing", () -> new MagmaFluidBlock.Flowing(FluidMagmaHolder.properties));
  public static RegistryObject<FlowingFluidBlock> BLOCK = BLOCKS.register(id + "_block",
      () -> new MagmaFluidBlock(STILL, Block.Properties.create(Material.WATER).hardnessAndResistance(100.0F).setLightLevel((p_235456_0_) -> {
        return 8;
      }).noDrops()));
  public static RegistryObject<Item> BUCKET = ITEMS.register(id + "_bucket",
      () -> new BucketItem(STILL, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(MaterialRegistry.ITEM_GROUP)));
  private static final ForgeFlowingFluid.Properties properties = new ForgeFlowingFluid.Properties(
      STILL,
      FLOWING,
      FluidAttributes.builder(
          new ResourceLocation("minecraft:block/magma"),
          new ResourceLocation("cyclic:fluid/magma_flow")))
              .bucket(BUCKET).block(BLOCK);

}
