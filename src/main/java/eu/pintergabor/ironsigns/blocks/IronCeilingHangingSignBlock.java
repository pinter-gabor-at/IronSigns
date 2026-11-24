package eu.pintergabor.ironsigns.blocks;

import eu.pintergabor.ironsigns.entities.HangingIronSignBlockEntity;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;


/**
 * Identical to its parent, but associated with a different entity.
 */
public class IronCeilingHangingSignBlock extends CeilingHangingSignBlock {

	public IronCeilingHangingSignBlock(WoodType woodType, Properties props) {
		super(woodType, props);
	}

	@Override
	public @NonNull BlockEntity newBlockEntity(
		@NonNull BlockPos pos, @NonNull BlockState state
	) {
		return new HangingIronSignBlockEntity(pos, state);
	}
}
