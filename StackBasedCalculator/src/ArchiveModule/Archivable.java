package ArchiveModule;

/**
 *
 * @author fsonnessa
 */
public interface Archivable {
    /**
     * Implements the way to how store the element of interest
     */
    public ArchiveItem toSave();
    
    /**
     * Implements the way to how restore the element of interest
     */
    public void toRestore(ArchiveItem item);
    
}
