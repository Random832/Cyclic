package com.lothrazar.cyclicmagic.component.peat;
import java.util.List;
import java.util.Random;
import com.lothrazar.cyclicmagic.IHasRecipe;
import com.lothrazar.cyclicmagic.block.base.BlockBase;
import com.lothrazar.cyclicmagic.data.Const;
import com.lothrazar.cyclicmagic.registry.RecipeRegistry;
import com.lothrazar.cyclicmagic.util.UtilParticle;
import com.lothrazar.cyclicmagic.util.UtilShape;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPeat extends BlockBase implements IHasRecipe {
  private static final double CHANCE_BAKE_PCT = 0.05;
  private boolean isBaked;
  public BlockPeat(boolean baked) {
    super(Material.GROUND);
    this.setSoundType(SoundType.GROUND);
    this.setHarvestLevel(Const.ToolStrings.shovel, 2);
    this.setTickRandomly(true);
    isBaked = baked;
  }
  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    if (this.isBaked) {
      return Item.getByNameOrId(Const.MODRES + "peat_fuel");
    }
    return super.getItemDropped(state, rand, fortune);
  }
  @Override
  public int quantityDropped(Random rand) {
    if (this.isBaked) {
      return 1 + rand.nextInt(2);
    }
    return 1;
  }
  @Override
  public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
    if (this.isBaked) {
      return;
    }
    Block bSide;
    List<BlockPos> around = UtilShape.squareHorizontalHollow(pos, 1);
    for (BlockPos p : around) {
      //try to bake if SOURCE water is nearby 
      bSide = world.getBlockState(p).getBlock();
      if (bSide == Blocks.WATER) {
        tryBake(world, pos);
        return;
      }
    }
  }
  private void tryBake(World world, BlockPos pos) {
    if (this.isBaked == false && world.rand.nextDouble() < CHANCE_BAKE_PCT) {
      world.setBlockToAir(pos);
      world.setBlockState(pos, Block.getBlockFromName(Const.MODRES + "peat_baked").getDefaultState());
      UtilParticle.spawnParticle(world, EnumParticleTypes.WATER_BUBBLE, pos);
    }
  }
  @Override
  public int tickRate(World worldIn) {
    return 1100;
  }
  @Override
  public IRecipe addRecipe() {
    if (isBaked) {
      return null;// on recipe for this 
    }
    return RecipeRegistry.addShapedRecipe(new ItemStack(this, 2),
        "pcp",
        "ccc",
        "pcp",
        'c', "dirt",
        'p', new ItemStack(Item.getByNameOrId(Const.MODRES + "peat_biomass")));
  }
}
