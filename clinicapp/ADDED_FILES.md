# Added backend components

This branch includes the REST controllers and persistence adapters requested earlier:

- Business controllers: appointments, billing, catalog, clinical history, nursing, orders, patient vitals, patients, and user administration.
- Persistence adapters: clinical history entries and billing ledger records via JPA entities, mappers, and Spring Data repositories.
- Database bootstrap: `schema.sql` defines the necessary tables for patients, security accounts, appointments, catalog items, clinical records, nursing/vital signs, and billing.

These files are ready to be merged into the main branch; documentation files (README, DATA_MODEL, PROGRESS) remain unchanged from the previous update.
