package chess.pieces;

import java.util.Map;
import java.util.Set;

import chess.Player;
import chess.Position;

/**
 * The Queen
 */
public class Queen extends Piece{
    public Queen(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'q';
    }

	@Override
	public Set<Position> getPossibleMoves(
			Map<Position, Piece> positionToPieceMap, Position originalPos) {
		// TODO Auto-generated method stub
		return null;
	}
}
