package eu.pintergabor.ironsigns.entities;

import eu.pintergabor.ironsigns.main.Main;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;


/**
 * Common entity for all IronSign blocks.
 * <p>
 * Needed, because entity types must be registered with a list of blocks they are associated with.
 * Vanilla entity types are already registered with their list of blocks,
 * so they cannot be used.
 */
public class IronSignBlockEntity extends SignBlockEntity {

	public IronSignBlockEntity(BlockPos pos, BlockState state) {
		super(Main.ironSignEntity.get(), pos, state);
	}
}
