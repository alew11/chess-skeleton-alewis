package chess.pieces;

import java.util.Map;
import java.util.Set;

import chess.Player;
import chess.Position;

/**
 * The 'Rook' class
 */
public class Rook extends Piece {

    public Rook(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'r';
    }

	@Override
	public Set<Position> getPossibleMoves(
			Map<Position, Piece> positionToPieceMap, Position originalPos) {
		// TODO Auto-generated method stub
		return null;
	}
}
