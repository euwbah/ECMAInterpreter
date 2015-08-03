package Token;

/**
 * 10th (last) order
 */
public class AssignmentOperator extends Operator {
    public AssignmentOperatorType type;

    public AssignmentOperator(AssignmentOperatorType type) {
        this.type = type;
        this.opGrp = OperatorGroup.Assignement;
    }

    public enum AssignmentOperatorType {
        Assignment, IncrementAssignment, DecrementAssignmnet, MultiplicateAssignment, DivisiveAssignment, ModuloAssignment, FunctionalAssignment
    }
}
