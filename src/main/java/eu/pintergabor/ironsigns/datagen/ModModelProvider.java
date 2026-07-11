package eu.pintergabor.ironsigns.datagen;

import static net.minecraft.client.data.models.BlockModelGenerators.*;
import static net.minecraft.client.data.models.model.TextureMapping.getBlockTexture;

import java.util.Arrays;

import eu.pintergabor.ironsigns.main.Main;
import eu.pintergabor.ironsigns.main.SignVariant;
import org.jspecify.annotations.NonNull;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;


public final class ModModelProvider extends FabricModelProvider {

	public ModModelProvider(FabricPackOutput output) {
		super(output);
	}

	/**
	 * Common part of {@link #createSign(BlockModelGenerators, Block, Block, Block)}
	 * and {@link #createHangingSign(BlockModelGenerators, Block, Block, Block)}.
	 *
	 * @param wallSign The sign or the hanging sign attached to the wall.
	 * @param base     Base variant.
	 * @return The multi variant ready for registration.
	 */
	private static @NonNull MultiVariantGenerator createWallSign(
		final @NonNull Block wallSign,
		final MultiVariant base
	) {
		return MultiVariantGenerator.dispatch(wallSign)
			.with(PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_FACING)
				.select(Direction.SOUTH, base)
				.select(Direction.WEST, base.with(Y_ROT_90))
				.select(Direction.NORTH, base.with(Y_ROT_180))
				.select(Direction.EAST, base.with(Y_ROT_270))
			);
	}

	/**
	 * Create blockstates, block and item models for standing sign and wall sign.
	 *
	 * @param particleBlock Block for particle generation.
	 * @param standingSign  The standing sign block.
	 * @param wallSign      The wall sign block.
	 */
	@SuppressWarnings("SameParameterValue")
	private void createSign(
		final @NonNull BlockModelGenerators generators,
		final @NonNull Block particleBlock,
		final @NonNull Block standingSign,
		final @NonNull Block wallSign
	) {
		// Item.
		generators.registerSimpleFlatItemModel(standingSign.asItem());
		// Textures.
		final TextureMapping mapping = (new TextureMapping())
			.put(TextureSlot.ALL, getBlockTexture(standingSign))
			.put(TextureSlot.PARTICLE, getBlockTexture(particleBlock));
		// Standing sign.
		final MultiVariant rot0 = plainVariant(ModelTemplates.SIGN_ROT_0
			.createWithSuffix(standingSign, "_rot_0", mapping, generators.modelOutput));
		final MultiVariant rot1 = plainVariant(ModelTemplates.SIGN_ROT_1
			.createWithSuffix(standingSign, "_rot_1", mapping, generators.modelOutput));
		final MultiVariant rot2 = plainVariant(ModelTemplates.SIGN_ROT_2
			.createWithSuffix(standingSign, "_rot_2", mapping, generators.modelOutput));
		final MultiVariant rot3 = plainVariant(ModelTemplates.SIGN_ROT_3
			.createWithSuffix(standingSign, "_rot_3", mapping, generators.modelOutput));
		// Create.
		generators.blockStateOutput.accept(
			BlockModelGenerators.createSign(standingSign, rot0, rot1, rot2, rot3));
		// Wall sign.
		final MultiVariant base = plainVariant(ModelTemplates.WALL_SIGN
			.create(wallSign, mapping, generators.modelOutput));
		// Create.
		generators.blockStateOutput.accept(
			createWallSign(wallSign, base));

	}

	/**
	 * Create blockstates, block and item models for hanging sign and wall hanging sign.
	 *
	 * @param particleBlock      Block for particle generation.
	 * @param ceilingHangingSign The sign block hanging from the ceiling.
	 * @param wallHangingSign    The sign block attached to the wall.
	 */
	@SuppressWarnings("SameParameterValue")
	private void createHangingSign(
		final @NonNull BlockModelGenerators generators,
		final @NonNull Block particleBlock,
		final @NonNull Block ceilingHangingSign,
		final @NonNull Block wallHangingSign
	) {
		// Item.
		generators.registerSimpleFlatItemModel(ceilingHangingSign.asItem());
		// Textures.
		TextureMapping mapping = (new TextureMapping())
			.put(TextureSlot.ALL, getBlockTexture(ceilingHangingSign))
			.put(TextureSlot.PARTICLE, getBlockTexture(particleBlock));
		// Hanging sign.
		MultiVariant rot0 = plainVariant(ModelTemplates.HANGING_SIGN_ROT_0
			.createWithSuffix(ceilingHangingSign, "_rot_0", mapping, generators.modelOutput));
		MultiVariant rot1 = plainVariant(ModelTemplates.HANGING_SIGN_ROT_1
			.createWithSuffix(ceilingHangingSign, "_rot_1", mapping, generators.modelOutput));
		MultiVariant rot2 = plainVariant(ModelTemplates.HANGING_SIGN_ROT_2
			.createWithSuffix(ceilingHangingSign, "_rot_2", mapping, generators.modelOutput));
		MultiVariant rot3 = plainVariant(ModelTemplates.HANGING_SIGN_ROT_3
			.createWithSuffix(ceilingHangingSign, "_rot_3", mapping, generators.modelOutput));
		MultiVariant wrot0 = plainVariant(ModelTemplates.ATTACHED_HANGING_SIGN_ROT_0
			.createWithSuffix(wallHangingSign, "_rot_0", mapping, generators.modelOutput));
		MultiVariant wrot1 = plainVariant(ModelTemplates.ATTACHED_HANGING_SIGN_ROT_1
			.createWithSuffix(wallHangingSign, "_rot_1", mapping, generators.modelOutput));
		MultiVariant wrot2 = plainVariant(ModelTemplates.ATTACHED_HANGING_SIGN_ROT_2
			.createWithSuffix(wallHangingSign, "_rot_2", mapping, generators.modelOutput));
		MultiVariant wrot3 = plainVariant(ModelTemplates.ATTACHED_HANGING_SIGN_ROT_3
			.createWithSuffix(wallHangingSign, "_rot_3", mapping, generators.modelOutput));
		// Create.
		generators.blockStateOutput.accept(
			BlockModelGenerators.createHangingSign(
				ceilingHangingSign, rot0, rot1, rot2, rot3, wrot0, wrot1, wrot2, wrot3));
		// Wall hanging sign.
		final MultiVariant base = plainVariant(ModelTemplates.WALL_HANGING_SIGN
			.create(wallHangingSign, mapping, generators.modelOutput));
		// Create.
		generators.blockStateOutput.accept(
			createWallSign(wallHangingSign, base));
	}

	/**
	 * Generate blockstates, block and item models for one {@link SignVariant}.
	 *
	 * @param sv {@link SignVariant}.
	 */
	private void generateSignBlockStates(
		@NonNull BlockModelGenerators generators,
		@NonNull SignVariant sv
	) {
		// Generate blockstates, block and item models for Sign and WallSign.
		createSign(generators, Blocks.IRON_BLOCK,
			sv.standingSign, sv.wallSign);
		// Generate blockstates, block and item models for HangingSign and WallHangingSign.
		createHangingSign(generators, Blocks.IRON_BLOCK,
			sv.ceilingHangingSign, sv.wallHangingSign);
	}

	/**
	 * Generate blockstates, block and item models.
	 */
	@Override
	public void generateBlockStateModels(
		@NonNull BlockModelGenerators blockStateModelGenerator
	) {
		// Iron sign.
		generateSignBlockStates(blockStateModelGenerator, Main.ironSign);
		// Color signs.
		Arrays.stream(Main.colorSigns)
			.forEach(sv ->
				generateSignBlockStates(blockStateModelGenerator, sv));
	}

	@Override
	public void generateItemModels(
		@NonNull ItemModelGenerators itemModelGenerator
	) {
		// Already generated in generateBlockStateModels.
	}
}
