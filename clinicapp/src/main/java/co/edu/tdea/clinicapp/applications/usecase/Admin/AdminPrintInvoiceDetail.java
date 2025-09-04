package co.edu.tdea.clinicapp.applications.usecase.Admin;

public final class AdminPrintInvoiceDetail {
    public String execute(Query query) {
        return query.invoiceNumber;
    }
    public static final class Query {
        private final String invoiceNumber;

        public Query(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
        }
        public String invoiceNumber() { return invoiceNumber; }
    }
}