package com.endremastered.endrem.world.structures;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.world.ERStructureConfig.ERConfiguredStructure;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.data.client.model.VariantSettings;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.*;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;

import java.util.List;
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

//    public static void addPieces(StructureManager manager, StructurePiecesHolder structurePiecesHolder,BlockPos pos, Random random) {
//        BlockRotation blockRotation = BlockRotation.NONE;
//        System.out.println("BlockRotation Value: " + blockRotation);
//        structurePiecesHolder.addPiece(new EndCastlePieces.Piece(manager, BOTTOM_LEFT, pos.add(20, height, 24), blockRotation));
//        structurePiecesHolder.addPiece(new EndCastlePieces.Piece(manager, MID_LEFT, pos.add(-25, height, 24), blockRotation));
//        structurePiecesHolder.addPiece(new EndCastlePieces.Piece(manager, TOP_LEFT, pos.add(-48, height, 24), blockRotation));
//        structurePiecesHolder.addPiece(new EndCastlePieces.Piece(manager, BOTTOM_RIGHT, pos.add(20, height, -40), blockRotation));
//        structurePiecesHolder.addPiece(new EndCastlePieces.Piece(manager, MID_RIGHT, pos.add(-24, height, -47), blockRotation));
//        structurePiecesHolder.addPiece(new EndCastlePieces.Piece(manager, TOP_RIGHT, pos.add(-48, height, -40), blockRotation));
//        structurePiecesHolder.addPiece(new EndCastlePieces.Piece(manager, BOTTOM_MID, pos.add(41, height, 0), blockRotation));
//        structurePiecesHolder.addPiece(new EndCastlePieces.Piece(manager, MID_MID, pos, blockRotation));
//        structurePiecesHolder.addPiece(new EndCastlePieces.Piece(manager, TOP_MID, pos.add(-48, height, 0), blockRotation));
//    }

    private static final Map<Identifier, BlockPos> OFFSET = new ImmutableMap.Builder<Identifier, BlockPos>()
            .put(BOTTOM_LEFT, new BlockPos(20, height, 24))
            .put(MID_LEFT, new BlockPos(-25, height, 24))
            .put(TOP_LEFT, new BlockPos(-48, height, 24))
            .put(BOTTOM_RIGHT, new BlockPos(20, height, -40))
            .put(BOTTOM_MID, new BlockPos(41, height, 0))
            .put(MID_RIGHT, new BlockPos(-24, height, -47))
            .put(TOP_RIGHT, new BlockPos(-48, height, -40))
            .put(TOP_MID, new BlockPos(-48, height, 0))
            .put(MID_MID, new BlockPos(0, height, 0))
            .build();

    public static void start(StructureManager manager, BlockPos pos, BlockRotation rotation, List<StructurePiece> pieceList) {
        for (Map.Entry<Identifier, BlockPos> entry : OFFSET.entrySet()) {
            pieceList.add(new EndCastlePieces.Piece(manager, entry.getKey(), entry.getValue().rotate(rotation).add (pos.getX(), pos.getY(), pos.getZ()), rotation, 0));
        }
    }

    public static class Piece extends SimpleStructurePiece {

        public Piece(StructureManager manager, Identifier template, BlockPos pos, BlockRotation rotation, int p_71248_) {
            super(ERConfiguredStructure.PIECE, 0, manager, template, template.toString(), createPlacementData(rotation), pos);
        }

        public Piece(ServerWorld world, NbtCompound nbt) {
            super(ERConfiguredStructure.PIECE, nbt, world, (identifier1 -> createPlacementData(BlockRotation.valueOf(nbt.getString("Rot")))));
        }

        private static StructurePlacementData createPlacementData(BlockRotation rotation) {
            return (new StructurePlacementData()).setRotation(rotation).setMirror(BlockMirror.NONE).addProcessor(BlockIgnoreStructureProcessor.IGNORE_AIR_AND_STRUCTURE_BLOCKS);
        }

        protected void writeNbt(ServerWorld world, NbtCompound nbt) {
            super.writeNbt(world, nbt);
            nbt.putString("Rot", this.placementData.getRotation().name());
        }

        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess serverWorldAccess, Random random, BlockBox boundingBox) {
            Identifier lootTable = new Identifier(EndRemastered.MOD_ID, String.format("chests/%s", metadata));
            serverWorldAccess.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
            BlockEntity tileentity = serverWorldAccess.getBlockEntity(pos.down());
            if (tileentity instanceof ChestBlockEntity) {
                ((ChestBlockEntity) tileentity).setLootTable(lootTable, random.nextLong());
            }
            else{
                ((BarrelBlockEntity) tileentity).setLootTable(lootTable, random.nextLong());
            }
        }
    }
}
