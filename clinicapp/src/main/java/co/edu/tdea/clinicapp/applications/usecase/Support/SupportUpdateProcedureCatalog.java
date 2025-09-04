package co.edu.tdea.clinicapp.applications.usecase.Support;

public final class SupportUpdateProcedureCatalog {
    public void execute(Command command) { }
    public static final class Command {
        private final String code;
        private final String name;
        private final double baseCost;
        private final boolean requiresSpecialist;
        private final String specialtyId;

        public Command(String code, String name, double baseCost, boolean requiresSpecialist, String specialtyId) {
            this.code = code;
            this.name = name;
            this.baseCost = baseCost;
            this.requiresSpecialist = requiresSpecialist;
            this.specialtyId = specialtyId;
        }
        public String code() { return code; }
        public String name() { return name; }
        public double baseCost() { return baseCost; }
        public boolean requiresSpecialist() { return requiresSpecialist; }
        public String specialtyId() { return specialtyId; }
    }
}