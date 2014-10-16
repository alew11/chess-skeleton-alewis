package chess.pieces;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import chess.GameState;
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
	public Set<Position> getPossibleMoves(GameState state, Position originalPos) {
			
		Set<Position> possiblePositions = new HashSet<Position>();
		
		// Left
		possiblePositions.addAll(getMovesByDirection(state, originalPos, -2, 0));
		// Up
		possiblePositions.addAll(getMovesByDirection(state, originalPos, 0, 2));
		// Right
		possiblePositions.addAll(getMovesByDirection(state, originalPos, 2, 0));
		// Down
		possiblePositions.addAll(getMovesByDirection(state, originalPos, 0, -2));

		
		return possiblePositions;
	}
	
	private Set<Position> getMovesByDirection(GameState state, Position originalPos, int horiz, int vert) {
		
		Map<Position, Piece> positionToPieceMap = state.getCurrentPositions();
		Set<Position> possiblePositions = new HashSet<Position>();
		Player player = positionToPieceMap.get(originalPos).getOwner();
		char col = originalPos.getColumn();
		int row = originalPos.getRow();
		Position testPos1;
		Position testPos2;
		
		// Check to see which way is the long tail of the 'L'
		if (horiz == 0) {
			testPos1 = new Position((char)(col + 1), row + vert);
			testPos2 = new Position((char)(col - 1), row + vert);
		} else {
			testPos1 = new Position((char)(col + horiz), row + 1);
			testPos2 = new Position((char)(col + horiz), row - 1);
		}
		
		// Check the first possible short tail
		if (testPos1.getColumn() >= Position.MIN_COLUMN && testPos1.getColumn() <= Position.MAX_COLUMN
				&& testPos1.getRow() >= Position.MIN_ROW && testPos1.getRow() <= Position.MAX_ROW) {
			// If there is no piece on the square add the piece
			if (positionToPieceMap.get(testPos1) == null) {
				possiblePositions.add(testPos1);
			} else {
				// If the piece is the opponent's, able to capture
				if (!positionToPieceMap.get(testPos1).getOwner().equals(player)) {
					possiblePositions.add(testPos1);
				}
				}
			
		}
		
		// Check the second possible short tail
		if (testPos2.getColumn() >= Position.MIN_COLUMN && testPos2.getColumn() <= Position.MAX_COLUMN
				&& testPos2.getRow() >= Position.MIN_ROW && testPos2.getRow() <= Position.MAX_ROW) {
			if (positionToPieceMap.get(testPos2) == null) {
				possiblePositions.add(testPos2);
			} else {
				if (!positionToPieceMap.get(testPos2).getOwner().equals(player)) {
					possiblePositions.add(testPos2);
				}
				}
			
		}
		
		return possiblePositions;
	}
}
