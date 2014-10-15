package chess;

import static org.junit.Assert.*;

import java.util.HashSet;
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
     * Include these squares in list
     */
	@Test
    public void testPawnMoveFromOpen() {
		
		Set<Position> actualPossible = new HashSet<Position>();
		actualPossible.add(new Position("c5"));
		actualPossible.add(new Position("d6"));

    	
    	state.placePiece(new Pawn(Player.White), new Position("c5"));
    	state.placePiece(new Pawn(Player.White), new Position("a5"));
    	state.placePiece(new Pawn(Player.Black), new Position("a6"));
    	state.placePiece(new Pawn(Player.Black), new Position("d6"));

    	
    	Set<Position> calcedPossible = state.listPossibleMoves();
    	
    	assertEquals(actualPossible, calcedPossible);
    }
	
	/*
     * Can move forward once from any square as long as piece is not blocked
     * Include these squares in list
     */
	@Test
    public void testPawnMoveFromOrigin() {
		
		Set<Position> actualPossible = new HashSet<Position>();
		actualPossible.add(new Position("c3"));
		actualPossible.add(new Position("c4"));
		actualPossible.add(new Position("d3"));
		actualPossible.add(new Position("h3"));
    	
    	state.placePiece(new Pawn(Player.White), new Position("c2"));
    	state.placePiece(new Pawn(Player.Black), new Position("d3"));
    	state.placePiece(new Pawn(Player.White), new Position("h2"));
    	state.placePiece(new Pawn(Player.Black), new Position("h4"));

    	
    	Set<Position> calcedPossible = state.listPossibleMoves();
    	
    	assertEquals(actualPossible, calcedPossible);
    }

}
