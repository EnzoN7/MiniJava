package fr.n7.stl.block.ast.classElement;

public enum ElementModifier {
    NONE,
    STATIC,
    FINAL,
    STATIC_FINAL;

    public String toString() {
        switch (this) {
            case NONE:
                return "";
            case STATIC:
                return "static ";
            case FINAL:
                return "final ";
            case STATIC_FINAL:
                return "static final ";
            default:
                throw new IllegalArgumentException("Error : Unknown ElementModifier token");
        }
    }

}
