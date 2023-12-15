package eu.pintergabor.ironsigns.blocks;

import eu.pintergabor.ironsigns.entities.HangingIronSignBlockEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.WallHangingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class IronWallHangingSignBlock extends WallHangingSignBlock {

	public IronWallHangingSignBlock(WoodType woodType, Settings settings) {
		super(settings, woodType);
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new HangingIronSignBlockEntity(pos, state);
	}
}
