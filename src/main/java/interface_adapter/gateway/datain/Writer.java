package interface_adapter.gateway.datain;

import java.io.IOException;

public abstract class Writer {
    protected final String username;
    protected String packname;

    /**
     * Get pack/username from the program's current state.
     * This method helps writer classes to determine which path should write to.
     */
    public Writer(String[] partialDataPath) {
        this.username = partialDataPath[0];
        if (partialDataPath[1] != null) {
            this.packname = partialDataPath[1];
        }
    }

    /**
     * Write the object into database
     *
     */
    public abstract void write() throws IOException;

    /**
     * Archive the object in database (store in database but won't load in future)
     *
     */
    public abstract void archive() throws IOException;
}