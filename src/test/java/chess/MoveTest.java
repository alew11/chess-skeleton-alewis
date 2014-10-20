package chess;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chess.pieces.Pawn;

public class MoveTest {

	private GameState state;
	private GameState stateCompare;

	@Before
	public void setUp() throws Exception {
		state = new GameState();
		stateCompare = new GameState();
		state.reset();
		stateCompare.reset();
	}

	@Test
	public void testValidMove() {
		state.movePiece(new Position("e2"), new Position("e4"));
		
		assertEquals(state.getCurrentPlayer(), Player.Black);
		assertTrue(state.getPieceAt(new Position("e4")) instanceof Pawn);
	}
	
	@Test
	public void testMoveOpposingPlayerPiece() {
		state.movePiece(new Position("e7"), new Position("e6"));
		
		assertEquals(state.getCurrentPlayer(), Player.White);
		assertEquals(stateCompare.getPossibleMoveList(Player.White), state.getPossibleMoveList(Player.White));
	}
	
	@Test
	public void testMoveAtEmptyPosition() {
		state.movePiece(new Position("e5"), new Position("e6"));
		
		assertEquals(state.getCurrentPlayer(), Player.White);
		assertEquals(stateCompare.getPossibleMoveList(Player.White), state.getPossibleMoveList(Player.White));
	}

}
