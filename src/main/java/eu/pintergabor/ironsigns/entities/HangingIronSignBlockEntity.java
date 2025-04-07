package eu.pintergabor.ironsigns.entities;

import eu.pintergabor.ironsigns.main.Main;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;


/**
 * Common entity for all hanging IronSign blocks.
 * <p>
 * Needed, because entity types must be registered with a list of blocks they are associated with.
 * Vanilla entity types are already registered with their list of blocks,
 * so they cannot be used.
 * <p>
 * It extends {@link SignBlockEntity} and not {@link HangingSignBlockEntity}, because the
 * entity type is already hard coded in {@link HangingSignBlockEntity}.
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
	public int getMaxTextLineWidth() {
		return MAX_TEXT_WIDTH;
	}

	@Override
	@NotNull
	public SoundEvent getSignInteractionFailedSoundEvent() {
		return SoundEvents.WAXED_HANGING_SIGN_INTERACT_FAIL;
	}
}
