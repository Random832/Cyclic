package com.lothrazar.cyclic.fluid;

import com.lothrazar.cyclic.ModCyclic;
import com.lothrazar.cyclic.fluid.block.SlimeFluidBlock;
import com.lothrazar.cyclic.registry.MaterialRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

//Thanks to example https://github.com/MinecraftForge/MinecraftForge/blob/1.15.x/src/test/java/net/minecraftforge/debug/fluid/NewFluidTest.java
public class FluidSlimeHolder extends FluidHolderBase {

  private static final String id = "slime";
  public static RegistryObject<FlowingFluid> STILL = FLUIDS.register(id, () -> new SlimeFluidBlock.Source(FluidSlimeHolder.properties));
  public static RegistryObject<FlowingFluid> FLOWING = FLUIDS.register(id + "_flowing", () -> new SlimeFluidBlock.Flowing(FluidSlimeHolder.properties));
  public static RegistryObject<FlowingFluidBlock> BLOCK = BLOCKS.register(id + "_block",
      () -> new SlimeFluidBlock(STILL, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));
  public static RegistryObject<Item> BUCKET = ITEMS.register(id + "_bucket",
      () -> new BucketItem(STILL, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(MaterialRegistry.ITEM_GROUP)));
  private static final ForgeFlowingFluid.Properties properties = new ForgeFlowingFluid.Properties(
      STILL,
      FLOWING,
      FluidAttributes.builder(
          new ResourceLocation("cyclic:fluid/slime_still"),
          new ResourceLocation("cyclic:fluid/slime_flow")))
              .bucket(BUCKET).block(BLOCK);

  @Override
  @OnlyIn(Dist.CLIENT)
  public void registerClient() {
    RenderTypeLookup.setRenderLayer(STILL.get(), RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(FLOWING.get(), RenderType.getTranslucent());
  }

}
