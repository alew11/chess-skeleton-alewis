package chess.pieces;

import java.util.HashSet;
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
		
		Set<Position> possiblePositions = new HashSet<Position>();
		
		// Left
		possiblePositions.addAll(getMovesByDirection(positionToPieceMap, originalPos, -1, 0));
		// Up
		possiblePositions.addAll(getMovesByDirection(positionToPieceMap, originalPos, 0, 1));
		// Right
		possiblePositions.addAll(getMovesByDirection(positionToPieceMap, originalPos, 1, 0));
		// Down
		possiblePositions.addAll(getMovesByDirection(positionToPieceMap, originalPos, 0, -1));

		
		return possiblePositions;
	}
	
	private Set<Position> getMovesByDirection(
			Map<Position, Piece> positionToPieceMap, Position originalPos, int horiz, int vert) {
		
		Set<Position> possiblePositions = new HashSet<Position>();
		Player player = positionToPieceMap.get(originalPos).getOwner();
		char col = originalPos.getColumn();
		int row = originalPos.getRow();
		
		col += horiz;
		row += vert;
		
		while (col >= Position.MIN_COLUMN && col <= Position.MAX_COLUMN && row >= Position.MIN_ROW && row <= Position.MAX_ROW) {
			Position testPos = new Position(col, row);
			
			if (positionToPieceMap.get(testPos) == null) {
				possiblePositions.add(testPos);
			} else {
				if (positionToPieceMap.get(testPos).getOwner().equals(player)) {
					break;
				} else {
					possiblePositions.add(testPos);
					break;
				}
			}
			col += horiz;
			row += vert;
		}
		
		return possiblePositions;
	}
}
