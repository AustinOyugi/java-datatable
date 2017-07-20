import io.vavr.control.Try;

/**
 * A row of data in the DataTable.
 * Created by Martin Cooper on 08/07/2017.
 */
public class DataRow {

    private final DataTable table;
    private final Integer rowIdx;

    /**
     * Private DataRow constructor.
     * Use 'build' to create instance.
     * @param table The DataTable the DataRow is pointing to.
     * @param rowIdx The row index.
     */
    private DataRow(DataTable table, Integer rowIdx) {
        this.table = table;
        this.rowIdx = rowIdx;
    }

    /**
     * Returns the underlying Data Table for this row.
     * @return Returns the Data Table for this row .
     */
    public DataTable table() {
        return this.table;
    }

    /**
     * Returns the data as an array for this row.
     * @return Returns the data for this row in an array.
     */
    public Object[] data() {
        return this.table.columns()
                .toStream()
                .map(col -> col.getData().get(this.rowIdx))
                .toJavaArray();
    }

    public Object get(Integer colIndex) {
        return null;
    }

    public Object get(String colName) {
        return null;
    }

    public <T> T getAs(Class<T> type, Integer idx) {
        return null;
    }

    public <T> T getAs(Class<T> type, String colName) {
        return null;
    }

    /**
     * Builds an instance of a DataRow.
     * Row Index is validated before creation, returning a Failure on error.
     * @param table The DataTable the DataRow is pointing to.
     * @param rowIdx The row index.
     * @return Returns a DataRow wrapped in a Try.
     */
    public static Try<DataRow> build(DataTable table, Integer rowIdx) {
        Guard.notNull(table, "table");

        // Check row index bounds, and return new DataRow if success.
        return VectorExtensions.outOfBounds(table.rowCount(), rowIdx)
                ? DataTableException.tryError("Invalid row index for DataRow.")
                : Try.success(new DataRow(table, rowIdx));
    }
}
