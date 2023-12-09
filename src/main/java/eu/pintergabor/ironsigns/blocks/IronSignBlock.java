package eu.pintergabor.ironsigns.blocks;

import eu.pintergabor.ironsigns.entities.IronSignBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.SignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class IronSignBlock extends SignBlock {

	public IronSignBlock(Settings settings, WoodType woodType) {
		super(settings, woodType);
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new IronSignBlockEntity(pos, state);
	}
}
