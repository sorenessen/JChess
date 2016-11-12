package board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import alliance.Alliance;
import pieces.Piece;

public class BlackPlayer  extends Player {
	
	public BlackPlayer (final Board board,
			final Collection<Move> whiteStandardLegalMoves,
			final Collection<Move> blackStandardLegalMoves) {
		super(board, blackStandardLegalMoves, whiteStandardLegalMoves);
		}
	@Override
	public Collection<Piece> getActivePieces() {
		return this.board.getBlackPieces();
	}
	@Override
	public Alliance getAlliance() {
		return Alliance.BLACK;
	}
	@Override
	public Player getOpponent() {
		return this.board.whitePlayer();
	}
	@Override
	protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals, 
													final Collection<Move> opponentsLegals) {
		final List<Move> kingCastles = new ArrayList<>();
		if(this.playerKing.isFirstMove() && !this.isInCheck()) {
			//blacks kingside castle move
			if(!this.board.getTile(5).isTileOccupied() && !this.board.getTile(6).isTileOccupied()) {
				final Tile rookTile = this.board.getTile(7);
				if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
					if(Player.calculateAttacksOnTile(5, opponentsLegals).isEmpty() && 
					   Player.calculateAttacksOnTile(6, opponentsLegals).isEmpty() &&
					   rookTile.getPiece().getPieceType().isRook()) {
						//TODO add castlemove
						kingCastles.add(null);	
					}
				}
			}
			if(!this.board.getTile(1).isTileOccupied() && 
			   !this.board.getTile(2).isTileOccupied() && 
			   !this.board.getTile(3).isTileOccupied()) {
				
			   final Tile rookTile = this.board.getTile(0);
			   if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
				   //TODO ADD CASTLE MOVE
				   kingCastles.add(null);
			   }
			}
		}
		return ImmutableList.copyOf(kingCastles);	}

}
