/*
 *  IsAttacked_SquareIsAttackedByWhiteQueen.java
 *
 *  chess383 is a collection of chess related utilities.
 *  Copyright (C) 2020 Jörg Dippel
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package chess383.attack;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess383.ColorEnum;
import chess383.ICoordinateFactory;
import chess383.piece.concretion.king.InitialKing;
import chess383.piece.concretion.queen.Queen;
import chess383.piece.concretion.rook.Rook;
import chess383.player.Player;

/**
 * <p>
 * The class IsAttacked_SquareIsAttackedByWhiteQueen implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   June 2020
 *
 */
@DisplayName("the public method boolean isAttacked( ) for class Attack is tested for two given player lists")
public class IsAttacked_SquareIsAttackedByWhiteQueen {  
    
    static private Attack define() {
        
        Player whitePlayer = Player.create( ColorEnum.WHITE, Arrays.asList( InitialKing.create( "e1" ), Queen.create( "g2" ), Rook.create( "h1" ) ) );
        Player blackPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( InitialKing.create( "e8" ) ));
        return Attack.create( blackPlayer, whitePlayer );
    }
    
    static private Attack attack = define();
    
    @ParameterizedTest( name = "given two player lists and a location {0} it is checked whether a white queen can attack this location." )
    @MethodSource("locationAndResultProvider")
    public void testWithArgMethodSource_IfSquareIsAttacked( String location, boolean evaluationResult  ) {
        
        ICoordinateFactory.STANDARD.get();
        
        assertThat( evaluationResult )
                .as( String.format( "Given two player lists then it is checked whether square %s is attacked by the opponent", location ) )
                .isEqualTo( attack.isAttacked( location ) );
    }
    
    public static Stream<Arguments> locationAndResultProvider() {
        return Stream.of(
            
            Arguments.of( "f3", true )
          , Arguments.of( "e4", true )
          , Arguments.of( "d5", true )
          , Arguments.of( "c6", true )
          , Arguments.of( "b7", true )
          , Arguments.of( "a8", true )
          , Arguments.of( "f1", true )
          , Arguments.of( "h3", true )
          , Arguments.of( "g1", true )
          , Arguments.of( "g3", true )
          , Arguments.of( "g4", true )
          , Arguments.of( "g5", true )
          , Arguments.of( "g6", true )
          , Arguments.of( "g7", true )
          , Arguments.of( "g8", true )
          , Arguments.of( "h2", true )
          , Arguments.of( "f2", true )
          , Arguments.of( "e2", true )
          , Arguments.of( "d2", true )
          , Arguments.of( "d2", true )
          , Arguments.of( "b2", true )
          , Arguments.of( "a2", true )
          
          , Arguments.of( "e3", false )
          , Arguments.of( "h1", false )
          , Arguments.of( "g2", false )
            
        ); }
}


