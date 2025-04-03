package eu.pintergabor.ironsigns.blocks;

import eu.pintergabor.ironsigns.entities.IronSignBlockEntity;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;


public class IronSignBlock extends StandingSignBlock {

	public IronSignBlock(WoodType woodType, Properties settings) {
		super(woodType, settings);
	}

	@Override
	@NotNull
	public BlockEntity newBlockEntity(
		@NotNull BlockPos pos, @NotNull BlockState state) {
		return new IronSignBlockEntity(pos, state);
	}
}
