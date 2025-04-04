package eu.pintergabor.ironsigns.blocks;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import eu.pintergabor.ironsigns.entities.IronSignBlockEntity;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;


public class IronSignBlock extends StandingSignBlock {
	public static final MapCodec<StandingSignBlock> CODEC =
		RecordCodecBuilder.mapCodec((instance) ->
			instance.group(
					WoodType.CODEC.fieldOf("wood_type")
						.forGetter(SignBlock::type),
					propertiesCodec())
				.apply(instance, IronSignBlock::new));

	public IronSignBlock(WoodType woodType, Properties settings) {
		super(woodType, settings);
	}

	@Override
	@NotNull
	public MapCodec<StandingSignBlock> codec() {
		return CODEC;
	}

	@Override
	@NotNull
	public BlockEntity newBlockEntity(
		@NotNull BlockPos pos, @NotNull BlockState state) {
		return new IronSignBlockEntity(pos, state);
	}
}
