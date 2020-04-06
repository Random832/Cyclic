package com.lothrazar.cyclic.block.shapecreate;

import com.lothrazar.cyclic.base.ScreenBase;
import com.lothrazar.cyclic.gui.Textbox;
import com.lothrazar.cyclic.registry.TextureRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class ScreenShape extends ScreenBase<ContainerShape> {

  private Textbox txtHeight;
  private Textbox txtSize;

  public ScreenShape(ContainerShape screenContainer, PlayerInventory inv, ITextComponent titleIn) {
    super(screenContainer, inv, titleIn);
  }

  @Override
  public void init() {
    super.init();
    this.txtHeight = new Textbox(this.font, guiLeft + 150, guiTop + 20, 20, 20, I18n.format("x.search"));
    this.txtHeight.setFocused2(true);
    this.children.add(txtHeight);
    this.txtSize = new Textbox(this.font, guiLeft + 150, guiTop + 45, 20, 20, I18n.format("x.search"));
    this.children.add(txtSize);
  }

  @Override
  public void removed() {
    this.txtHeight = null;
    this.txtSize = null;
  }

  @Override
  public void render(int mouseX, int mouseY, float partialTicks) {
    this.renderBackground();
    super.render(mouseX, mouseY, partialTicks);
    this.renderHoveredToolTip(mouseX, mouseY);
    this.txtHeight.render(mouseX, mouseX, partialTicks);
    this.txtSize.render(mouseX, mouseX, partialTicks);
  }

  @Override
  public void tick() {
    this.txtHeight.tick();
    this.txtSize.tick();
  }
  //  @Override
  //  public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_) {
  //    if (this.heightTxt.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_)) {
  //      return true;
  //    }
  //    return false;
  //  }
  //
  //  @Override
  //  public boolean charTyped(char p_charTyped_1_, int p_charTyped_2_) {
  //    if (this.heightTxt.charTyped(p_charTyped_1_, p_charTyped_2_)) {
  //      return true;
  //    }
  //    return false;
  //  }

  @Override
  protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    //    btnToggle.setTooltip(UtilChat.lang("gui.cyclic.flowing" + container.getFlowing()));
    //    btnToggle.setTextureId(container.getFlowing() == 1 ? TextureEnum.POWER_MOVING : TextureEnum.POWER_STOP);
    this.drawButtonTooltips(mouseX, mouseY);
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    this.drawBackground(TextureRegistry.GUI);
    this.drawSlot(60, 20);
  }
}
