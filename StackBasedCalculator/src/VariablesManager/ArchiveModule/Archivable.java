package VariablesManager.ArchiveModule;

/**
 *
 * @author fsonnessa
 */
public interface Archivable {
    /**
     * Implements the way to how store the element of interest
     */
    public void saveState();
    
    /**
     * Implements the way to how restore the element of interest
     */
    public void restoreState();
    
}
