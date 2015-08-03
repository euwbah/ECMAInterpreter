##Just another random project##

ECMAInterpreter : an impending Unity script
=============================

###Info:
- Evaluates expressions using a Top-Down recursive decent parser.
- Uses standard Lexing and ParseTrees
- Separated parsing of expressions and statements.
- Everything is an object
- To be converted into a DLL for usage in Unity


###What works:
* Lexing
    + Unary
        - Positive | Negative
    + Literal
        - Numbers
    + Additive
        - Addition | Subtraction

* Debug
    + CodePosition
        - Syntax error position detection
    + TokenGroup.toString()
        - ParseTree ASCIInator

###Current WIP:
- Parsing into ParseTree
    - Parser.TreeParsing.splitBy()
- Evaluation
    Evaluator.Evaluator.EvaluateExpression()