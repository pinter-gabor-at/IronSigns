package eu.pintergabor.ironsigns.blocks;

import eu.pintergabor.ironsigns.entities.IronSignBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class IronWallSignBlock extends WallSignBlock {

    public IronWallSignBlock(WoodType woodType, Settings settings) {
        super(woodType, settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new IronSignBlockEntity(pos, state);
    }
}