package com.endremastered.endrem.world.structures;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.world.ERStructureConfig.ERStructures;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.*;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;

import java.util.Map;
import java.util.Random;

public class EndCastlePieces {

    private static final int height = 0;
    private static final Identifier BOTTOM_LEFT = EndRemastered.createIdentifier("end_castle/castle_bl");
    private static final Identifier MID_LEFT = EndRemastered.createIdentifier("end_castle/castle_ml");
    private static final Identifier TOP_LEFT = EndRemastered.createIdentifier("end_castle/castle_tl");
    private static final Identifier BOTTOM_MID = EndRemastered.createIdentifier("end_castle/castle_bm");
    private static final Identifier BOTTOM_RIGHT = EndRemastered.createIdentifier("end_castle/castle_br");
    public static final Identifier MID_RIGHT = EndRemastered.createIdentifier("end_castle/castle_mr");
    public static final Identifier TOP_RIGHT = EndRemastered.createIdentifier("end_castle/castle_tr");
    public static final Identifier TOP_MID = EndRemastered.createIdentifier("end_castle/castle_tm");
    public static final Identifier MID_MID = EndRemastered.createIdentifier("end_castle/castle_mm");

    private static final Map<Identifier, BlockPos> OFFSET = ImmutableMap.of(
            BOTTOM_LEFT, new BlockPos(20, height, 24),
            MID_LEFT, new BlockPos(-25, height, 24),
            TOP_LEFT, new BlockPos(-48, height, 24),
            BOTTOM_RIGHT, new BlockPos(20, height, -40),
            BOTTOM_MID, new BlockPos(41, height, 0),
            MID_RIGHT, new BlockPos(-24, height, -47),
            TOP_RIGHT, new BlockPos(-48, height, -40),
            TOP_MID, new BlockPos(-48, height, 0),
            MID_MID, new BlockPos(0, height, 0)
            );

    public static void addPieces(StructureManager manager, BlockPos pos, BlockRotation rotation, StructurePiecesHolder piecesHolder, Random random) {
        for (Map.Entry<Identifier, BlockPos> entry : OFFSET.entrySet()) {
            piecesHolder.addPiece(new EndCastlePiece(manager, entry.getKey(), entry.getValue().rotate(rotation).add(pos.getX(), pos.getY(), pos.getZ()), rotation));
        }
    }

    public static class EndCastlePiece extends SimpleStructurePiece {

        public EndCastlePiece(StructureManager manager, Identifier identifier, BlockPos pos, BlockRotation rotation) {
            super(ERStructures.PIECE, 0, manager, identifier, identifier.toString(), createPlacementData(rotation), pos);
        }

        public EndCastlePiece(StructureContext context, NbtCompound nbt) {
            super(ERStructures.PIECE, nbt, context.structureManager(), (identifier1) ->
                    createPlacementData(BlockRotation.valueOf(nbt.getString("Rot")))
            );
        }

        private static StructurePlacementData createPlacementData(BlockRotation rotation) {
            return (new StructurePlacementData()).setRotation(rotation).setMirror(BlockMirror.NONE);
        }

        protected void writeNbt(StructureContext context, NbtCompound nbt) {
            super.writeNbt(context, nbt);
            nbt.putString("Rot", this.placementData.getRotation().name());
        }

        protected void handleMetadata(String chest, BlockPos pos, ServerWorldAccess serverWorldAccess, Random random, BlockBox boundingBox) {
            Identifier lootTable = new Identifier(EndRemastered.MOD_ID, String.format("chests/%s", chest));
            serverWorldAccess.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
            LootableContainerBlockEntity.setLootTable(serverWorldAccess, random, pos.down(), lootTable);
        }
    }
}
