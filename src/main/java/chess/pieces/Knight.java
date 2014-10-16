package chess.pieces;

import java.util.Map;
import java.util.Set;

import chess.Player;
import chess.Position;

/**
 * The Knight class
 */
public class Knight extends Piece {
    public Knight(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'n';
    }

	@Override
	public Set<Position> getPossibleMoves(
			Map<Position, Piece> positionToPieceMap, Position originalPos) {
		// TODO Auto-generated method stub
		return null;
	}
}
