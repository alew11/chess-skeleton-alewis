package chess.pieces;

import java.util.Map;
import java.util.Set;

import chess.Player;
import chess.Position;

/**
 * The 'Bishop' class
 */
public class Bishop extends Piece {
    public Bishop(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'b';
    }

	@Override
	public Set<Position> getPossibleMoves(
			Map<Position, Piece> positionToPieceMap, Position originalPos) {
		// TODO Auto-generated method stub
		return null;
	}
}
