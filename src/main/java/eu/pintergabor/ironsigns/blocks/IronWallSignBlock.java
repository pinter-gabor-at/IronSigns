package eu.pintergabor.ironsigns.blocks;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import eu.pintergabor.ironsigns.entities.IronSignBlockEntity;
import org.jetbrains.annotations.Nullable;

import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class IronWallSignBlock extends AbstractSignBlock {
	public static final MapCodec<WallSignBlock> CODEC = WallSignBlock.CODEC;
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
	private static final Map<Direction, VoxelShape> FACING_TO_SHAPE =
		Maps.newEnumMap(ImmutableMap.of(
			Direction.NORTH, Block.createCuboidShape(
				0.0, 4.5, 14.0, 16.0, 12.5, 16.0),
			Direction.SOUTH, Block.createCuboidShape(
				0.0, 4.5, 0.0, 16.0, 12.5, 2.0),
			Direction.EAST, Block.createCuboidShape(
				0.0, 4.5, 0.0, 2.0, 12.5, 16.0),
			Direction.WEST, Block.createCuboidShape(
				14.0, 4.5, 0.0, 16.0, 12.5, 16.0)));

	public MapCodec<WallSignBlock> getCodec() {
		return CODEC;
	}

	@Override
	public String getTranslationKey() {
		return this.asItem().getTranslationKey();
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return FACING_TO_SHAPE.get(state.get(FACING));
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return world.getBlockState(pos.offset(state.get(FACING).getOpposite())).isSolid();
	}

	@Override
	@Nullable
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockState blockState = this.getDefaultState();
		FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
		World worldView = ctx.getWorld();
		BlockPos blockPos = ctx.getBlockPos();
		Direction[] directions = ctx.getPlacementDirections();
		for (Direction direction : directions) {
			if (!direction.getAxis().isHorizontal() ||
				!(blockState = blockState.with(FACING, direction.getOpposite())).canPlaceAt(worldView, blockPos)) {
				continue;
			}
			return blockState.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
		}
		return null;
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos)) {
			return Blocks.AIR.getDefaultState();
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	public float getRotationDegrees(BlockState state) {
		return state.get(FACING).asRotation();
	}

	@Override
	public Vec3d getCenter(BlockState state) {
		VoxelShape voxelShape = FACING_TO_SHAPE.get(state.get(FACING));
		return voxelShape.getBoundingBox().getCenter();
	}

	@Override
	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return state
			.with(FACING, rotation.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.rotate(mirror.getRotation(state.get(FACING)));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED);
	}

	public IronWallSignBlock(WoodType woodType, Settings settings) {
		super(woodType, settings.sounds(woodType.soundType()));
		this.setDefaultState(this.stateManager.getDefaultState()
			.with(FACING, Direction.NORTH)
			.with(WATERLOGGED, false));
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new IronSignBlockEntity(pos, state);
	}
}
