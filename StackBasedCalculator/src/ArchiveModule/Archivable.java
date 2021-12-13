package ArchiveModule;

/**
 *
 * @author fsonnessa
 */
public interface Archivable {
    /**
     * Implements how to extract the current state of a class
     */
    public ArchivableState getCurrentState();
    
    /**
     * Implements how to set the current state of a class
     */
    public void setCurrentState(ArchivableState state);
    
}
