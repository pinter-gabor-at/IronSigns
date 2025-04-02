package eu.pintergabor.ironsigns.entities;

import eu.pintergabor.ironsigns.main.Main;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;


/**
 * Common entity for all IronSign blocks.
 * <p>
 * There is only one instance {@link Main#ironSignEntity}.
 */
public class IronSignBlockEntity extends SignBlockEntity {

    public IronSignBlockEntity(BlockPos pos, BlockState state) {
        super(Main.ironSignEntity, pos, state);
    }
}
