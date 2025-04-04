package eu.pintergabor.ironsigns.blocks;

import eu.pintergabor.ironsigns.entities.HangingIronSignBlockEntity;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;


public class IronWallHangingSignBlock extends WallHangingSignBlock {

	public IronWallHangingSignBlock(WoodType woodType, Properties settings) {
		super(woodType, settings);
	}

	@Override
	@NotNull
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new HangingIronSignBlockEntity(pos, state);
	}
}
