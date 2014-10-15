package chess;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import chess.pieces.Pawn;
import chess.GameState;

/*
 * Possible Pawn Scenarios
 * Move vertical one square
 * Move vertical two squares (optionally) if yet unmoved
 * Take piece diagonally (forward only)
 */
public class PawnTest {

	private GameState state;
	
	@Before
	public void setUp() throws Exception {
		state = new GameState();
	}

    /*
     * Can move forward once from any square as long as piece is not blocked
     * Can take on diagonal
     * Cannot go off of board
     * Include these squares in list
     */
	@Test
    public void testPawnMoveFromOpen() {
		
    	Map<Position, Set<Position>> actualPossible =  new HashMap<Position, Set<Position>>();
    	Set<Position> positionsTo = new HashSet<Position>();
    	
    	// Should be bound to board
    	state.placePiece(new Pawn(Player.White), new Position("e8"));
    	
    	// All valid open board moves
    	state.placePiece(new Pawn(Player.White), new Position("e5"));
    	// For take right
    	state.placePiece(new Pawn(Player.Black), new Position("d6"));
    	// For take left
    	state.placePiece(new Pawn(Player.Black), new Position("f6"));
    	
    	// Blocked piece
    	state.placePiece(new Pawn(Player.White), new Position("b4"));
    	state.placePiece(new Pawn(Player.Black), new Position("b5"));


    	positionsTo.add(new Position("d6"));
    	positionsTo.add(new Position("e6"));
		positionsTo.add(new Position("f6"));
		
		actualPossible.put(new Position("e8"), positionsTo);
    	
    	Map<Position, Set<Position>> calcedPossible = state.listPossibleMoves();   
    	
    	assertEquals(actualPossible, calcedPossible);
    }
	
	/*
     * Can move forward once from any square as long as piece is not blocked
     * Include these squares in list
     */
	@Test
    public void testPawnMoveFromOrigin() {
		
		Map<Position, Set<Position>> actualPossible =  new HashMap<Position, Set<Position>>();
    	Set<Position> positionsToE2 = new HashSet<Position>();
    	Set<Position> positionsToB2 = new HashSet<Position>();
    	
    	// All valid unmoved board moves
    	state.placePiece(new Pawn(Player.White), new Position("e2"));
    	// For take right
    	state.placePiece(new Pawn(Player.Black), new Position("d3"));
    	// For take left
    	state.placePiece(new Pawn(Player.Black), new Position("f3"));
    	
    	// Blocked piece at second position
    	state.placePiece(new Pawn(Player.White), new Position("b2"));
    	state.placePiece(new Pawn(Player.Black), new Position("b4"));


    	positionsToE2.add(new Position("e3"));
    	positionsToE2.add(new Position("e4"));
		positionsToE2.add(new Position("d3"));
		positionsToE2.add(new Position("f3"));
		
		positionsToB2.add(new Position("b3"));
		
		actualPossible.put(new Position("E2"), positionsToE2);
		actualPossible.put(new Position("B2"), positionsToB2);
    	
    	Map<Position, Set<Position>> calcedPossible = state.listPossibleMoves();   
    	
    	assertEquals(actualPossible, calcedPossible);
    }

}
