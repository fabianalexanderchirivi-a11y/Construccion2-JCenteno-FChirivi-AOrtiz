package co.edu.tdea.clinicapp.applications.usecase.Support;

public final class SupportUpdateDrugCatalog {
    public void execute(Command command) { }
    public static final class Command {
        private final String code;
        private final String name;
        private final double unitCost;

        public Command(String code, String name, double unitCost) {
            this.code = code;
            this.name = name;
            this.unitCost = unitCost;
        }
        public String code() { return code; }
        public String name() { return name; }
        public double unitCost() { return unitCost; }
    }
}