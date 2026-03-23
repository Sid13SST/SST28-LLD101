# Snake and Ladder
A Low-Level Design and Implementation of the classic Snake and Ladder game in Java.

## Class Diagram

```mermaid
classDiagram
    class Player {
        -String name
        -int position
        +Player(String name)
        +getName() String
        +getPosition() int
        +setPosition(int position)
    }

    class Dice {
        -int numberOfDice
        -Random random
        +Dice(int numberOfDice)
        +roll() int
    }

    class Snake {
        -int head
        -int tail
        +Snake(int head, int tail)
        +getHead() int
        +getTail() int
    }

    class Ladder {
        -int start
        -int end
        +Ladder(int start, int end)
        +getStart() int
        +getEnd() int
    }

    class Board {
        -int size
        -int totalCells
        -Map<Integer, Snake> snakes
        -Map<Integer, Ladder> ladders
        +Board(int n, String difficultyLevel)
        -initializeBoard(int n, String difficultyLevel) void
        +getTotalCells() int
        +getNextPosition(int position) int
    }

    class Game {
        -Board board
        -Dice dice
        -Queue<Player> players
        -List<Player> rank
        +Game(Board board, Dice dice, List<Player> playerList)
        +play() void
    }

    class Main {
        +main(String[] args) void
    }

    Board "1" *-- "many" Snake : contains
    Board "1" *-- "many" Ladder : contains
    Game "1" *-- "1" Board : has
    Game "1" *-- "1" Dice : uses
    Game "1" o-- "many" Player : manages
    Main ..> Game : initializes
```
