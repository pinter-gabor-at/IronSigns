package eu.pintergabor.ironsigns.entities;

import eu.pintergabor.ironsigns.main.Main;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;

/**
 * Common entity for all IronSign blocks
 * <p>
 * There is only one instance {@link Main#hangingIronSignEntity}
 */
public class HangingIronSignBlockEntity extends SignBlockEntity {
    private static final int MAX_TEXT_WIDTH = 60;
    private static final int TEXT_LINE_HEIGHT = 9;

    public HangingIronSignBlockEntity(BlockPos pos, BlockState state) {
        super(Main.hangingIronSignEntity, pos, state);
    }

    @Override
    public int getTextLineHeight() {
        return TEXT_LINE_HEIGHT;
    }

    @Override
    public int getMaxTextWidth() {
        return MAX_TEXT_WIDTH;
    }

    @Override
    public SoundEvent getInteractionFailSound() {
        return SoundEvents.BLOCK_HANGING_SIGN_WAXED_INTERACT_FAIL;
    }
}
