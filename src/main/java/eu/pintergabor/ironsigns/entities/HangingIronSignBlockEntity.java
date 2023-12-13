package eu.pintergabor.ironsigns.entities;

import eu.pintergabor.ironsigns.main.Main;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.HangingSignBlockEntity;
import net.minecraft.util.math.BlockPos;

/**
 * Common entity for all IronSign blocks
 * <p>
 * There is only one instance {@link Main#getHangingIronSignEntity()}
 */
public class HangingIronSignBlockEntity extends HangingSignBlockEntity {

	public HangingIronSignBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
	}

	@Override
	public BlockEntityType<?> getType() {
		return Main.getHangingIronSignEntity();
	}
}
