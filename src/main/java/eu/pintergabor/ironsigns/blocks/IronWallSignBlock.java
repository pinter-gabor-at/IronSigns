package eu.pintergabor.ironsigns.blocks;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import eu.pintergabor.ironsigns.entities.IronSignBlockEntity;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;


/**
 * Identical to its parent, but associated with a different entity.
 */
public class IronWallSignBlock extends WallSignBlock {
	public static final MapCodec<WallSignBlock> CODEC =
		RecordCodecBuilder.mapCodec((instance) ->
			instance.group(
					WoodType.CODEC.fieldOf("wood_type")
						.forGetter(SignBlock::type),
					propertiesCodec())
				.apply(instance, IronWallSignBlock::new));


	public IronWallSignBlock(WoodType woodType, Properties props) {
		super(woodType, props);
	}

	@Override
	public @NonNull MapCodec<WallSignBlock> codec() {
		return CODEC;
	}

	@Override
	public @NonNull BlockEntity newBlockEntity(
		@NonNull BlockPos pos, @NonNull BlockState state
	) {
		return new IronSignBlockEntity(pos, state);
	}
}
