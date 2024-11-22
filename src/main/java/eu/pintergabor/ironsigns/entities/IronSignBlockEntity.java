package eu.pintergabor.ironsigns.entities;

import eu.pintergabor.ironsigns.main.Main;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.util.math.BlockPos;

/**
 * Common entity for all IronSign blocks
 * <p>
 * There is only one instance {@link Main#ironSignEntity}
 */
public class IronSignBlockEntity extends SignBlockEntity {

    public IronSignBlockEntity(BlockPos pos, BlockState state) {
        super(Main.ironSignEntity, pos, state);
    }
}
