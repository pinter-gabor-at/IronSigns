package eu.pintergabor.ironsigns.blocks;

import eu.pintergabor.ironsigns.entities.IronSignBlockEntity;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;


/**
 * Identical to its parent, but associated with a different entity.
 */
public class IronStandingSignBlock extends StandingSignBlock {

	public IronStandingSignBlock(WoodType woodType, Properties props) {
		super(woodType, props);
	}

	@Override
	public @NonNull BlockEntity newBlockEntity(
		@NonNull BlockPos pos, @NonNull BlockState state
	) {
		return new IronSignBlockEntity(pos, state);
	}
}
