# mp2 Feedback

## Grade: 2.0

| Compilation | Timeout | Duration |
|:-----------:|:-------:|:--------:|
|Yes|No|10.4|

## Test Results
### cpen221.mp2.graph.Task1
| Test Status | Count |
| ----------- | ----- |
|tests|9|
|errors|0|
|skipped|0|
|failures|3|
#### Failed Tests
1. `testRemoveVertex1() (java.lang.AssertionError: expected:<[]> but was:<[cpen221.mp2.graph.Edge@28075]>)`
1. `testRemoveVertex2() (java.lang.AssertionError: expected:<[cpen221.mp2.graph.Edge@4d1ffaf]> but was:<[cpen221.mp2.graph.Edge@28075, cpen221.mp2.graph.Edge@4d1ffaf]>)`
1. `testRemoveVertex3() (java.lang.AssertionError: expected:<[cpen221.mp2.graph.Edge@4d1ffaf]> but was:<[cpen221.mp2.graph.Edge@28075, cpen221.mp2.graph.Edge@4d1ebc8, cpen221.mp2.graph.Edge@4d1ffaf]>)`
### cpen221.mp2.graph.Task2
| Test Status | Count |
| ----------- | ----- |
|tests|20|
|errors|0|
|skipped|0|
|failures|9|
#### Failed Tests
1. `shortestPath(Graph, Vertex, Vertex, int)[1] (java.lang.AssertionError)`
1. `shortestPath(Graph, Vertex, Vertex, int)[2] (java.lang.AssertionError)`
1. `shortestPath(Graph, Vertex, Vertex, int)[3] (java.lang.AssertionError)`
1. `search(Graph, Vertex, int, Set)[1] (java.lang.AssertionError: expected:<[cpen221.mp2.graph.Vertex@44, cpen221.mp2.graph.Vertex@48]> but was:<[]>)`
1. `search(Graph, Vertex, int, Set)[2] (java.lang.AssertionError: expected:<[cpen221.mp2.graph.Vertex@46, cpen221.mp2.graph.Vertex@44, cpen221.mp2.graph.Vertex@48]> but was:<[]>)`
1. `search(Graph, Vertex, int, Set)[3] (java.lang.AssertionError: expected:<[cpen221.mp2.graph.Vertex@4f, cpen221.mp2.graph.Vertex@6d, cpen221.mp2.graph.Vertex@4b, cpen221.mp2.graph.Vertex@53]> but was:<[]>)`
1. `diameter(Graph, int)[1] (java.lang.AssertionError: expected:<21> but was:<0>)`
1. `diameter(Graph, int)[3] (java.lang.AssertionError: expected:<184> but was:<0>)`
1. `minimumSpanningTree(Graph, Set)[2] (java.lang.AssertionError: expected:<[cpen221.mp2.graph.Edge@c0, cpen221.mp2.graph.Edge@c0, cpen221.mp2.graph.Edge@84, cpen221.mp2.graph.Edge@c4, cpen221.mp2.graph.Edge@88, cpen221.mp2.graph.Edge@c8, cpen221.mp2.graph.Edge@8a, cpen221.mp2.graph.Edge@8c, cpen221.mp2.graph.Edge@cc, cpen221.mp2.graph.Edge@ce, cpen221.mp2.graph.Edge@90, cpen221.mp2.graph.Edge@d0, cpen221.mp2.graph.Edge@9c, cpen221.mp2.graph.Edge@a0, cpen221.mp2.graph.Edge@a0, cpen221.mp2.graph.Edge@e0, cpen221.mp2.graph.Edge@e4, cpen221.mp2.graph.Edge@a8, cpen221.mp2.graph.Edge@ac, cpen221.mp2.graph.Edge@b0, cpen221.mp2.graph.Edge@b2, cpen221.mp2.graph.Edge@b4, cpen221.mp2.graph.Edge@b8, cpen221.mp2.graph.Edge@b8, cpen221.mp2.graph.Edge@bc]> but was:<[]>)`

## Test Coverage
84
## Other Comments
Incorrect RI, unwanted variables, missing contructor

**minor: Similar blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `222` to `233`

**major: Avoid too many `return` statements within this method.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `89` to `89`

**major: Method `main` has 64 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `71` to `139`

**major: Avoid too many `return` statements within this method.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `81` to `81`

**major: Avoid too many `return` statements within this method.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `127` to `127`

**minor: Similar blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `239` to `250`

**minor: Method `main` has a Cognitive Complexity of 38 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `71` to `139`

**minor: Method `getMin` has a Cognitive Complexity of 7 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Dijkstra.java`; lines `53` to `65`

**minor: Method `equals` has a Cognitive Complexity of 9 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Edge.java`; lines `85` to `94`

**minor: Method `getEdge` has a Cognitive Complexity of 6 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `569` to `579`

**minor: Method `edgeLength` has a Cognitive Complexity of 6 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `124` to `132`

**minor: Method `pruneRandomEdges` has 41 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `593` to `643`

**minor: Method `getMin` has a Cognitive Complexity of 7 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `432` to `445`

**minor: `Graph` has 25 methods (exceeds 20 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `12` to `644`

**minor: Similar blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `491` to `497`

**minor: Method `search` has a Cognitive Complexity of 10 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `479` to `513`

**minor: File `Graph.java` has 331 lines of code (exceeds 250 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `1` to `644`

**minor: Method `shortestMap` has 26 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `308` to `338`

**minor: Method `search` has 28 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `479` to `513`

**minor: Method `diameter` has 27 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `527` to `557`

**minor: Method `pruneRandomEdges` has a Cognitive Complexity of 10 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `593` to `643`

**minor: Method `diameter` has a Cognitive Complexity of 20 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `527` to `557`

**minor: Similar blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `254` to `259`

**minor: Method `endPhase` has a Cognitive Complexity of 9 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/gui/GUI.java`; lines `252` to `280`

**minor: Method `init` has a Cognitive Complexity of 8 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/gui/GUI.java`; lines `181` to `211`

**minor: Method `doInBackground` has a Cognitive Complexity of 11 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/gui/GUI.java`; lines `326` to `352`

**minor: Method `make` has 6 arguments (exceeds 4 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/gui/Planet.java`; lines `35` to `36`

**minor: `SpacePanel` has 22 methods (exceeds 20 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/gui/SpacePanel.java`; lines `27` to `374`

**minor: Method `init` has 40 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/gui/SpacePanel.java`; lines `191` to `247`

**minor: Method `keyReleased` has 29 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/gui/SpacePanel.java`; lines `82` to `113`


## Checkstyle Results
### `MillenniumFalcon.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 6 | 8 | `Unused import - cpen221.mp2.graph.ImGraph.` |
| 7 | 8 | `Unused import - cpen221.mp2.models.Link.` |
| 8 | 8 | `Unused import - cpen221.mp2.models.Planet.` |
| 9 | 8 | `Unused import - cpen221.mp2.models.PlanetStatus.` |
| 10 | 8 | `Unused import - cpen221.mp2.util.Heap.` |
| 12 | 8 | `Unused import - java.util.ArrayList.` |
| 13 | 8 | `Unused import - java.util.Collections.` |
| 14 | 8 | `Unused import - java.util.List.` |
| 15 | 8 | `Unused import - java.util.NoSuchElementException.` |
| 20 | None | `Type Javadoc comment is missing an @author tag.` |
| 25 | None | `Comment matches to-do format 'TODO:'.` |
| 30 | None | `Comment matches to-do format 'TODO:'.` |
| 6 | 8 | `Unused import - cpen221.mp2.graph.ImGraph.` |
| 7 | 8 | `Unused import - cpen221.mp2.models.Link.` |
| 8 | 8 | `Unused import - cpen221.mp2.models.Planet.` |
| 9 | 8 | `Unused import - cpen221.mp2.models.PlanetStatus.` |
| 10 | 8 | `Unused import - cpen221.mp2.util.Heap.` |
| 12 | 8 | `Unused import - java.util.ArrayList.` |
| 13 | 8 | `Unused import - java.util.Collections.` |
| 14 | 8 | `Unused import - java.util.List.` |
| 15 | 8 | `Unused import - java.util.NoSuchElementException.` |
| 20 | None | `Type Javadoc comment is missing an @author tag.` |
| 25 | None | `Comment matches to-do format 'TODO:'.` |
| 30 | None | `Comment matches to-do format 'TODO:'.` |
### `Graph.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 0 | None | `File does not end with a newline.` |
| 3 | None | `Using the '.*' form of import should be avoided - java.util.*.` |
| 11 | None | `Type Javadoc comment is missing an @author tag.` |
| 82 | None | `Line is longer than 100 characters (found 111).` |
| 107 | 12 | `'for' is not followed by whitespace.` |
| 107 | 36 | `'{' is not preceded with whitespace.` |
| 108 | 15 | `'if' is not followed by whitespace.` |
| 108 | 93 | `'{' is not preceded with whitespace.` |
| 142 | None | `Line is longer than 100 characters (found 116).` |
| 219 | None | `Comment matches to-do format 'TODO:'.` |
| 228 | None | `Expected an @return tag.` |
| 228 | 36 | `Expected @param tag for 'destination'.` |
| 228 | 65 | `Expected @param tag for 'distance'.` |
| 242 | None | `Line is longer than 100 characters (found 118).` |
| 250 | 5 | `Missing a Javadoc comment.` |
| 250 | 40 | `'(' is preceded with whitespace.` |
| 250 | 50 | `'{' is not preceded with whitespace.` |
| 260 | 37 | `'{' is not preceded with whitespace.` |
| 267 | 12 | `'for' is not followed by whitespace.` |
| 267 | 18 | `'=' is not preceded with whitespace.` |
| 267 | 19 | `'=' is not followed by whitespace.` |
| 267 | 46 | `'{' is not preceded with whitespace.` |
| 294 | 13 | `'<' is preceded with whitespace.` |
| 294 | 18 | `Name 'MST' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 317 | None | `Comment matches to-do format 'TODO:'.` |
| 323 | None | `Expected an @return tag.` |
| 323 | 31 | `Expected @param tag for 'edges'.` |
| 335 | None | `Line is longer than 100 characters (found 122).` |
| 335 | 5 | `Missing a Javadoc comment.` |
| 335 | 79 | `',' is not followed by whitespace.` |
| 340 | None | `Line is longer than 100 characters (found 116).` |
| 355 | None | `Expected an @return tag.` |
| 355 | 31 | `Expected @param tag for 'node'.` |
| 355 | 39 | `Expected @param tag for 'target'.` |
| 370 | None | `Expected an @return tag.` |
| 370 | 30 | `Expected @param tag for 'vertices'.` |
| 370 | 56 | `Expected @param tag for 'distance'.` |
| 397 | 12 | `'for' is not followed by whitespace.` |
| 397 | 49 | `'{' is not preceded with whitespace.` |
| 476 | 23 | `'if' is not followed by whitespace.` |
| 476 | 48 | `'{' is not preceded with whitespace.` |
| 479 | 27 | `'else' is not followed by whitespace.` |
| 479 | 27 | `'{' is not preceded with whitespace.` |
| 481 | 27 | `'if' is not followed by whitespace.` |
| 481 | 46 | `'{' is not preceded with whitespace.` |
| 523 | 16 | `'edgeList' hides a field.` |
| 524 | 12 | `'for' is not followed by whitespace.` |
| 524 | 31 | `'{' is not preceded with whitespace.` |
| 525 | 15 | `'if' is not followed by whitespace.` |
| 525 | 97 | `'{' is not preceded with whitespace.` |
| 549 | None | `Missing a Javadoc comment.` |
| 553 | 13 | `Missing a Javadoc comment.` |
| 553 | 13 | `Redundant 'public' modifier.` |
| 0 | None | `File does not end with a newline.` |
| 3 | None | `Using the '.*' form of import should be avoided - java.util.*.` |
| 11 | None | `Type Javadoc comment is missing an @author tag.` |
| 82 | None | `Line is longer than 100 characters (found 111).` |
| 107 | 12 | `'for' is not followed by whitespace.` |
| 107 | 36 | `'{' is not preceded with whitespace.` |
| 108 | 15 | `'if' is not followed by whitespace.` |
| 108 | 93 | `'{' is not preceded with whitespace.` |
| 142 | None | `Line is longer than 100 characters (found 116).` |
| 219 | None | `Comment matches to-do format 'TODO:'.` |
| 228 | None | `Expected an @return tag.` |
| 228 | 36 | `Expected @param tag for 'destination'.` |
| 228 | 65 | `Expected @param tag for 'distance'.` |
| 242 | None | `Line is longer than 100 characters (found 118).` |
| 250 | 5 | `Missing a Javadoc comment.` |
| 250 | 40 | `'(' is preceded with whitespace.` |
| 250 | 50 | `'{' is not preceded with whitespace.` |
| 260 | 37 | `'{' is not preceded with whitespace.` |
| 267 | 12 | `'for' is not followed by whitespace.` |
| 267 | 18 | `'=' is not preceded with whitespace.` |
| 267 | 19 | `'=' is not followed by whitespace.` |
| 267 | 46 | `'{' is not preceded with whitespace.` |
| 294 | 13 | `'<' is preceded with whitespace.` |
| 294 | 18 | `Name 'MST' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 317 | None | `Comment matches to-do format 'TODO:'.` |
| 323 | None | `Expected an @return tag.` |
| 323 | 31 | `Expected @param tag for 'edges'.` |
| 335 | None | `Line is longer than 100 characters (found 122).` |
| 335 | 5 | `Missing a Javadoc comment.` |
| 335 | 79 | `',' is not followed by whitespace.` |
| 340 | None | `Line is longer than 100 characters (found 116).` |
| 355 | None | `Expected an @return tag.` |
| 355 | 31 | `Expected @param tag for 'node'.` |
| 355 | 39 | `Expected @param tag for 'target'.` |
| 370 | None | `Expected an @return tag.` |
| 370 | 30 | `Expected @param tag for 'vertices'.` |
| 370 | 56 | `Expected @param tag for 'distance'.` |
| 397 | 12 | `'for' is not followed by whitespace.` |
| 397 | 49 | `'{' is not preceded with whitespace.` |
| 476 | 23 | `'if' is not followed by whitespace.` |
| 476 | 48 | `'{' is not preceded with whitespace.` |
| 479 | 27 | `'else' is not followed by whitespace.` |
| 479 | 27 | `'{' is not preceded with whitespace.` |
| 481 | 27 | `'if' is not followed by whitespace.` |
| 481 | 46 | `'{' is not preceded with whitespace.` |
| 523 | 16 | `'edgeList' hides a field.` |
| 524 | 12 | `'for' is not followed by whitespace.` |
| 524 | 31 | `'{' is not preceded with whitespace.` |
| 525 | 15 | `'if' is not followed by whitespace.` |
| 525 | 97 | `'{' is not preceded with whitespace.` |
| 549 | None | `Missing a Javadoc comment.` |
| 553 | 13 | `Missing a Javadoc comment.` |
| 553 | 13 | `Redundant 'public' modifier.` |

