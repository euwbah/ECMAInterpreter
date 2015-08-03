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

* Parsing (TreeParsing)
    + parse method
    
* Tokens
    + Operators [Listed in Order of Precedence]
        - Primary (TODO)
            - Possession operator
            - ... (TODO)
        - Unary (TODO)
            - Positive
            - Negative
            - Negation
            - Pre-Increment (TODO)
            - Pre-Decrement (TODO)
        - Multiplicative
            - Multiplication
            - Division
            - Modulo
        - Additive
            - Addition
            - Subtraction
        - Relational
            - Less than
            - Less than or Equal to
            - More than or Equal to
            - More than
        - Equatorial
            - Equals
            - Not Equals
        - Conditional AND
        - Conditional OR
        - Conditional ( ?: )
            - Condition Marker '?'
            - If-Else Marker ':'
        - Assignment
            - Assign
            - Add-Assign
            - Subtract-Assign
            - Multiplicate-Assign
            - Divide-Assign
            - Modulo-Assign
            - Lambda (really???)

* Debug
    + CodePosition
        - Syntax error position detection
    + TokenGroup.toString()
        - ParseTree ASCIInator

###Current WIP:
- Primary Operators
    - Method calls, Array Indexes (type casting, maybe?)

- Syntax Handlers
    - Read from next brace-type
    - Check for brace-type imbalances
    - Smart Split
        - e.g: Splitting parameters in a method call.

- Evaluation
    Evaluator.Evaluator.EvaluateExpression()