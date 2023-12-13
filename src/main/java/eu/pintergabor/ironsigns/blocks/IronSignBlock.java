package eu.pintergabor.ironsigns.blocks;

import com.mojang.serialization.MapCodec;
import eu.pintergabor.ironsigns.entities.IronSignBlockEntity;

import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

// ERROR: It does not display the stick, because SignBlockEntityRenderer.render()
// enables it only for descendant of SignBlock
public class IronSignBlock extends AbstractSignBlock {
	public static final MapCodec<SignBlock> CODEC = SignBlock.CODEC;
	public static final IntProperty ROTATION = Properties.ROTATION;

	public MapCodec<SignBlock> getCodec() {
		return CODEC;
	}

	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return world.getBlockState(pos.down()).isSolid();
	}

	public BlockState getPlacementState(ItemPlacementContext ctx) {
		FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
		return this.getDefaultState()
			.with(ROTATION, RotationPropertyHelper.fromYaw(ctx.getPlayerYaw() + 180.0F))
			.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
	}

	public BlockState getStateForNeighborUpdate(
		BlockState state, Direction direction, BlockState neighborState,
		WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		return direction == Direction.DOWN && !this.canPlaceAt(state, world, pos) ?
			Blocks.AIR.getDefaultState() :
			super.getStateForNeighborUpdate(
				state, direction, neighborState, world, pos, neighborPos);
	}

	public float getRotationDegrees(BlockState state) {
		return RotationPropertyHelper.toDegrees(state.get(ROTATION));
	}

	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return state.with(ROTATION, rotation.rotate(state.get(ROTATION), 16));
	}

	public BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.with(ROTATION, mirror.mirror(state.get(ROTATION), 16));
	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(ROTATION, WATERLOGGED);
	}

	public IronSignBlock(WoodType woodType, Settings settings) {
		super(woodType, settings.sounds(woodType.soundType()));
		this.setDefaultState(this.stateManager.getDefaultState()
			.with(ROTATION, 0)
			.with(WATERLOGGED, false));
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new IronSignBlockEntity(pos, state);
	}
}
