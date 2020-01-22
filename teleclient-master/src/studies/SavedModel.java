package studies;

import java.io.IOException;

public abstract class SavedModel<A, B, C> {
    private transient int id;
    public SavedModel save(A n, B v){
        throw new java.lang.UnsupportedOperationException("Saving with 2 parameters is not supported");
    }
    public SavedModel save(A n, B v, C c) throws IOException, InterruptedException {
        throw new java.lang.UnsupportedOperationException("Saving with 3 parameters is not supported");
    }

    public int getId() {
        return id;
    }
}
