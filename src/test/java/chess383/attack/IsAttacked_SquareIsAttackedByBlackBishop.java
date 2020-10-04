/*
 *  IsAttacked_SquareIsAttackedByBlackBishop.java
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
import chess383.piece.concretion.bishop.Bishop;
import chess383.piece.concretion.king.InitialKing;
import chess383.piece.concretion.pawn.MovedWhitePawn;
import chess383.player.Player;

/**
 * <p>
 * The class IsAttacked_SquareIsAttackedByBlackBishop implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   June 2020
 *
 */
@DisplayName("the public method boolean isAttacked( ) for class Attack is tested for given player lists")
public class IsAttacked_SquareIsAttackedByBlackBishop {  
    
    static private Attack define() {
        
        Player whitePlayer =  Player.create( ColorEnum.WHITE, Arrays.asList( InitialKing.create( "e1" ), MovedWhitePawn.create( "c3" ) ));
        Player blackPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( InitialKing.create( "e8" ), Bishop.create( "b2" ) ));
        return Attack.create( whitePlayer, blackPlayer );
    }
    
    static private Attack attack = define();
    
    
    @ParameterizedTest( name = "given player lists and a location {0} it is checked whether a black bishop can attack this location." )
    @MethodSource("locationAndResultProvider")
    public void testWithArgMethodSource_IfSquareIsAttacked( String location, boolean evaluationResult ) {
        
        ICoordinateFactory.STANDARD.get();
        
        assertThat( evaluationResult )
                .as( String.format( "Given player lists are checked whether location %s is attacked by the opponent or vice-versa", location ) )
                .isEqualTo( attack.isAttacked( location ) );
    }
    
    
    public static Stream<Arguments> locationAndResultProvider() {
        return Stream.of(
            
            Arguments.of( "a1", true )
          , Arguments.of( "a3", true )
          , Arguments.of( "c3", true )
          , Arguments.of( "c1", true )
            
          , Arguments.of( "c2", false )
          , Arguments.of( "b1", false )
          , Arguments.of( "a2", false )
          , Arguments.of( "b3", false )
          , Arguments.of( "d4", false )
          , Arguments.of( "e5", false )
          , Arguments.of( "f6", false )
          , Arguments.of( "g7", false )
          , Arguments.of( "h8", false )
            
        ); }
}


