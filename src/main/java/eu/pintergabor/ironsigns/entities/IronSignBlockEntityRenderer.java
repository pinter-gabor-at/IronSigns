package eu.pintergabor.ironsigns.entities;

import eu.pintergabor.ironsigns.blocks.IronSignBlock;

import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class IronSignBlockEntityRenderer extends SignBlockEntityRenderer {
	public IronSignBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
		super(ctx);
	}

	// ERROR: typeToModel is private in SignBlockEntityRenderer
	// Duplicating it would be difficult, because the decompiled code is faulty.
	@Override
	public void render(SignBlockEntity signBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
		BlockState blockState = signBlockEntity.getCachedState();
		AbstractSignBlock abstractSignBlock = (AbstractSignBlock)blockState.getBlock();
		WoodType woodType = AbstractSignBlock.getWoodType(abstractSignBlock);
		SignModel signModel = this.typeToModel.get(woodType);
		signModel.stick.visible = blockState.getBlock() instanceof IronSignBlock;
		this.render(signBlockEntity, matrixStack, vertexConsumerProvider, i, j, blockState, abstractSignBlock, woodType, signModel);
	}


}
